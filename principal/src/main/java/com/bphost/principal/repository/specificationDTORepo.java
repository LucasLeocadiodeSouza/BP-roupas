package com.bphost.principal.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bphost.principal.model.categoryCardDTO;
import com.bphost.principal.model.specificationDTO;
import com.bphost.principal.model.specificationDTO2;

import jakarta.persistence.EntityManager;

@Repository
public class specificationDTORepo {
    private final EntityManager em;

    public specificationDTORepo(EntityManager em){
        this.em = em;
    }

    public List<specificationDTO> findAllSpecificationColorByProduct(Integer product_id, Integer size_id){
        String query = "SELECT DISTINCT new com.bphost.principal.model.specificationDTO(" +
                       "specprod.id.product_id, " +
                       "specprod.id.size_id, " +
                       "null, " +
                       "sc.id, " +
                       "sc.color, " +
                       "specprod.storage) " +
                       "FROM specification_prod specprod " +
                       "JOIN specification_color sc ON specprod.id.color_id = sc.id " +
                       "WHERE specprod.id.product_id = :product_id ";

        if(product_id != null && product_id != 0) query += " AND specprod.id.product_id = :product_id";
        if(size_id != null && size_id != 0) query += " AND specprod.id.size_id = :size_id";

        var q = em.createQuery(query, specificationDTO.class);
    
        if(product_id != null && product_id != 0) q.setParameter("product_id", product_id);
        if(size_id != null && size_id != 0) q.setParameter("size_id", size_id);

        return q.getResultList();
    }

    public List<specificationDTO> findAllSpecificationSizeByProduct(Integer product_id){
        String query = "SELECT new com.bphost.principal.model.specificationDTO( " +
                       "specprod.id.product_id, " +
                       "specprod.id.size_id, " +
                       "specsize.size, " +
                       "specprod.storage) " +
                       "FROM specification_prod specprod " +
                       "JOIN specification_size  specsize ON specprod.id.size_id = specsize.id ";

        boolean hasand = false;

        if(product_id != null && product_id != 0){
            query += (hasand?" AND ":" WHERE ") + " specprod.id.product_id = :product_id";
            hasand = true;
        }

        var q = em.createQuery(query, specificationDTO.class);
    
        if(product_id != null && product_id != 0) q.setParameter("product_id", product_id);

        return q.getResultList();
    }

    public List<specificationDTO> findAllSpecificationSizeByCategory(Integer category){
        String query = "SELECT new com.bphost.principal.model.specificationDTO( " +
                       "specsize.id, " +
                       "specsize.size) " +
                       "FROM specification_size specsize ";

        if(category != null && category != 0) query += " WHERE specsize.category.id = :categ_id";

        var q = em.createQuery(query, specificationDTO.class);
    
        if(category != null && category != 0) q.setParameter("categ_id", category);

        return q.getResultList();
    }

    public List<specificationDTO2> findAllByProductIdAndSize(Integer product_id, Integer size_id){
        String query = "SELECT new com.bphost.principal.model.specificationDTO2( " +
                       "spec.id.color_id, " +
                       "spec.storage) " +
                       "FROM specification_prod spec " +
                       "WHERE spec.id.product_id = :product_id AND spec.id.size_id = :size_id";

        var q = em.createQuery(query, specificationDTO2.class);
    
        if(product_id != null && product_id != 0) q.setParameter("product_id", product_id);
        if(size_id != null && size_id != 0) q.setParameter("size_id", size_id);

        return q.getResultList();
    }

 
}
