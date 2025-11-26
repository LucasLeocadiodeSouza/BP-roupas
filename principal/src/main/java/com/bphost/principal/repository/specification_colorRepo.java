package com.bphost.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bphost.principal.model.specification_color;
import com.bphost.principal.model.specification_colorId;

public interface specification_colorRepo extends JpaRepository<specification_color, specification_colorId>{
    
}
