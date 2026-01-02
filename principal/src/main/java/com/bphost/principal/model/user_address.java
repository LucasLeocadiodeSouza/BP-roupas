package com.bphost.principal.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_address")
public class user_address {
    @EmbeddedId
    private user_addressId id;

    @Column(length = 100)
    private String street;

    @Column(length = 100)
    private String neighborhood;

    @Column(length = 5)
    private String number;

    @Column(length = 8)
    private String CEP;

    @Column(length = 50)
    private String city;

    @Column(length = 50)
    private String state;

    @Column(length = 45)
    private String country;

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean active;
}
