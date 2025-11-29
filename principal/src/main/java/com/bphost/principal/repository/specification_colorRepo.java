package com.bphost.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bphost.principal.model.specification_color;

@Repository
public interface specification_colorRepo extends JpaRepository<specification_color, Integer>{
    @Query("SELECT color FROM specification_color color WHERE color.id = :id")
    specification_color findColorById(@Param("id") Integer id);
}
