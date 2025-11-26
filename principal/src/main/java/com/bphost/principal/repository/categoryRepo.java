package com.bphost.principal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bphost.principal.model.category;
import com.bphost.principal.model.product;

public interface categoryRepo extends JpaRepository<category, Integer>{
    @Query("SELECT categ FROM category categ WHERE categ.active = true")
    List<category> findAllCategActives();
}
