package com.bphost.principal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bphost.principal.model.specification_size;

@Repository
public interface specification_sizeRepo extends JpaRepository<specification_size, Integer>{
    @Query("SELECT size FROM specification_size size WHERE size.id = :id")
    specification_size findSizeById(@Param("id") Integer id);

    @Query("SELECT size FROM specification_size size WHERE size.category.id = :categ_id")
    List<specification_size> findAllSizeByCategoryID(@Param("categ_id") Integer categ_id);
}
