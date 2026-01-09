package com.bphost.principal.repository;

import java.math.BigDecimal;
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

    public List<productCardDTO> getProductsWithMostComments(Integer categ_id, Integer subcateg_id, Integer size, Integer page){
        String query = "SELECT new com.bphost.principal.model.productCardDTO( " +
                       "prod.id, " +
                       "prod.name, " +
                       "prod.price, " +
                       "(SELECT img.src FROM product_img img WHERE img.id.product_id = prod.id ORDER BY img.id.seq ASC LIMIT 1), " +
                       "subcat.id.category_id, " +
                       "subcat.id.subcategory_seq, " +
                       "COUNT(comments)) " + 
                       "FROM product prod " +
                       "JOIN subcat_product subcat ON prod.id = subcat.id.product_id " +
                       "JOIN comments_prod comments ON prod.id = comments.id.product_id ";

        boolean hasand = false;

        if(categ_id != null && categ_id != 0){
            query += (hasand?" AND ":" WHERE ") + " subcat.id.category_id = :categ_id";
            hasand = true;
        }

        if(subcateg_id != null && subcateg_id != 0){
            query += (hasand?" AND ":" WHERE ") + " subcat.id.subcategory_seq = :subcateg_id";
            hasand = true;
        }

        query += " GROUP BY prod.id, prod.name, prod.price, subcat.id.category_id, subcat.id.subcategory_seq ORDER BY COUNT(comments) DESC ";

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

    public List<productCardDTO> getBestSellingProducts(Integer categ_id, Integer subcateg_id, Integer size, Integer page){
        String query = "SELECT new com.bphost.principal.model.productCardDTO( " +
                       "prod.id, " +
                       "prod.name, " +
                       "prod.price, " +
                       "(SELECT img.src FROM product_img img WHERE img.id.product_id = prod.id ORDER BY img.id.seq ASC LIMIT 1), " +
                       "subcat.id.category_id, " +
                       "subcat.id.subcategory_seq, " +
                       "COUNT(purchases)) " + 
                       "FROM product prod " +
                       "JOIN subcat_product subcat ON prod.id = subcat.id.product_id " +
                       "JOIN userPurchases purchases ON prod.id = purchases.id.product_id ";

        boolean hasand = false;

        if(categ_id != null && categ_id != 0){
            query += (hasand?" AND ":" WHERE ") + " subcat.id.category_id = :categ_id";
            hasand = true;
        }

        if(subcateg_id != null && subcateg_id != 0){
            query += (hasand?" AND ":" WHERE ") + " subcat.id.subcategory_seq = :subcateg_id";
            hasand = true;
        }

        query += " GROUP BY prod.id, prod.name, prod.price, subcat.id.category_id, subcat.id.subcategory_seq ORDER BY COUNT(purchases) DESC ";

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

    public List<productCardDTO> getStartingFromAPrice(BigDecimal price, Integer category_id, Integer subcategory_id, Integer size, Integer page){
        String query = "SELECT new com.bphost.principal.model.productCardDTO( " +
                       "prod.id, " +
                       "prod.name, " +
                       "prod.price, " +
                       "(SELECT img.src FROM product_img img WHERE img.id.product_id = prod.id ORDER BY img.id.seq ASC LIMIT 1), " +
                       "subcat.id.category_id, " +
                       "subcat.id.subcategory_seq) " +
                       "FROM product prod " +
                       "JOIN subcat_product subcat ON prod.id = subcat.id.product_id " + 
                       "WHERE prod.price <= " + price;

        if(category_id != null && category_id != 0) query += " AND  subcat.id.category_id = :category_id";

        if(subcategory_id != null && subcategory_id != 0) query += " AND subcat.id.subcategory_seq = :subcategory_id";

        query += " ORDER BY prod.price ASC";

        var q = em.createQuery(query, productCardDTO.class);
        
        if(page != null) {
            int offset = page * size;
            q.setFirstResult(offset);
            q.setMaxResults(size);
        } else q.setMaxResults(size);

        if(category_id != null && category_id != 0) q.setParameter("category_id", category_id);
        if(subcategory_id != null && subcategory_id != 0) q.setParameter("subcategory_id", subcategory_id);

        return q.getResultList();
    }

    public List<productCardDTO> getProductsWithDiscount(Integer category_id, Integer subcategory_id, Integer size, Integer page){
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

        if(category_id != null && category_id != 0){
            query += (hasand?" AND ":" WHERE ") + " subcat.id.category_id = :category_id";
            hasand = true;
        }

        if(subcategory_id != null && subcategory_id != 0){
            query += (hasand?" AND ":" WHERE ") + " subcat.id.subcategory_seq = :subcategory_id";
            hasand = true;
        }

        query += " ORDER BY prod.price ASC";

        var q = em.createQuery(query, productCardDTO.class);
        
        if(page != null) {
            int offset = page * size;
            q.setFirstResult(offset);
            q.setMaxResults(size);
        } else q.setMaxResults(size);

        if(category_id != null && category_id != 0) q.setParameter("category_id", category_id);
        if(subcategory_id != null && subcategory_id != 0) q.setParameter("subcategory_id", subcategory_id);

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
                       "JOIN subcat_product subcat ON prod.id = subcat.id.product_id " +
                       "ORDER BY prod.id DESC";

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

    public List<productCardDTO> searchProducts(String text, Integer page, Integer size){
        String query = "SELECT new com.bphost.principal.model.productCardDTO( " +
                       "prod.id, " +
                       "prod.name, " +
                       "prod.price, " +
                       "(SELECT img.src FROM product_img img WHERE img.id.product_id = prod.id ORDER BY img.id.seq ASC LIMIT 1), " +
                       "subcat.id.category_id, " +
                       "subcat.id.subcategory_seq) " +
                       "FROM product prod " +
                       "JOIN subcat_product subcat ON prod.id = subcat.id.product_id " +
                       "WHERE LOWER(prod.name) LIKE LOWER(:searchText) " +
                       "OR LOWER(prod.description) LIKE LOWER(:searchText) ";

        var q = em.createQuery(query, productCardDTO.class);
        
        q.setParameter("searchText", "%" + text + "%");
        
        if(page != null) {
            int offset = page * size;
            q.setFirstResult(offset);
            q.setMaxResults(size);
        } else q.setMaxResults(size);
        
        return q.getResultList();
    }
}
