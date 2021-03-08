package com.footpath.inventory.model;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubCategory {

    @MongoId
    private String _id = new ObjectId().toString();
    @NonNull
    private String scatId;
    @NonNull
    private String specific;
    @NonNull
    private String desc;
}
