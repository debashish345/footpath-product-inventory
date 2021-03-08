package com.footpath.inventory.bean;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryBean {

    private String scatId;
    private String specific;
    private String desc;
}
