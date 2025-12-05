package com.bphost.principal.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bphost.principal.model.product;
import com.bphost.principal.model.productCardDTO;
import com.bphost.principal.model.product_img;
import com.bphost.principal.model.product_imgId;
import com.bphost.principal.model.subcat_product;
import com.bphost.principal.repository.productCardDTORepo;
import com.bphost.principal.repository.productRepo;
import com.bphost.principal.repository.product_imgRepo;
import com.bphost.principal.repository.subcat_productRepo;

import jakarta.transaction.Transactional;

@Service
public class productService {
    @Autowired
    private productRepo prodRepo;

    @Autowired
    private product_imgRepo prodImgRepo;

    @Autowired
    private categoryService categservice;

    @Autowired
    private productCardDTORepo prodcardrepo;

    public static String uploadDirectory = System.getProperty("user.dir") + "/uploadImage/products";
    public static String tempDirectory = System.getProperty("user.dir") + "/uploadImage/temp";

    @Transactional
    public void registerImage(MultipartFile image, Integer product_id) throws IOException{
       product product = prodRepo.findProductById(product_id);
       if(product == null) throw new RuntimeException("Não encontrado o produto com o Código '" + product_id + "'!");
    
       Integer seqimg = prodImgRepo.findMaxSeqByProductId(product_id);
       if(seqimg == null) seqimg = 0;

        seqimg = seqimg + 1;

        String original = Objects.requireNonNull(image.getOriginalFilename());
        String ext = original.substring(original.lastIndexOf("."));

        String newName = "product_" + product.getId() + "_" + seqimg + ext;

        Path fileNameAndPath = Paths.get(uploadDirectory, newName);
        Files.write(fileNameAndPath, image.getBytes());

       product_img prod_image = new product_img();
       prod_image.setProduct(product);
       prod_image.setSrc(newName);
       prod_image.setId(new product_imgId(product_id, seqimg));
    
       prodImgRepo.save(prod_image);
    }

    @Transactional
    public Integer registerProduct(Integer id, String name, String description, BigDecimal price){
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

        return product.getId();
    }

    @Transactional
    public void adapterRegisterProduct(Integer         id, 
                                       String          name, 
                                       String          description, 
                                       BigDecimal      price, 
                                       Integer         category_id, 
                                       Integer         subcategory_seq, 
                                       Integer         color_id, 
                                       Integer[]       size_id, 
                                       Integer         storage, 
                                       MultipartFile[] images) throws IOException{

        if(images == null || images.length < 3) throw new RuntimeException("Deve ser anexado pelo menos 3 imagens do produto!");
        if(images.length > 7) throw new RuntimeException("Limite de anexos ao produto atingido! Limite: 6");
        
        Integer prodId = registerProduct(id, name, description, price);
        categservice.registerSubCategProd(prodId, category_id, subcategory_seq);

        for (Integer size : size_id) {
            categservice.registerProductSize(prodId, size, color_id, storage);
        }

        for (MultipartFile image : images) {
            registerImage(image, prodId);
        }
    }

    public List<productCardDTO> getProductCardByCategoryId(Integer category_id, Integer subcategory_id, Integer page){
        List<productCardDTO> prodCards = prodcardrepo.findAllProductDTOByCategoryId(category_id, subcategory_id, page);
        return prodCards;
    }

    public List<productCardDTO> getBestSellingProducts(Integer category_id, Integer subcategory_id){
        List<productCardDTO> prodCards = prodcardrepo.getBestSellingProducts(category_id, subcategory_id);
        return prodCards;
    }

    public List<productCardDTO> getNewDiscovery(){
        List<productCardDTO> prodCards = prodcardrepo.getNewDiscovery();
        return prodCards;
    }

    public List<productCardDTO> getBestRatedDeals(){
        List<productCardDTO> prodCards = prodcardrepo.getBestRatedDeals();
        return prodCards;
    }

    public List<productCardDTO> getHouseRecommendations(){
        List<productCardDTO> prodCards = prodcardrepo.getHouseRecommendations();
        return prodCards;
    }
}
