package com.footpath.inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.footpath.inventory.enums.CategoryAvlZone;
import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubCategory {

    @MongoId
    @JsonIgnore
    private String _id = new ObjectId().toString();
    @NonNull
    private String scatId;

    @NonNull
    private String subCatDisplayName;

    @NonNull
    private String specific;

    @NonNull
    private String specificDisplayName;

    @NonNull
    private Boolean isActive;

    @NonNull
    private Map<String, Boolean> subCategoryAvlZone = new HashMap<>();

    @NonNull
    private String desc;
}
