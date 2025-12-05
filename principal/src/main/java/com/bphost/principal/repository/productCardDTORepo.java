package com.bphost.principal.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.bphost.principal.model.productCardDTO;
import jakarta.persistence.EntityManager;

@Repository
public class productCardDTORepo {
    private final EntityManager em;

    public productCardDTORepo(EntityManager em){
        this.em = em;
    }

    public List<productCardDTO> findAllProductDTOByCategoryId(Integer categ_id, Integer subcateg_id, Integer page){
        String query = "SELECT new com.bphost.principal.model.productCardDTO( " +
                       "prod.id, " +
                       "prod.name, " +
                       "prod.price, " +
                       "(SELECT img.src FROM product_img img WHERE img.id.product_id = prod.id ORDER BY img.id.seq ASC LIMIT 1), " +
                       "subcat.id.category_id, " +
                       "subcat.id.subcategory_seq) " +
                       "FROM product prod " +
                       "JOIN subcat_product subcat ON prod.id = subcat.id.product_id ";

        boolean hasand = false;
        Integer size   = 30;

        if(categ_id != null && categ_id != 0){
            query += (hasand?" AND ":" WHERE ") + " subcat.id.category_id = :categ_id";
            hasand = true;
        }

        if(subcateg_id != null && subcateg_id != 0){
            query += (hasand?" AND ":" WHERE ") + " subcat.id.subcategory_seq = :subcateg_id";
            hasand = true;
        }

        var q = em.createQuery(query, productCardDTO.class);
        
        if(page != null) {
            int offset = page * size;
            q.setFirstResult(offset);
            q.setMaxResults(size);
        } else q.setMaxResults(size);
    

        if(categ_id != null && categ_id != 0) q.setParameter("categ_id", categ_id);
        if(subcateg_id != null && subcateg_id != 0) q.setParameter("subcateg_id", subcateg_id);

        return q.getResultList();
    }

    public List<productCardDTO> getBestSellingProducts(Integer categ_id, Integer subcateg_id){
        String query = "SELECT new com.bphost.principal.model.productCardDTO( " +
                       "prod.id, " +
                       "prod.name, " +
                       "prod.price, " +
                       "(SELECT img.src FROM product_img img WHERE img.id.product_id = prod.id ORDER BY img.id.seq ASC LIMIT 1), " +
                       "subcat.id.category_id, " +
                       "subcat.id.subcategory_seq) " +
                       "FROM product prod " +
                       "JOIN subcat_product subcat ON prod.id = subcat.id.product_id ";

        boolean hasand = false;
        Integer size   = 12;

        if(categ_id != null && categ_id != 0){
            query += (hasand?" AND ":" WHERE ") + " subcat.id.category_id = :categ_id";
            hasand = true;
        }

        if(subcateg_id != null && subcateg_id != 0){
            query += (hasand?" AND ":" WHERE ") + " subcat.id.subcategory_seq = :subcateg_id";
            hasand = true;
        }

        var q = em.createQuery(query, productCardDTO.class);
        
        q.setMaxResults(size);

        if(categ_id != null && categ_id != 0) q.setParameter("categ_id", categ_id);
        if(subcateg_id != null && subcateg_id != 0) q.setParameter("subcateg_id", subcateg_id);

        return q.getResultList();
    }

    public List<productCardDTO> getNewDiscovery(){
        String query = "SELECT new com.bphost.principal.model.productCardDTO( " +
                       "prod.id, " +
                       "prod.name, " +
                       "prod.price, " +
                       "(SELECT img.src FROM product_img img WHERE img.id.product_id = prod.id ORDER BY img.id.seq ASC LIMIT 1), " +
                       "subcat.id.category_id, " +
                       "subcat.id.subcategory_seq) " +
                       "FROM product prod " +
                       "JOIN subcat_product subcat ON prod.id = subcat.id.product_id ";

        Integer size = 4;

        var q = em.createQuery(query, productCardDTO.class);

        q.setMaxResults(size);

        return q.getResultList();
    }

    public List<productCardDTO> getBestRatedDeals(){
        String query = "SELECT new com.bphost.principal.model.productCardDTO( " +
                       "prod.id, " +
                       "prod.name, " +
                       "prod.price, " +
                       "(SELECT img.src FROM product_img img WHERE img.id.product_id = prod.id ORDER BY img.id.seq ASC LIMIT 1), " +
                       "subcat.id.category_id, " +
                       "subcat.id.subcategory_seq) " +
                       "FROM product prod " +
                       "JOIN subcat_product subcat ON prod.id = subcat.id.product_id ";
                       
        Integer size = 4;

        var q = em.createQuery(query, productCardDTO.class);
        
        q.setMaxResults(size);

        return q.getResultList();
    }

    public List<productCardDTO> getHouseRecommendations(){
        String query = "SELECT new com.bphost.principal.model.productCardDTO( " +
                       "prod.id, " +
                       "prod.name, " +
                       "prod.price, " +
                       "(SELECT img.src FROM product_img img WHERE img.id.product_id = prod.id ORDER BY img.id.seq ASC LIMIT 1), " +
                       "subcat.id.category_id, " +
                       "subcat.id.subcategory_seq) " +
                       "FROM product prod " +
                       "JOIN subcat_product subcat ON prod.id = subcat.id.product_id ";
                       
        Integer size = 4;

        var q = em.createQuery(query, productCardDTO.class);
        
        q.setMaxResults(size);

        return q.getResultList();
    }
}
