package com.bphost.principal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.bphost.principal.model.subcat_product;
import com.bphost.principal.model.subcat_productId;

public interface subcat_productRepo extends JpaRepository<subcat_product, subcat_productId>{
    @Query("SELECT scprod FROM subcat_product scprod WHERE scprod.id.product_id = :prod_id AND scprod.id.category_id = :categ_id AND scprod.id.subcategory_seq = :subcateg_seq")
    subcat_product findSubcategProdById(@Param("prod_id") Integer prod_id, @Param("categ_id") Integer categ_id, @Param("subcateg_seq") Integer subcateg_seq);

    @Query("SELECT scprod FROM subcat_product scprod WHERE scprod.id.category_id = :categ_id")
    List<subcat_product> findAllSubcategProdByCategory(@Param("categ_id") Integer categ_id);

    @Query("SELECT scprod FROM subcat_product scprod WHERE scprod.id.subcategory_seq = :subcateg_seq")
    List<subcat_product> findAllSubcategProdBySubCategory(@Param("subcateg_seq") Integer subcateg_id);
}
