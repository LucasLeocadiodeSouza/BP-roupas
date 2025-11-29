package com.bphost.principal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.bphost.principal.model.category;

public interface categoryRepo extends JpaRepository<category, Integer>{
    @Query("SELECT categ FROM category categ WHERE categ.active = true")
    List<category> findAllCategActives();

    @Query("SELECT categ FROM category categ WHERE categ.id = :id")
    category findCategById(@Param("id") Integer id);
}
