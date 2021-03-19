package com.footpath.inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.footpath.inventory.enums.CategoryAvlZone;
import com.mongodb.lang.NonNull;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "category_type")
public class ParentCategory {

    @MongoId
    @JsonIgnore
    private String _id = new ObjectId().toString();

    @NonNull
    private String pcatId;

    @NonNull
    private String parentCategoryDisplayName;

    @NonNull
    private Boolean isActive;

    @NonNull
    private Map<String, Boolean> parentCategoryAvlZone = new HashMap<>();

    @NonNull
    private String desc;

    private List<SubCategory> subCategoryList;
}
