package com.bphost.principal.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.bphost.principal.model.userCartDTO;
import jakarta.persistence.EntityManager;

@Repository
public class userDTORepo{
    private final EntityManager em;

    public userDTORepo(EntityManager em){
        this.em = em;
    }

    public List<userCartDTO> findAllHistortyByUser(Integer useraccount_id){
        String query = "SELECT new com.bphost.principal.model.userCartDTO( " +
                       "hist.id.useraccount_id, " + 
                       "prod.id, " +
                       "prod.name, " +
                       "prod.price, " +
                       "(SELECT img.src FROM product_img img WHERE img.id.product_id = prod.id ORDER BY img.id.seq ASC LIMIT 1), " +
                       "0.0)" +
                       "FROM user_history hist " +
                       "JOIN product prod ON hist.id.product_id = product.id " + 
                       "WHERE hist.id.useraccount_id = :userAccountId ORDER BY hist.update_at DESC";

        var q = em.createQuery(query, userCartDTO.class);

        q.setParameter("userAccountId", useraccount_id);

        return q.getResultList();
    }
}
