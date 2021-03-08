package com.footpath.inventory.controller;

import com.footpath.inventory.exception.CustomCategoryException;
import com.footpath.inventory.model.ParentCategory;
import com.footpath.inventory.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class ProductController {

	@Autowired
	private CategoryService categoryServiceImpl;

	@PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
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
	public ResponseEntity<Object> getAllCategory(){
		return ResponseEntity.ok().body(categoryServiceImpl.getAllCategory());
	}

}
