package com.footpath.inventory.service;

import com.footpath.inventory.bean.ParentCategoryBean;
import com.footpath.inventory.model.ParentCategory;

import java.util.List;

public interface CategoryService {

    ParentCategory newParentCategory(ParentCategory parentCategory);

    List<ParentCategory> getAllCategory();

}
