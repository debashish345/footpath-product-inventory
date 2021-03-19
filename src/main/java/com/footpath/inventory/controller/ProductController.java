package com.footpath.inventory.controller;

import com.footpath.inventory.enums.CategoryAvlZone;
import com.footpath.inventory.exception.CustomCategoryException;
import com.footpath.inventory.model.ParentCategory;
import com.footpath.inventory.model.SubCategory;
import com.footpath.inventory.model.Zone;
import com.footpath.inventory.repository.impl.CategoryRepoWrapper;
import com.footpath.inventory.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class ProductController {

	@Autowired
	private CategoryService categoryServiceImpl;

	@PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin("*")
	public ResponseEntity<Object> newParentCategory(@RequestBody ParentCategory parentCategory){
		ParentCategory pCat;
		try{
			pCat = categoryServiceImpl.newParentCategory(parentCategory);
			return ResponseEntity.ok().body(pCat);
		}catch (CustomCategoryException e){
			return ResponseEntity.status(HttpStatus.IM_USED).body(e.getErrorModel());
		}
		catch (Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No content found in request body!!!");
		}
	}

	@GetMapping(value = "/get")
	@CrossOrigin("*")
	public ResponseEntity<Object> getAllCategory(){
		return ResponseEntity.ok().body(categoryServiceImpl.getAllCategory());
	}



	@Autowired
	private CategoryRepoWrapper categoryRepoWrapper;
	@GetMapping(value = "/testPoint")
	public String testPoint(){
		ParentCategory p = new ParentCategory();
		p.setPcatId("TECHNOLOGY-L1");
		p.setParentCategoryDisplayName("TECHNOLOGY");
		p.setIsActive(true);
		p.setDesc("All tech products");

		SubCategory s = new SubCategory();
		s.setScatId("LAPTOP-L2");
		s.setSubCatDisplayName("LAPTOPS");
		s.setSpecific("HP-L3");
		s.setSpecificDisplayName("HP Laptops");
		s.setIsActive(true);
		Map<String, Boolean> subCategoryAvlZoneMap = new HashMap<>();
		subCategoryAvlZoneMap.put(CategoryAvlZone.IN_WB.toString(), true);
		subCategoryAvlZoneMap.put(CategoryAvlZone.IN_TN.toString(), true);
		subCategoryAvlZoneMap.put(CategoryAvlZone.IN_DL.toString(), true);
		s.setSubCategoryAvlZone(subCategoryAvlZoneMap);

		p.setParentCategoryAvlZone(subCategoryAvlZoneMap);
		ArrayList<SubCategory> a = new ArrayList<>();
		a.add(s);
		p.setSubCategoryList(a);
		categoryRepoWrapper.getParentCategoryRepo().save(p);

		return "Complete";
	}

}
