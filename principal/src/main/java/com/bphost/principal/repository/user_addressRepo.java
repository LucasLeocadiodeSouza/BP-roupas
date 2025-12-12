package com.bphost.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bphost.principal.model.user_address;
import com.bphost.principal.model.user_addressId;

public interface user_addressRepo extends JpaRepository<user_address, user_addressId> {
    
}
