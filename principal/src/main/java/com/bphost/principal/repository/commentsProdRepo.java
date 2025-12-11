package com.bphost.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bphost.principal.model.comments_prod;
import com.bphost.principal.model.comments_prodId;

public interface commentsProdRepo extends JpaRepository<comments_prod, comments_prodId> {
    @Query("SELECT COUNT(cp) FROM comments_prod cp WHERE cp.id.product_id = :product_id")
    Integer countCommentsByProductId(Integer product_id);

    @Query("SELECT COUNT(cp) FROM comments_prod cp WHERE cp.id.product_id = :product_id AND cp.comments.rating = 1")
    Integer countCommentsWithRating1ByProductId(Integer product_id);

    @Query("SELECT COUNT(cp) FROM comments_prod cp WHERE cp.id.product_id = :product_id AND cp.comments.rating = 2")
    Integer countCommentsWithRating2ByProductId(Integer product_id);

    @Query("SELECT COUNT(cp) FROM comments_prod cp WHERE cp.id.product_id = :product_id AND cp.comments.rating = 3")
    Integer countCommentsWithRating3ByProductId(Integer product_id);

    @Query("SELECT COUNT(cp) FROM comments_prod cp WHERE cp.id.product_id = :product_id AND cp.comments.rating = 4")
    Integer countCommentsWithRating4ByProductId(Integer product_id);

    @Query("SELECT COUNT(cp) FROM comments_prod cp WHERE cp.id.product_id = :product_id AND cp.comments.rating = 5")
    Integer countCommentsWithRating5ByProductId(Integer product_id);
}
    