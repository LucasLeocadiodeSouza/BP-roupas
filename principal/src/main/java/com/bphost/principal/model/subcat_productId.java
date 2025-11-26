package com.bphost.principal.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class subcat_productId implements Serializable{
    @Column(name = "product_id")
    private Integer product_id ;

    @Column(name = "category_id")
    private Integer category_id;

    @Column(name = "subcategory_seq")
    private Integer subcategory_seq;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof subcat_productId)) return false;

        subcat_productId that = (subcat_productId) o;
        return Objects.equals(product_id, that.product_id) &&
               Objects.equals(category_id, that.category_id) &&
               Objects.equals(subcategory_seq, that.subcategory_seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id, category_id, subcategory_seq);
    }
}
