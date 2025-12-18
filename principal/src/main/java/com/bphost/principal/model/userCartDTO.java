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
public class userCartDTO {
    private Integer    useraccount_id;
    private Integer    product_id;
    private String     name;
    private BigDecimal price;
    private Integer    size_id;
    private String     size;
    private Integer    color_id;
    private String     color;
    private Integer    quantity;
    private String     image;


    public userCartDTO(Integer    useraccount_id, 
                       Integer    product_id, 
                       String     name, 
                       BigDecimal price,
                       Integer    size_id,
                       Integer    color_id, 
                       Integer    quantity, 
                       String     image) {
        
        this.useraccount_id = useraccount_id;
        this.product_id     = product_id;
        this.name           = name;
        this.price          = price;
        this.size_id        = size_id;
        this.color_id       = color_id;
        this.quantity       = quantity;
        this.image          = image;
    }

    
}
