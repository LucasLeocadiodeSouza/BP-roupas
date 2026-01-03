package com.bphost.principal.model;

import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class userPurchasesId {
    @Column(name = "useraccount_id")
    private Integer useraccount_id ;

    @Column(name = "product_id")
    private Integer product_id ;

    @Column(name = "spec_size_id")
    private Integer spec_size_id;

    @Column(name = "spec_color_id")
    private Integer spec_color_id;

    @Column(name = "sequence")
    private Integer sequence;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof userPurchasesId)) return false;

        userPurchasesId that = (userPurchasesId) o;
        return Objects.equals(useraccount_id, that.useraccount_id) &&
               Objects.equals(product_id, that.product_id) &&
               Objects.equals(spec_size_id, that.spec_size_id) &&
               Objects.equals(spec_color_id, that.spec_color_id) &&
               Objects.equals(sequence, that.sequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(useraccount_id, product_id, spec_size_id, spec_color_id, sequence);
    }
}
