package com.footpath.inventory.repository;

import com.footpath.inventory.model.ParentCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentCategoryRepo extends MongoRepository<ParentCategory, String> {

    @Query("{$or : [{pcatId  : ?0}, {subCategoryList.scatId  : ?1}, {subCategoryList.specific  : ?2}]}")
    Object existsById(String pcatId, String scatId, String specific);

    @Query("{pcatId : {$eq  : ?0}}")
    Object existsBypcatId(String pcatId);

    @Query("{subCategoryList.scatId : {$eq : ?0}}")
    Object existsByscatId(String scatId);

    @Query("{subCategoryList.specific : {$eq : ?0}}")
    Object existsBySpecific(String specific);

}
