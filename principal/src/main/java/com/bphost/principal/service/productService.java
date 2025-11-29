package com.bphost.principal.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bphost.principal.model.product;
import com.bphost.principal.model.product_img;
import com.bphost.principal.model.product_imgId;
import com.bphost.principal.repository.productRepo;
import com.bphost.principal.repository.product_imgRepo;
import jakarta.transaction.Transactional;

@Service
public class productService {
    @Autowired
    private productRepo prodRepo;

    @Autowired
    private product_imgRepo prodImgRepo;

    @Transactional
    public void registerImage(String image, Integer product_id){
       product product = prodRepo.findProductById(product_id);
       if(product == null) throw new RuntimeException("Não encontrado o produto com o Código '" + product_id + "'!");
    
       Integer seqimg = prodImgRepo.findMaxSeqByProductId(product_id);
       if(seqimg == null) seqimg = 0;
    
       product_img imagemimovel = new product_img();
       imagemimovel.setProduct(product);
       imagemimovel.setSrc(image);
       imagemimovel.setId(new product_imgId(seqimg + 1, product_id));
    
       prodImgRepo.save(imagemimovel);
    }

    @Transactional
    public void registerProduct(Integer id, String name, String description, BigDecimal price){
        product product = prodRepo.findProductById(id);
        if(product == null) product = new product();
        
        if(name == null || name.isBlank()) throw new RuntimeException("Deve ser informado o nome do produto!");
        if(description == null || description.isBlank()) throw new RuntimeException("Deve ser informado a descrição do produto!");
        if(price == null || price.equals(BigDecimal.ZERO)) throw new RuntimeException("Deve ser informado o preço do produto!");

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setActive(true);

        prodRepo.save(product);
    }
}
