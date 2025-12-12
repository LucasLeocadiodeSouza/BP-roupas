package com.bphost.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bphost.principal.model.user_history;
import com.bphost.principal.model.user_historyId;

public interface user_historyRepo extends JpaRepository<user_history, user_historyId> {
    
}
