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
}
