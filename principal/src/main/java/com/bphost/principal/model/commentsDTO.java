package com.bphost.principal.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class commentsDTO {
    private Integer   comment_id;
    private Integer   product_id;
    private String    user_name;
    private String    description;
    private Integer   rating;
    private LocalDate created_at;
    private Double    average_rating;
    private Integer   total_comments;


    public commentsDTO(Integer comment_id, 
                       Integer product_id, 
                       String user_name, 
                       String description, 
                       Integer rating,
                       LocalDate created_at) {
        
        this.comment_id  = comment_id;
        this.product_id  = product_id;
        this.user_name   = user_name;
        this.description = description;
        this.rating      = rating;
        this.created_at  = created_at;
    }

    
}
