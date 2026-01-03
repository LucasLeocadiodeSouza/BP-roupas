package com.bphost.principal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.bphost.principal.model.userPurchases;
import com.bphost.principal.model.userPurchasesId;

public interface userPurchasesRepo extends JpaRepository<userPurchases, userPurchasesId> {
    @Query("SELECT MAX(purchase.id.sequence) FROM userPurchases purchase WHERE purchase.id.useraccount_id = :user_id AND purchase.id.product_id = :prod_id AND purchase.id.spec_size_id = :size_id AND purchase.id.spec_color_id = :color_id")
    Integer findLastSequencePurchaseById(@Param("user_id") Integer user_id, @Param("prod_id") Integer prod_id, @Param("size_id") Integer size_id, @Param("color_id") Integer color_id);
    
    @Query("SELECT purchase FROM userPurchases purchase WHERE purchase.id.useraccount_id = :user_id AND purchase.id.product_id = :prod_id AND purchase.id.spec_size_id = :size_id AND purchase.id.spec_color_id = :color_id AND purchase.id.sequence = :sequence")
    userPurchases findPurchaseById(@Param("user_id") Integer user_id, @Param("prod_id") Integer prod_id, @Param("size_id") Integer size_id, @Param("color_id") Integer color_id, @Param("sequence") Integer sequence);

    @Query("SELECT purchase FROM userPurchases purchase WHERE purchase.id.useraccount_id = :user_id")
    List<userPurchases> findAllPurchaseByUser(@Param("user_id") Integer user_id);

    @Query("SELECT purchase FROM userPurchases purchase WHERE purchase.id.useraccount_id = :user_id AND purchase.status = 1")
    List<userPurchases> findAllPreparingPurchaseByUser(@Param("user_id") Integer user_id);

    @Query("SELECT purchase FROM userPurchases purchase WHERE purchase.id.useraccount_id = :user_id AND purchase.status = 2")
    List<userPurchases> findAllWaitingPurchaseByUser(@Param("user_id") Integer user_id);

    @Query("SELECT purchase FROM userPurchases purchase WHERE purchase.id.useraccount_id = :user_id AND purchase.status = 3")
    List<userPurchases> findAllDeliveredPurchaseByUser(@Param("user_id") Integer user_id);
}
