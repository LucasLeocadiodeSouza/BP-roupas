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
public class user_addressId implements Serializable{
    @Column(name = "useraccount_id")
    private Integer useraccount_id;

    @Column(name = "seq")
    private Integer seq;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof user_addressId)) return false;
        user_addressId that = (user_addressId) o;
        return Objects.equals(useraccount_id, that.useraccount_id) && 
               Objects.equals(seq, that.seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(useraccount_id, seq);
    }
}
