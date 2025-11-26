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
@Table(name = "subcategory")
public class subcategory {
    @EmbeddedId
    private subcategoryId id;

    @ManyToOne
    @MapsId("category_id")
    @JoinColumn(name = "category_id")
    private category category;

    @Column(length = 40)
    private String name;

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean active;
}
