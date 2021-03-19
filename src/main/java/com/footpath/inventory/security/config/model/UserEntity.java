package com.footpath.inventory.security.config.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "user")
public class UserEntity {

    @MongoId
    @JsonIgnore
    private String _id = new ObjectId().toString();
    @NonNull
    private String email; //second level unique key
    @JsonIgnore
    @NonNull
    private String password;
    @NonNull
    private String firstName;
    private String surName;
    private String gender;
    private String dob;

    private List<UserRole> userRole;
    private List<UserAddress> userAddresses;
    private List<UserActivity> userActivity;
}
