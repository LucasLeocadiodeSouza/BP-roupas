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
public class subcategoryId implements Serializable{
    @Column(name = "category_id")
    private Integer category_id;

    @Column(name = "seq")
    private Integer seq;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof subcategoryId)) return false;
        subcategoryId that = (subcategoryId) o;
        return Objects.equals(category_id, that.category_id) &&
               Objects.equals(seq, that.seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category_id, seq);
    }
}
