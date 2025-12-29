package com.bphost.principal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.bphost.principal.model.user_history;
import com.bphost.principal.model.user_historyId;

public interface user_historyRepo extends JpaRepository<user_history, user_historyId> {
    @Query("SELECT hist FROM user_history hist WHERE hist.id.useraccount_id = :user_id AND hist.id.product_id = :prod_id")
    user_history findHistoryById(@Param("user_id") Integer user_id, @Param("prod_id") Integer prod_id);
    
    @Query("SELECT hist FROM user_history hist WHERE hist.id.useraccount_id = :user_id")
    List<user_history> findAllHistoryByUser(@Param("user_id") Integer user_id);
}
