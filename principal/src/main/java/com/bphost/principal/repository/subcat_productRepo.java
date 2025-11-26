package com.bphost.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bphost.principal.model.product;
import com.bphost.principal.model.subcat_product;
import com.bphost.principal.model.subcat_productId;

public interface subcat_productRepo extends JpaRepository<subcat_product, subcat_productId>{
    
}
