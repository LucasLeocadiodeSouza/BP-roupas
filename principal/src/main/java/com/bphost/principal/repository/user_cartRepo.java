package com.bphost.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bphost.principal.model.user_cart;
import com.bphost.principal.model.user_cartId;

public interface user_cartRepo extends JpaRepository<user_cart, user_cartId> {
    
}
