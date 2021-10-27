package com.api.smart_city.controller;

import com.api.smart_city.dto.user.RoleDTO;
import com.api.smart_city.dto.user.UserDTO;
import com.api.smart_city.model.Role;
import com.api.smart_city.model.User;
import com.api.smart_city.service.UserService;
import com.api.smart_city.dto.RoleToUserForm;
import com.api.smart_city.utils.CustomResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity getUsers() {

        Collection<UserDTO> users = new ArrayList<>();

        userService.getUsers().forEach(user -> {
            users.add(new UserDTO(user));
        });

        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity getUsers(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok().body(new UserDTO(userService.getUser(userId)));
    }

    @PostMapping("/user/save")
    public ResponseEntity saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(new UserDTO(userService.saveUser(user)));
    }

    @GetMapping(path = "/roles")
    public  ResponseEntity getRoles() {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        Collection<RoleDTO> roles = new ArrayList<>();
        userService.getRoles().forEach(role -> {
            roles.add(new RoleDTO(role));
        });

        return ResponseEntity.created(uri).body(roles);
    }

    @PostMapping("/role/save")
    public ResponseEntity<CustomResponse> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        userService.saveRole(role);
        return ResponseEntity.created(uri).body(new CustomResponse(true,null,"OK"));
    }

    @PostMapping("/role/addToUser")
    public ResponseEntity<CustomResponse> addRoleToUser(@RequestBody RoleToUserForm form) throws JSONException {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().body(new CustomResponse(true,null,"OK"));
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

}

