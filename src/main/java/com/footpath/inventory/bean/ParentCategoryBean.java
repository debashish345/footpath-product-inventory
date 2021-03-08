package com.footpath.inventory.bean;

import com.footpath.inventory.model.SubCategory;
import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentCategoryBean {

    private String pcatId;
    private String desc;
    private List<SubCategoryBean> subCategoryList;
}
