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
public class specification_color_prodId implements Serializable {
    @Column(name = "product_id")
    private Integer product_id;

    @Column(name = "seq")
    private Integer seq;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof specification_color_prodId)) return false;
        specification_color_prodId that = (specification_color_prodId) o;
        return Objects.equals(product_id, that.product_id) &&
               Objects.equals(seq, that.seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id, seq);
    }
}
