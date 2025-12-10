package com.bphost.principal.model;

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
@Table(name = "comments_prod")
public class comments_prod {
    @EmbeddedId
    private comments_prodId id;

    @ManyToOne
    @MapsId("comment_id")
    @JoinColumn(name = "comment_id")
    private comments comments;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private product product;
}
