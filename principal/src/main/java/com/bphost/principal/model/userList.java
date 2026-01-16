package com.bphost.principal.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_list")
public class userList {
    @EmbeddedId
    private userListId id;

    @ManyToOne
    @MapsId("useraccount_id")
    @JoinColumn(name = "useraccount_id")
    private user_account userAccount;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private product product;

    @Column(length = 50)
    private String name;

    private LocalDate create_at;
}
