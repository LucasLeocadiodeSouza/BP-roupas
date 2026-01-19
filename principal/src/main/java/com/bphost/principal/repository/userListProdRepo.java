package com.bphost.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bphost.principal.model.userListProd;
import com.bphost.principal.model.userListProdId;

@Repository
public interface userListProdRepo extends JpaRepository<userListProd, userListProdId>{
    @Query("SELECT list FROM userListProd list WHERE list.id.useraccount_id = :userId AND list.id.seqlist = :seq AND list.id.product_id = :product_id")
    userListProd findUserListProdById(@Param("userId") Integer userId, @Param("seq") Integer seq, @Param("product_id") Integer product_id);
}