package com.footpath.inventory.security.config.service;

import com.footpath.inventory.security.config.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InventoryUserDetails implements UserDetailsService {

    @Autowired
    private UserManagementServiceImpl userManagementService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        UserEntity existsUser = userManagementService.getUserByEmail(email).orElseThrow(() -> new RuntimeException("User not exists"));

        existsUser.getUserRole().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName())));
        return new User(existsUser.getEmail(), existsUser.getPassword(), authorities);
    }
}
