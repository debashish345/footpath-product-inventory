package com.footpath.inventory.security.config.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

    @MongoId
    @JsonIgnore
    private String _id = new ObjectId().toString();

    private String roleName;
}
