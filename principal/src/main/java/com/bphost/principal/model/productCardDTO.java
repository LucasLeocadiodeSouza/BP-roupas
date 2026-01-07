package com.bphost.principal.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class productCardDTO {
    private Integer    product_id;
    private String     name;
    private String     description;
    private BigDecimal price;
    private String     srcimage;
    private String[]   srcimages;
    private Boolean    active;
    private Integer    category_id;
    private Integer    subcategory_seq;
    private Double     avarage_rating;
    private Integer    total_comments;

    public productCardDTO(Integer product_id,
                          String  name, 
                          String  srcimage,
                          Integer category_id) {
        this.product_id = product_id;
        this.name       = name;
        this.srcimage   = srcimage;
        this.category_id = category_id;
    }

    public productCardDTO(Integer    product_id, 
                          String     name, 
                          BigDecimal price, 
                          String     srcimage, 
                          Integer    category_id,
                          Integer    subcategory_seq) {

        this.product_id      = product_id;
        this.name            = name;
        this.price           = price;
        this.srcimage        = srcimage;
        this.category_id     = category_id;
        this.subcategory_seq = subcategory_seq;
    }


    public productCardDTO(Integer    product_id, 
                          String     name, 
                          String     description, 
                          BigDecimal price, 
                          String     srcimage,
                          Boolean    active, 
                          Integer    category_id, 
                          Integer    subcategory_seq) {
        this.product_id      = product_id;
        this.name            = name;
        this.description     = description;
        this.price           = price;
        this.srcimage        = srcimage;
        this.active          = active;
        this.category_id     = category_id;
        this.subcategory_seq = subcategory_seq;
    }

    public productCardDTO(Integer    product_id, 
                          String     name, 
                          String     description, 
                          BigDecimal price, 
                          String     srcimage,
                          Boolean    active, 
                          Integer    category_id, 
                          Integer    subcategory_seq,
                          Double     avarage_rating,
                          Integer    total_comments) {
        this.product_id      = product_id;
        this.name            = name;
        this.description     = description;
        this.price           = price;
        this.srcimage        = srcimage;
        this.active          = active;
        this.category_id     = category_id;
        this.subcategory_seq = subcategory_seq;
        this.avarage_rating  = avarage_rating;
        this.total_comments  = total_comments;
    }

    public productCardDTO(Integer product_id, 
                          String name, 
                          String description, 
                          BigDecimal price, 
                          String[] srcimages,
                          Boolean active, 
                          Integer category_id, 
                          Integer subcategory_seq) {
        this.product_id      = product_id;
        this.name            = name;
        this.description     = description;
        this.price           = price;
        this.srcimages       = srcimages;
        this.active          = active;
        this.category_id     = category_id;
        this.subcategory_seq = subcategory_seq;
    }

    public productCardDTO(Integer product_id, 
                          String name, 
                          String description, 
                          BigDecimal price, 
                          String srcimage,
                          String[] srcimages, 
                          Boolean active, 
                          Integer category_id, 
                          Integer subcategory_seq) {
        this.product_id      = product_id;
        this.name            = name;
        this.description     = description;
        this.price           = price;
        this.srcimage        = srcimage;
        this.srcimages       = srcimages;
        this.active          = active;
        this.category_id     = category_id;
        this.subcategory_seq = subcategory_seq;
    }

    
}
