package com.bphost.principal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bphost.principal.model.product;
import com.bphost.principal.model.subcategory;
import com.bphost.principal.model.subcategoryId;

public interface subcategoryRepo extends JpaRepository<subcategory, subcategoryId>{
    @Query("SELECT subcateg FROM subcategory subcateg WHERE subcateg.id.category_id = :id")
    List<subcategory> findAllSubcategoriesByCategory(@Param("id") Integer id);
}
