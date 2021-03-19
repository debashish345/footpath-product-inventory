package com.footpath.inventory.controller.security;

import com.footpath.inventory.security.config.model.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user/auth")
public class UserManagement {

    private UserMana

    @PostMapping(value = "/test/new")
    public ResponseEntity newUserTest(){



        return ResponseEntity.ok("Test end point");
    }

}
