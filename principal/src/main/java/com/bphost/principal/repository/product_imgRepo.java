package com.bphost.principal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bphost.principal.model.product_img;
import com.bphost.principal.model.product_imgId;

public interface product_imgRepo extends JpaRepository<product_img, product_imgId>{
    @Query("SELECT image FROM product_img image WHERE image.id.product_id = :product_id")
    List<product_img> findAllImgByProductId(@Param("product_id") Integer product_id);

    @Query("SELECT MAX(i.id.seq) FROM product_img i WHERE i.id.product_id = :product_id")
    Integer findMaxSeqByProductId(@Param("product_id") Integer product_id);
}
