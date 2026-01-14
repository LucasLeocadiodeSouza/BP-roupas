package com.bphost.principal.model;

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
public class userListId {
    @Column(name = "useraccount_id")
    private Integer useraccount_id;

    @Column(name = "seq")
    private Integer seq;

    @Column(name = "product_id")
    private Integer product_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof userListId)) return false;
        userListId that = (userListId) o;
        return Objects.equals(useraccount_id, that.useraccount_id) && 
               Objects.equals(seq, that.seq) && 
               Objects.equals(product_id, that.product_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(useraccount_id, seq, product_id);
    }
}
