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

    public List<userCartDTO> findListById(Integer useraccount_id, Integer seqList){
        String query = "SELECT new com.bphost.principal.model.userCartDTO( " +
                       "listProd.id.useraccount_id, " + 
                       "prod.id, " +
                       "prod.name, " +
                       "prod.price, " +
                       "(SELECT img.src FROM product_img img WHERE img.id.product_id = prod.id ORDER BY img.id.seq ASC LIMIT 1), " +
                       "0.0, " +
                       "prod.name) " + 
                       "FROM userListProd listProd " +
                       "JOIN product prod ON listProd.id.product_id = product.id " + 
                       "WHERE listProd.id.useraccount_id = :userAccountId AND listProd.id.seqlist = :seqList ORDER BY listProd.create_at DESC";

        var q = em.createQuery(query, userCartDTO.class);

        q.setParameter("userAccountId", useraccount_id);
        q.setParameter("seqList", seqList);

        return q.getResultList();
    }

    public List<userCartDTO> findAllListByUser(Integer useraccount_id){
        String query = "SELECT new com.bphost.principal.model.userCartDTO( " +
                       "list.id.useraccount_id, " + 
                       "list.name, " +
                       "list.id.seq)" +
                       "FROM userList list " +
                       "WHERE list.id.useraccount_id = :userAccountId ORDER BY list.create_at DESC";

        var q = em.createQuery(query, userCartDTO.class);

        q.setParameter("userAccountId", useraccount_id);

        return q.getResultList();
    }

    public List<userCartDTO> findSomeProductByList(Integer useraccount_id, Integer seqList, Integer quantity){
        String query = "SELECT new com.bphost.principal.model.userCartDTO( " +
                       "listProd.id.useraccount_id, " + 
                       "prod.id, " +
                       "prod.name, " +
                       "prod.price, " +
                       "(SELECT img.src FROM product_img img WHERE img.id.product_id = prod.id ORDER BY img.id.seq ASC LIMIT 1), " +
                       "0.0, " +
                       "prod.name, " +
                       "listProd.id.seqlist)" +
                       "FROM userListProd listProd " +
                       "JOIN product prod ON listProd.id.product_id = product.id " + 
                       "WHERE listProd.id.useraccount_id = :userAccountId AND listProd.id.seqlist = :seqList ORDER BY listProd.create_at DESC";

        var q = em.createQuery(query, userCartDTO.class);

        q.setParameter("userAccountId", useraccount_id);
        q.setParameter("seqList", seqList);

        q.setMaxResults(quantity);

        return q.getResultList();
    }
}
