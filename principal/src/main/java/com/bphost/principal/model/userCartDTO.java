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
    private Double     avarage_rating;
    private String     nameList;
    private Integer    seqList;


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


    public userCartDTO(Integer    useraccount_id, 
                       Integer    product_id, 
                       String     name, 
                       BigDecimal price, 
                       Integer    size_id,
                       String     size, 
                       Integer    color_id, 
                       String     color, 
                       Integer    quantity, 
                       String     image) {

        this.useraccount_id = useraccount_id;
        this.product_id     = product_id;
        this.name           = name;
        this.price          = price;
        this.size_id        = size_id;
        this.size           = size;
        this.color_id       = color_id;
        this.color          = color;
        this.quantity       = quantity;
        this.image          = image;
    }

    public userCartDTO( Integer    useraccount_id,
                        Integer    product_id,
                        String     name,
                        BigDecimal price,
                        String     image,
                        Double     avarage_rating ){
        this.useraccount_id = useraccount_id;
        this.product_id     = product_id;
        this.name           = name;
        this.price          = price;
        this.image          = image;
        this.avarage_rating = avarage_rating;
    }

    public userCartDTO( Integer    useraccount_id,
                        Integer    product_id,
                        String     name,
                        BigDecimal price,
                        String     image,
                        Double     avarage_rating,
                        String     nameList ){
        this.useraccount_id = useraccount_id;
        this.product_id     = product_id;
        this.name           = name;
        this.price          = price;
        this.image          = image;
        this.avarage_rating = avarage_rating;
        this.nameList       = nameList;
    }

    public userCartDTO( Integer    useraccount_id,
                        Integer    product_id,
                        String     name,
                        BigDecimal price,
                        Integer    size_id,
                        String     size,
                        Integer    color_id,
                        String     color,
                        Integer    quantity,
                        String     image,
                        Double     avarage_rating ){
        this.useraccount_id = useraccount_id;
        this.product_id     = product_id;
        this.name           = name;
        this.price          = price;
        this.size_id        = size_id;
        this.size           = size;
        this.color_id       = color_id;
        this.color          = color;
        this.quantity       = quantity;
        this.image          = image;
        this.avarage_rating = avarage_rating;
    }

    public userCartDTO( Integer useraccount_id,
                        String  nameList,
                        Integer seqList){
        this.useraccount_id = useraccount_id;
        this.nameList       = nameList;
        this.seqList        = seqList;
    }

    public userCartDTO( Integer    useraccount_id,
                        Integer    product_id,
                        String     name,
                        BigDecimal price,
                        String     image,
                        Double     avarage_rating,
                        String     nameList,
                        Integer    seqList){
        this.useraccount_id = useraccount_id;
        this.product_id     = product_id;
        this.name           = name;
        this.price          = price;
        this.image          = image;
        this.avarage_rating = avarage_rating;
        this.nameList       = nameList;
        this.seqList        = seqList;
    }
}
