package com.bphost.principal.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.bphost.principal.model.productCardDTO;
import jakarta.persistence.EntityManager;

@Repository
public class productCardDTORepo {
    private final EntityManager em;

    public productCardDTORepo(EntityManager em){
        this.em = em;
    }

    @Autowired
    private commentsProdRepo commentsprodrepo;

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

    public List<productCardDTO> findAllProductDTOByCategoryId(Integer categ_id, Integer subcateg_id){
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
        
        q.setMaxResults(size);

        if(categ_id != null && categ_id != 0) q.setParameter("categ_id", categ_id);
        if(subcateg_id != null && subcateg_id != 0) q.setParameter("subcateg_id", subcateg_id);

        return q.getResultList();
    }

    public List<productCardDTO> getProductInformation(Integer product_id){

        Integer total_comments = commentsprodrepo.countCommentsByProductId(product_id);

        Double avarage_rating = 5.0;

        if(total_comments != 0){
            avarage_rating = ((commentsprodrepo.countCommentsWithRating1ByProductId(product_id) * 1.0) +
                             (commentsprodrepo.countCommentsWithRating2ByProductId(product_id) * 2.0)  + 
                             (commentsprodrepo.countCommentsWithRating3ByProductId(product_id) * 3.0)  + 
                             (commentsprodrepo.countCommentsWithRating4ByProductId(product_id) * 4.0)  + 
                             (commentsprodrepo.countCommentsWithRating5ByProductId(product_id) * 5.0)) /
                              total_comments;
        }

        String query = "SELECT new com.bphost.principal.model.productCardDTO( " +
                       "prod.id, " +
                       "prod.name, " +
                       "prod.description, " +
                       "prod.price, " +
                       "img.src, " +
                       "prod.active, " +
                       "subcat.id.category_id, " +
                       "subcat.id.subcategory_seq, " +
                       avarage_rating.toString() + ", " +
                       total_comments.toString() + ") " +
                       "FROM product prod " +
                       "JOIN subcat_product subcat ON prod.id = subcat.id.product_id " +
                       "JOIN product_img img ON prod.id = img.id.product_id ";

        boolean hasand = false;

        if(product_id != null && product_id != 0){
            query += (hasand?" AND ":" WHERE ") + " prod.id = :product_id";
            hasand = true;
        }

        var q = em.createQuery(query, productCardDTO.class);

        if(product_id != null && product_id != 0) q.setParameter("product_id", product_id);

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
