package com.api.smart_city.security;

import com.api.smart_city.filter.CustomAuthenticationFilter;
import com.api.smart_city.filter.CustomAuthorizationFilter;
import com.api.smart_city.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers(
                "/api/user/save",
                "/api/user/{userId}",
                "/api/login/**",
                "/api/overall/**",
                "/api/token/refresh/**",
                "/api/post/{postId}/addComment",
                "/api/posts",
                "/api/attachments/upload",
                "/api/attachments/upload"
        ).permitAll();

        http.authorizeRequests().antMatchers(
                "/api/post/save",
                "/api/post/{postId}/addAttachments",
                "/api/post/{postId}/addFeedback",
                "/api/post/remove/{postId}",
                "/api/post/{postId}/update",
                "/api/attachments/upload"
        ).hasAnyAuthority("Administrator","Content_Creator","Author");

        http.authorizeRequests().antMatchers(
                "/api/category/add",
                "/api/feature/add",
                "/api/roles",
                "/api/role/**",
                "/api/user/**",
                "/api/users"
        ).hasAnyAuthority("Administrator");

        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
