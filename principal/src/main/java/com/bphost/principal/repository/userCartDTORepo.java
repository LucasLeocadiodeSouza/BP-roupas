package com.bphost.principal.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.bphost.principal.model.userCartDTO;
import jakarta.persistence.EntityManager;

@Repository
public class userCartDTORepo {

    private final EntityManager em;

    public userCartDTORepo(EntityManager em){
        this.em = em;
    }

    public List<userCartDTO> findAllActivesSubcategories(Integer useraccount_id){
        String query = "SELECT new com.bphost.principal.model.userCartDTO( " +
                       "c.id.useraccount_id, " + 
                       "prod.id, " +
                       "prod.name, " +
                       "prod.price, " +
                       "c.id.spec_size_id, " +
                       "specsize.size, " +
                       "c.id.spec_color_id, " +
                       "sc.color, " +
                       "c.quantity, " +
                       "(SELECT img.src FROM product_img img WHERE img.id.product_id = prod.id ORDER BY img.id.seq ASC LIMIT 1)) " +
                       "FROM user_cart c " +
                       "JOIN product prod ON c.id.product_id = product.id " + 
                       "JOIN specification_color sc ON c.id.spec_color_id = sc.id " +
                       "JOIN specification_size specsize ON c.id.spec_size_id = specsize.id " +
                       "WHERE c.id.useraccount_id = :userAccountId ";

        var q = em.createQuery(query, userCartDTO.class);
    
        q.setParameter("userAccountId", useraccount_id);

        return q.getResultList();
    }
}
