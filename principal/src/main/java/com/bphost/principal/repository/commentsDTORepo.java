package com.bphost.principal.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.bphost.principal.model.commentsDTO;
import jakarta.persistence.EntityManager;

@Repository
public class commentsDTORepo {
    private final EntityManager em;

    public commentsDTORepo(EntityManager em){
        this.em = em;
    }

    public List<commentsDTO> findCommentsByProduct(Integer product_id, Integer rating, Integer page){
        String query = "SELECT new com.bphost.principal.model.commentsDTO( " +
                       "cp.id.comment_id, " +
                       "cp.id.product_id, " +
                       "null, " +
                       "c.description, " +
                       "c.rating, " +
                       "c.created_at) " +
                       "FROM comments_prod cp " +
                       "JOIN comments c ON cp.id.comment_id = c.id " +
                       //"JOIN comments_user cu ON cu.id.comment_id = c.id " +
                       "WHERE cp.id.product_id = :product_id";
        
        int size = 7;

        if(rating != null && rating != 0) query += " AND c.rating = :rating ";

        query += " ORDER BY c.created_at DESC ";

        var q = em.createQuery(query, commentsDTO.class);
    
        if(page != null) {
            int offset = page * size;
            q.setFirstResult(offset);
            q.setMaxResults(size);
        } else q.setMaxResults(size);

        q.setParameter("product_id", product_id);

        if(rating != null && rating != 0) q.setParameter("rating", rating);

        return q.getResultList();
    }
}
