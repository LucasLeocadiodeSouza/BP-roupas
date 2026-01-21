package com.bphost.principal.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_purchases")
public class userPurchases {
    @EmbeddedId
    private userPurchasesId id;

    @ManyToOne
    @MapsId("useraccount_id")
    @JoinColumn(name = "useraccount_id")
    private user_account userAccount;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private product product;
    
    @ManyToOne
    @MapsId("spec_size_id")
    @JoinColumn(name = "spec_size_id")
    private specification_size spec_size;
    
    @ManyToOne
    @MapsId("spec_color_id")
    @JoinColumn(name = "spec_color_id")
    private specification_color spec_color;

    private Integer    status;
    private Integer    quantity;
    private BigDecimal discount;
    private LocalDate  create_at;
    private LocalDate  delivered_at;
}
