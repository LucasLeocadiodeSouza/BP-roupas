package com.bphost.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bphost.principal.model.userList;
import com.bphost.principal.model.userListId;

@Repository
public interface userListRepo extends JpaRepository<userList, userListId>{
    @Query("SELECT list FROM userList list WHERE list.id.useraccount_id = :userId AND list.id.seq = :seq AND list.id.product_id = :product_id")
    userList findUserListById(@Param("userId") Integer userId, @Param("seq") Integer seq, @Param("product_id") Integer product_id);
}
