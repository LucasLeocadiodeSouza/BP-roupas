package com.bphost.principal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class userDTO {
    private String  username;
    private String  telephone;
    private String  email;
    private Integer addressSeq;
    private String  street;
    private String  number;
    private String  neighborhood;
    private String  cep;
    private String  city;
    private String  state;
    private String  country;


    public userDTO(String username, String telephone, String email) {
        this.username  = username;
        this.telephone = telephone;
        this.email     = email;
    }

    
}
