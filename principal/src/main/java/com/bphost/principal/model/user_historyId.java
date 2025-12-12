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
public class user_historyId implements Serializable{
    @Column(name = "useraccount_id")
    private Integer useraccount_id;

    @Column(name = "product_id")
    private Integer product_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof user_historyId)) return false;
        user_historyId that = (user_historyId) o;
        return Objects.equals(useraccount_id, that.useraccount_id) && 
               Objects.equals(product_id, that.product_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(useraccount_id, product_id);
    }
}
