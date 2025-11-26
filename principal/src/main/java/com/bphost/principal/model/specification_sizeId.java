package com.bphost.principal.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class specification_sizeId implements Serializable{
    @Column(name = "product_id")
    private Integer product_id;

    @Column(name = "seq")
    private Integer seq;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof specification_sizeId)) return false;
        specification_sizeId that = (specification_sizeId) o;
        return Objects.equals(product_id, that.product_id) &&
               Objects.equals(seq, that.seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id, seq);
    }
}
