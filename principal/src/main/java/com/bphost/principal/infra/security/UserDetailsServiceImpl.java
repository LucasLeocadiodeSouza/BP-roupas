package com.bphost.principal.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.bphost.principal.repository.user_accountRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private user_accountRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}