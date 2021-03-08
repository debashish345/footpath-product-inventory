package com.footpath.inventory.model;

import com.mongodb.lang.NonNull;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "category_type")
public class ParentCategory {

    @MongoId
    private String _id = new ObjectId().toString();

    @NonNull
    private String pcatId;

    @NonNull
    private String desc;

    private List<SubCategory> subCategoryList;
}
