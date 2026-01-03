package com.bphost.principal.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.bphost.principal.model.user_cart;
import com.bphost.principal.model.user_cartId;

public interface user_cartRepo extends JpaRepository<user_cart, user_cartId> {
    @Query("SELECT cart FROM user_cart cart WHERE cart.id.useraccount_id = :user_id AND cart.id.product_id = :prod_id AND cart.id.spec_size_id = :size_id AND cart.id.spec_color_id = :color_id")
    user_cart findCardById(@Param("user_id") Integer user_id, @Param("prod_id") Integer prod_id, @Param("size_id") Integer size_id, @Param("color_id") Integer color_id);

    @Query("SELECT cart.quantity FROM user_cart cart WHERE cart.id.useraccount_id = :user_id AND cart.id.product_id = :prod_id AND cart.id.spec_size_id = :size_id AND cart.id.spec_color_id = :color_id")
    Integer findQuantityByProdCard(@Param("user_id") Integer user_id, @Param("prod_id") Integer prod_id, @Param("size_id") Integer size_id, @Param("color_id") Integer color_id);

    @Query("SELECT cart FROM user_cart cart WHERE cart.id.useraccount_id = :user_id")
    List<user_cart> findAllByUserAccount(@Param("user_id") Integer userId);
}
