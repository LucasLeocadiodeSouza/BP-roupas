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
public class comments_prodId implements Serializable{
    @Column(name = "product_id")
    private Integer product_id;

    @Column(name = "comment_id")
    private Integer comment_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof comments_prodId)) return false;
        comments_prodId that = (comments_prodId) o;
        return Objects.equals(product_id, that.product_id) && 
               Objects.equals(comment_id, that.comment_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id, comment_id);
    }
}
