package com.bphost.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bphost.principal.model.product;

public interface productRepo extends JpaRepository<product, Integer>{
    
}
