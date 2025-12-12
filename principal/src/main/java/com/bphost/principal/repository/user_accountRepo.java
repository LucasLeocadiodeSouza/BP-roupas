package com.bphost.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.bphost.principal.model.user_account;

public interface user_accountRepo extends JpaRepository<user_account, Integer> {
    UserDetails findByUsername(String name);
    user_account findIdByUsername(String name);
}
