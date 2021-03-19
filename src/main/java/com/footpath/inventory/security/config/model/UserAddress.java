package com.footpath.inventory.security.config.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class UserAddress {

    @MongoId
    @JsonIgnore
    private String _id = new ObjectId().toString();

    private String country;
    private String state;
    private String postalCode;
}
