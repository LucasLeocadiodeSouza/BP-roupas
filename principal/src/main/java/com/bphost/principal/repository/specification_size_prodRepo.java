package com.bphost.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bphost.principal.model.specification_sizeId;
import com.bphost.principal.model.specification_size_prod;

public interface specification_size_prodRepo extends JpaRepository<specification_size_prod, specification_sizeId>{
    
}
