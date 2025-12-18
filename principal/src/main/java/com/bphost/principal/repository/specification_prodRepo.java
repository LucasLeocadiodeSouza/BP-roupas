package com.bphost.principal.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.bphost.principal.model.specification_prod;
import com.bphost.principal.model.specification_prodId;

public interface specification_prodRepo extends JpaRepository<specification_prod, specification_prodId>{
    @Query("SELECT spec FROM specification_prod spec WHERE spec.id.product_id = :product_id")
    List<specification_prod> findAllByProductId(@Param("product_id") Integer product_id);

    @Query("SELECT spec FROM specification_prod spec WHERE spec.id.product_id = :product_id AND spec.id.size_id = :size_id AND spec.id.color_id = :color_id")
    specification_prod findSpecProdById(Integer product_id, Integer size_id, Integer color_id);
}
