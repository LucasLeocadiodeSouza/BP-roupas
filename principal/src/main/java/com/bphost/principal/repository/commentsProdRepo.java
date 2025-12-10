package com.bphost.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bphost.principal.model.comments_prod;
import com.bphost.principal.model.comments_prodId;

public interface commentsProdRepo extends JpaRepository<comments_prod, comments_prodId> {
    
}
    