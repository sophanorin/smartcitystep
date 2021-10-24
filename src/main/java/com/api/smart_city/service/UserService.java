package com.api.smart_city.service;


import com.api.smart_city.model.Role;
import com.api.smart_city.model.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    User getUser(Long id);
    List<User>getUsers();
    Collection<Role> getRoles();
}