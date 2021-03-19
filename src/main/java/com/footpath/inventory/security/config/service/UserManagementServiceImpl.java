package com.footpath.inventory.security.config.service;

import com.footpath.inventory.security.config.model.UserEntity;
import com.footpath.inventory.security.config.repository.UserManagementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private UserManagementRepo userManagementRepo;

    @Override
    public Optional<UserEntity> getUserByEmail(String email) {
        return Optional.of(userManagementRepo.findByEmail(email).get());
    }
}

