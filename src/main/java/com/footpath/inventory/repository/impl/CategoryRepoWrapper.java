package com.footpath.inventory.repository.impl;

import com.footpath.inventory.repository.ParentCategoryRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Data
public class CategoryRepoWrapper {

    @Autowired
    private ParentCategoryRepo parentCategoryRepo;
}
