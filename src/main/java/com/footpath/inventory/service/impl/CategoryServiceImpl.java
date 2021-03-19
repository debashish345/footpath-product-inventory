package com.footpath.inventory.service.impl;

import com.footpath.inventory.enums.MessageEnum;
import com.footpath.inventory.exception.CustomCategoryException;
import com.footpath.inventory.model.ParentCategory;
import com.footpath.inventory.repository.impl.CategoryRepoWrapper;
import com.footpath.inventory.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepoWrapper categoryRepoWrapper;

    @Override
    public ParentCategory newParentCategory(ParentCategory parentCategory) {

        ParentCategory newPCat = null;

        ParentCategory savedPCat = (ParentCategory) categoryRepoWrapper.getParentCategoryRepo().existsBypcatId(parentCategory.getPcatId());

        if(savedPCat!=null){
            Boolean subCatExists = false;
            List<String> existedSpecificList = savedPCat.getSubCategoryList().stream().map(subCategory -> {
                return subCategory.getSpecific();
            }).collect(Collectors.toList());
            List<String> existedScatList = savedPCat.getSubCategoryList().stream().map(subCategory -> {
                return subCategory.getScatId();
            }).collect(Collectors.toList());

            parentCategory.getSubCategoryList().forEach(subCategory -> {
                Boolean subCatIdFlag = existedScatList.contains(subCategory.getScatId());
                Boolean specificFlag = existedSpecificList.contains(subCategory.getSpecific());
                if( subCatIdFlag && specificFlag ){
                    updateCategory(savedPCat, parentCategory);
                    throw new CustomCategoryException(MessageEnum.ALREADY_EXIST.toString(), "ParentCategory, SubCategory and Scecific  already exist!!!", null);
                }else if(subCatIdFlag){
                    updateSpecific(savedPCat, parentCategory);
                    throw new CustomCategoryException(MessageEnum.ALREADY_EXIST.toString(), "Scecific updated. Subcat already exist!!!", null);
                }else if(specificFlag){
                    updateSubCat(savedPCat, parentCategory);
                    throw new CustomCategoryException(MessageEnum.ALREADY_EXIST.toString(), "Subcat updated. Scecific already exist!!!", null);
                }else{
                    System.out.println("ok");
                    savedPCat.getSubCategoryList().add(parentCategory.getSubCategoryList().get(0));
                    categoryRepoWrapper.getParentCategoryRepo().save(savedPCat);
                    throw new CustomCategoryException(MessageEnum.UPDATE_SUCCESS.toString(), "Subcat category added successfully!!!", null);
                }
            });
        }else{
            newPCat = categoryRepoWrapper.getParentCategoryRepo().save(parentCategory);
        }

        return Optional.ofNullable(newPCat).orElseThrow(() -> new CustomCategoryException(null, null, null) );
    }

    private ParentCategory updateSubCat(ParentCategory parentCategory, ParentCategory newParentCategory){

        parentCategory.getSubCategoryList().forEach(subCategory -> {
            if(subCategory.getScatId().equalsIgnoreCase(newParentCategory.getSubCategoryList().get(0).getScatId())) {
                subCategory.setScatId(newParentCategory.getSubCategoryList().get(0).getScatId());
                subCategory.setSubCatDisplayName(newParentCategory.getSubCategoryList().get(0).getSubCatDisplayName());
                subCategory.setIsActive(newParentCategory.getSubCategoryList().get(0).getIsActive());
                subCategory.setSubCategoryAvlZone(newParentCategory.getSubCategoryList().get(0).getSubCategoryAvlZone());
                subCategory.setDesc(newParentCategory.getSubCategoryList().get(0).getDesc());
            }else{
                parentCategory.getSubCategoryList().add(newParentCategory.getSubCategoryList().get(0));
                categoryRepoWrapper.getParentCategoryRepo().save(parentCategory);
                throw new CustomCategoryException(MessageEnum.ALREADY_EXIST.toString(), "Scecific updated. Subcat already exist!!! Modification saved(if modified)", null);
            }

        });
        return categoryRepoWrapper.getParentCategoryRepo().save(parentCategory);
    }

    private ParentCategory updateSpecific(ParentCategory parentCategory, ParentCategory newParentCategory){
        parentCategory.getSubCategoryList().forEach(subCategory -> {
            if(subCategory.getSpecific().equalsIgnoreCase(newParentCategory.getSubCategoryList().get(0).getSpecific())) {
                subCategory.setSpecific(newParentCategory.getSubCategoryList().get(0).getSpecific());
                subCategory.setSpecificDisplayName(newParentCategory.getSubCategoryList().get(0).getSpecificDisplayName());
                subCategory.setIsActive(true);
                subCategory.setSubCategoryAvlZone(newParentCategory.getSubCategoryList().get(0).getSubCategoryAvlZone());
                subCategory.setDesc(newParentCategory.getSubCategoryList().get(0).getDesc());
            }else{
                parentCategory.getSubCategoryList().add(newParentCategory.getSubCategoryList().get(0));
                categoryRepoWrapper.getParentCategoryRepo().save(parentCategory);
                throw new CustomCategoryException(MessageEnum.ALREADY_EXIST.toString(), "Scecific updated. Subcat already exist!!!", null);
            }
        });
        return categoryRepoWrapper.getParentCategoryRepo().save(parentCategory);
    }

    private ParentCategory updateCategory(ParentCategory parentCategory, ParentCategory newParentCategory){

        parentCategory.setDesc(newParentCategory.getDesc());
        parentCategory.setIsActive(newParentCategory.getIsActive());
        parentCategory.setParentCategoryAvlZone(newParentCategory.getParentCategoryAvlZone());
        parentCategory.getSubCategoryList().forEach(subCategory -> {
            if(subCategory.getScatId().equalsIgnoreCase(newParentCategory.getSubCategoryList().get(0).getScatId()) &&
                    subCategory.getSpecific().equalsIgnoreCase(newParentCategory.getSubCategoryList().get(0).getSpecific())){
                Boolean flag = true;
                subCategory.setSubCatDisplayName(newParentCategory.getSubCategoryList().get(0).getSubCatDisplayName());
                subCategory.setSpecificDisplayName(newParentCategory.getSubCategoryList().get(0).getSpecificDisplayName());
                subCategory.setDesc(newParentCategory.getSubCategoryList().get(0).getDesc());
                subCategory.setIsActive(newParentCategory.getSubCategoryList().get(0).getIsActive());
                subCategory.setSubCategoryAvlZone(newParentCategory.getSubCategoryList().get(0).getSubCategoryAvlZone());
            }
        });
        return categoryRepoWrapper.getParentCategoryRepo().save(parentCategory);
    }

    @Override
    public List<ParentCategory> getAllCategory() {
        return categoryRepoWrapper.getParentCategoryRepo().findAll();
    }
}
