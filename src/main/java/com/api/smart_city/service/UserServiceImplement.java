package com.api.smart_city.service;

import com.api.smart_city.dto.user.RoleDTO;
import com.api.smart_city.dto.user.UserDTO;
import com.api.smart_city.model.Role;
import com.api.smart_city.model.User;
import com.api.smart_city.model.UserDetail;
import com.api.smart_city.repository.RoleRepository;
import com.api.smart_city.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImplement implements UserService, UserDetailsService {
   private final UserRepository userRepository;
   private final RoleRepository roleRepository;
   private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });

            UserDetail userDetail = new UserDetail(user.getUsername(), user.getPassword(), authorities);

            userDetail.setId(user.getId());
            userDetail.setFirstName(user.getFirstName());
            userDetail.setLastName(user.getLastName());
            userDetail.setUsername(user.getUsername());
            userDetail.setGender(user.getGender());
            userDetail.setEmail(user.getEmail());
            userDetail.setAddress(user.getAddress());
            userDetail.setRoles(user.getRoles());

            return userDetail;
        }
    }

    @Override
    @Transient
    public User saveUser(User user) {
        log.info("Saving new user {} {} to the database", user.getFirstName() ,user.getFirstName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByName("Regular");
        user.getRoles().add(role);

        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());

        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);

        if(username == null || roleName == null)
            throw new IllegalStateException("username or roleName could not be null");

        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUser(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() ->  new IllegalStateException("User with id " + id + " does not exists"));
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");

        return userRepository.findAll();
    }

    @Override
    public Collection<Role> getRoles() {
        return roleRepository.findAll();
    }
}
