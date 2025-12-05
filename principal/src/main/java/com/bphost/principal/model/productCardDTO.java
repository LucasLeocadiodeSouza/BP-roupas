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
    private Boolean    active;
    private Integer    category_id;
    private Integer    subcategory_seq;


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
}
