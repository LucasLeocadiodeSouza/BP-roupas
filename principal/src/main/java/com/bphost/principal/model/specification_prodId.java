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
public class specification_prodId implements Serializable{
    @Column(name = "product_id")
    private Integer product_id;

    @Column(name = "size_id")
    private Integer size_id;

    @Column(name = "color_id")
    private Integer color_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof specification_prodId)) return false;
        specification_prodId that = (specification_prodId) o;
        return Objects.equals(product_id, that.product_id) &&
               Objects.equals(size_id, that.size_id)     &&
               Objects.equals(color_id, that.color_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id, size_id, color_id);
    }
}
