package com.footpath.inventory.service.impl;

import com.footpath.inventory.bean.ParentCategoryBean;
import com.footpath.inventory.bean.SubCategoryBean;
import com.footpath.inventory.enums.MessageEnum;
import com.footpath.inventory.exception.CustomCategoryException;
import com.footpath.inventory.model.ParentCategory;
import com.footpath.inventory.repository.impl.CategoryRepoWrapper;
import com.footpath.inventory.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepoWrapper categoryRepoWrapper;

    /*add parent category, sub category and specific*/
    @Override
    public ParentCategory newParentCategory(ParentCategory parentCategory) {

        ParentCategory savedPCat = (ParentCategory) categoryRepoWrapper.getParentCategoryRepo().existsBypcatId(parentCategory.getPcatId());

        ParentCategory pCat = new ParentCategory();

        if (savedPCat!=null){//parent category not exists
            Boolean sCatOrSpecficFlag = true;
            List<String> savedSCatId = savedPCat.getSubCategoryList().stream().map(sCat -> sCat.getScatId()).collect(Collectors.toList());
            List<String> savedSpecific = savedPCat.getSubCategoryList().stream().map(sCat -> sCat.getSpecific()).collect(Collectors.toList());

            //if sCatId and specific both present in db it will be true
            sCatOrSpecficFlag = savedSCatId.contains(parentCategory.getSubCategoryList().get(0).getScatId()) &&
                                savedSpecific.contains(parentCategory.getSubCategoryList().get(0).getSpecific());

            savedPCat.setDesc(parentCategory.getDesc());//modily parent category desc
            if(!sCatOrSpecficFlag) {// if any of sCatId and specific not present
                parentCategory.getSubCategoryList().forEach(data -> {
                    savedPCat.getSubCategoryList().add(data);
                });
            }else{// if pCatId, sCatId, specific exists
                throw new CustomCategoryException(MessageEnum.ALREADY_EXIST.toString(), "Identical value exists!!!", null);
            }
            pCat = savedPCat;
        }else {// new parent category, create a new regitry
            pCat.setPcatId(parentCategory.getPcatId().toUpperCase());
            pCat.setDesc(parentCategory.getDesc());
            pCat.setSubCategoryList(parentCategory.getSubCategoryList());
        }
        return categoryRepoWrapper.getParentCategoryRepo().save(pCat);
    }

    @Override
    public List<ParentCategoryBean> getAllCategory() {

        List<ParentCategory> list = categoryRepoWrapper.getParentCategoryRepo().findAll();
        List<ParentCategoryBean> beanList = new ArrayList<>();
        List<SubCategoryBean> sBeanList = new ArrayList<>();
        ParentCategoryBean pBean = new ParentCategoryBean();
        SubCategoryBean cBean = new SubCategoryBean();

        list.forEach(data -> {
            BeanUtils.copyProperties(data, pBean);
            data.getSubCategoryList().forEach(sCat -> {
                BeanUtils.copyProperties(sCat, cBean);
                sBeanList.add(cBean);
            });
            pBean.setSubCategoryList(sBeanList);
            beanList.add(pBean);
        });

        return beanList;
    }


}
