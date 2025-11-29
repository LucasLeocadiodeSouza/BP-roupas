package com.bphost.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bphost.principal.model.specification_color_prodId;
import com.bphost.principal.model.specification_color_prod;

public interface specification_color_prodRepo extends JpaRepository<specification_color_prod, specification_color_prodId>{
    
}
