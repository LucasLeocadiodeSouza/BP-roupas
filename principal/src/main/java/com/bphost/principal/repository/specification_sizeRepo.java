package com.bphost.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bphost.principal.model.specification_size;
import com.bphost.principal.model.specification_sizeId;

public interface specification_sizeRepo extends JpaRepository<specification_size, specification_sizeId>{
    
}
