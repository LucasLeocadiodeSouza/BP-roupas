package com.bphost.principal.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.bphost.principal.exception.CommentException;
import com.bphost.principal.exception.ProductException;
import com.bphost.principal.exception.ProductNotFoundException;
import com.bphost.principal.model.categoryCardDTO;
import com.bphost.principal.model.comments;
import com.bphost.principal.model.commentsDTO;
import com.bphost.principal.model.comments_prod;
import com.bphost.principal.model.comments_prodId;
import com.bphost.principal.model.product;
import com.bphost.principal.model.productCardDTO;
import com.bphost.principal.model.product_img;
import com.bphost.principal.model.product_imgId;
import com.bphost.principal.model.specificationDTO;
import com.bphost.principal.repository.commentsDTORepo;
import com.bphost.principal.repository.commentsProdRepo;
import com.bphost.principal.repository.commentsRepo;
import com.bphost.principal.repository.productCardDTORepo;
import com.bphost.principal.repository.productRepo;
import com.bphost.principal.repository.product_imgRepo;
import com.bphost.principal.repository.specificationDTORepo;

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

    @Autowired
    private specificationDTORepo specDTOrepo;
    
    @Autowired
    private commentsProdRepo commentsprodrepo;
    
    @Autowired
    private commentsRepo commentrepo;

    @Autowired
    private commentsDTORepo commentDTORepo;

    private String uploadDirectory = System.getProperty("user.dir") + "/uploadImage/products";
    //private String tempDirectory = System.getProperty("user.dir") + "/uploadImage/temp";

    @Transactional
    private void registerImage(MultipartFile image, Integer product_id) throws IOException{
       product product = prodRepo.findProductById(product_id);
       if(product == null) throw new ProductNotFoundException("Não encontrado o produto com o Código '" + product_id + "'!");
    
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
    private Integer registerProduct(Integer id, String name, String description, BigDecimal price){
        product product = prodRepo.findProductById(id);
        if(product == null) product = new product();
        
        if(name == null || name.isBlank()) throw new ProductException("Deve ser informado o nome do produto!");
        if(description == null || description.isBlank()) throw new ProductException("Deve ser informado a descrição do produto!");
        if(price == null || price.equals(BigDecimal.ZERO)) throw new ProductException("Deve ser informado o preço do produto!");

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setActive(true);

        prodRepo.save(product);

        return product.getId();
    }

    @Transactional
    public void sendReviewComment(Integer id, String description, Integer rating){
        product product = prodRepo.findProductById(id);
        if(product == null) throw new ProductNotFoundException("Não encontrado o produto com o Código '" + id + "'!");

        if(description == null || description.isBlank()) throw new CommentException("Deve ser informado a descrição do comentario!");
        if(rating == null || rating < 1 || rating > 5) throw new CommentException("Deve ser informado uma avaliação válida para o produto!");

        comments comment = new comments();

        comment.setDescription(description);
        comment.setRating(rating);
        comment.setCreated_at(LocalDate.now());

        comments commentSaved = commentrepo.save(comment);

        comments_prodId commentprodId = new comments_prodId();
        commentprodId.setComment_id(commentSaved.getId());
        commentprodId.setProduct_id(product.getId());
        
        comments_prod commentprod = new comments_prod();
        commentprod.setId(commentprodId);
        commentprod.setProduct(product);
        commentprod.setComments(commentSaved);
        commentsprodrepo.save(commentprod);
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

        if(images == null || images.length < 3) throw new ProductException("Deve ser anexado pelo menos 3 imagens do produto!");
        if(images.length > 10) throw new ProductException("Limite de anexos ao produto atingido! Limite: 10");
        
        Integer prodId = registerProduct(id, name, description, price);
        categservice.registerSubCategProd(prodId, category_id, subcategory_seq);

        for (Integer size : size_id) {
            categservice.registerProductSize(prodId, size, color_id, storage);
        }

        for (MultipartFile image : images) {
            registerImage(image, prodId);
        }
    }


    public categoryCardDTO getParamsRequests(String url){
        String params = url.substring(url.indexOf("?") + 1);

        categoryCardDTO paramrequests = new categoryCardDTO();

        if(params.contains("category_id")) {
            String category = params.substring(params.indexOf("category_id=") + 12);

            paramrequests.setCategory_id(Integer.parseInt(
                category.indexOf("&") == -1 ? category : category.split("&")[0]
            ));

            paramrequests.setCategory_name(categservice.findCategoryById(paramrequests.getCategory_id()).getName());

            if(params.contains("subcategory_id")){
                String subcategory = params.substring(params.indexOf("subcategory_id=") + 15);

                paramrequests.setSubcategory_seq(Integer.parseInt(
                    subcategory.indexOf("&") == -1 ? subcategory : subcategory.split("&")[0]
                ));

                paramrequests.setSubcategory_name(categservice.getSubCategoryById(paramrequests.getCategory_id(), paramrequests.getSubcategory_seq()).getSubcategory_name());
            }

            return  paramrequests;
        }

        if(!params.contains("search")) return null;

        if(params.contains("&")){
            for(String key : params.split("&")){
                if(paramrequests.getCategory_id() != null) return paramrequests;
    
                String[] param = key.split("=");
                if(param[0] == "search"){
                    List<productCardDTO> prodSearchs = prodcardrepo.searchProducts(URLDecoder.decode(param[1], StandardCharsets.UTF_8), null, 7);
                    if(!prodSearchs.isEmpty()) {
                        paramrequests.setCategory_id(prodSearchs.get(0).getCategory_id());
                        paramrequests.setCategory_name(categservice.findCategoryById(paramrequests.getCategory_id()).getName());
                    }
                } 
            }
        }else{
            if(paramrequests.getCategory_id() != null) return paramrequests;

            List<productCardDTO> prodSearchs = prodcardrepo.searchProducts(URLDecoder.decode(params.substring(params.indexOf("search=") + 7), StandardCharsets.UTF_8), null, 7);
            if(!prodSearchs.isEmpty()) {
                paramrequests.setCategory_id(prodSearchs.get(0).getCategory_id());
                paramrequests.setCategory_name(categservice.findCategoryById(paramrequests.getCategory_id()).getName());
            }
        }
        return paramrequests;
    }

    // ########### PRODUCT METHODS ###########

    public List<productCardDTO> getProductInformation(Integer product_id){
        List<productCardDTO> productinfo = prodcardrepo.getProductInformation(product_id);
        return productinfo;
    }

    public List<specificationDTO> getSpecificationColorByProduct(Integer product_id, Integer size_id){
        List<specificationDTO> specifications = specDTOrepo.findAllSpecificationColorByProduct(product_id, size_id);
        return specifications;
    }

    public List<productCardDTO> getProductCardForProductMenu(Integer category_id, Integer subcategory_id, String search, Integer page){
        Map<Integer, productCardDTO> prodCardsMap = new LinkedHashMap<>();

        if (search != null && !search.isBlank()) {
            List<productCardDTO> prodCardsSearch = prodcardrepo.searchProducts(search, page, 30);

            for (productCardDTO dto : prodCardsSearch) { prodCardsMap.put(dto.getProduct_id(), dto);}
        }

        List<productCardDTO> prodCardsCategory = prodcardrepo.findAllProductDTOByCategoryId(category_id, subcategory_id, page);

        for(productCardDTO dto : prodCardsCategory) {
            prodCardsMap.put(dto.getProduct_id(), dto);
        }

        for (productCardDTO prodCard : prodCardsMap.values()) {
            Integer total_comments = commentsprodrepo.countCommentsByProductId(prodCard.getProduct_id());

            Double avarage_rating;
            if(total_comments != 0){
                avarage_rating = ((commentsprodrepo.countCommentsWithRating1ByProductId(prodCard.getProduct_id()) * 1.0) +
                                  (commentsprodrepo.countCommentsWithRating2ByProductId(prodCard.getProduct_id()) * 2.0)  + 
                                  (commentsprodrepo.countCommentsWithRating3ByProductId(prodCard.getProduct_id()) * 3.0)  + 
                                  (commentsprodrepo.countCommentsWithRating4ByProductId(prodCard.getProduct_id()) * 4.0)  + 
                                  (commentsprodrepo.countCommentsWithRating5ByProductId(prodCard.getProduct_id()) * 5.0)) /
                                  total_comments;
            } else avarage_rating = 5.0;

            prodCard.setAvarage_rating(avarage_rating);
            prodCard.setTotal_comments(total_comments);
        };

        return new ArrayList<>(prodCardsMap.values());
    }

    public List<commentsDTO> getCommentsByProductId(Integer product_id, Integer rating, Integer page){
        List<commentsDTO> comments = commentDTORepo.findCommentsByProduct(product_id, rating, page);
        return comments;
    }


    // ########### DASHBOARD METHODS ###########

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

    // ########### SEARCH METHODS ###########
    
    public List<productCardDTO> searchProductsSugestionsByTextInput(String text){
        List<productCardDTO> prodCards = prodcardrepo.searchProducts(text, null, 7);
        return prodCards;
    }
}
