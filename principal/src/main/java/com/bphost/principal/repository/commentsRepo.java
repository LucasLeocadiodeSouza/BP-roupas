package com.bphost.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bphost.principal.model.comments;

@Repository
public interface commentsRepo extends JpaRepository<comments, Integer> {
    
}
