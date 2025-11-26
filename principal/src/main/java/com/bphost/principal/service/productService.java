package com.bphost.principal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bphost.principal.repository.productRepo;

@Service
public class productService {
    @Autowired
    private productRepo prodRepo;
}
