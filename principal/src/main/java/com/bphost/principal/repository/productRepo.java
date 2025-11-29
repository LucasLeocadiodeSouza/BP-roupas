package com.bphost.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.bphost.principal.model.product;

public interface productRepo extends JpaRepository<product, Integer>{
    @Query("SELECT prod FROM product prod WHERE prod.id = :product_id")
    product findProductById(@Param("product_id") Integer product_id);
}
