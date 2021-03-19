package com.footpath.inventory.security.config.service;

import com.footpath.inventory.security.config.model.UserEntity;

import java.util.Optional;

public interface UserManagementService {

    Optional<UserEntity> getUserByEmail(String email);
}
