package com.bphost.principal.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.bphost.principal.model.categoryCardDTO;
import jakarta.persistence.EntityManager;

@Repository
public class categoryCardDTORepo {
    private final EntityManager em;

    public categoryCardDTORepo(EntityManager em){
        this.em = em;
    }

    public List<categoryCardDTO> findAllActivesSubcategories(Integer category_id){
        String query = "SELECT new com.bphost.principal.model.categoryCardDTO( " +
                       "categ.id, " +
                       "categ.name, " +
                       "categ.image, " +
                       "subcateg.id.seq, " +
                       "subcateg.name, " +
                       "subcateg.image) " +
                       "FROM subcategory subcateg " +
                       "JOIN category categ ON subcateg.id.category_id = categ.id ";

        boolean hasand = false;

        if(category_id != null && category_id != 0){
            query += (hasand?" AND ":" WHERE ") + " subcateg.id.category_id = :category_id";
            hasand = true;
        }

        var q = em.createQuery(query, categoryCardDTO.class);
    
        if(category_id != null && category_id != 0) q.setParameter("category_id", category_id);

        return q.getResultList();
    }
}
