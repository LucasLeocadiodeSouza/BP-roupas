package com.bphost.principal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class categoryCardDTO {
    private Integer category_id;
    private String  category_name;
    private String  category_img;
    private Integer subcategory_seq;
    private String  subcategory_name;
    private String  subcategory_img;

    public categoryCardDTO(Integer category_id, 
                           String  category_name, 
                           Integer subcategory_seq,
                           String  subcategory_name) {

        this.category_id      = category_id;
        this.category_name    = category_name;
        this.subcategory_seq  = subcategory_seq;
        this.subcategory_name = subcategory_name;
    }
}
