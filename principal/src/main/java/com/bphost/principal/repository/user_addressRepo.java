package com.bphost.principal.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.bphost.principal.model.user_address;
import com.bphost.principal.model.user_addressId;

public interface user_addressRepo extends JpaRepository<user_address, user_addressId> {
    @Query("SELECT address FROM user_address address WHERE address.id.useraccount_id = :user_id")
    List<user_address> findAllAddressByUser(@Param("user_id") Integer user_id);

    @Query("SELECT address FROM user_address address WHERE address.id.useraccount_id = :user_id AND address.active = true")
    user_address findAddressActiveByUser(@Param("user_id") Integer user_id);

    @Query("SELECT MAX(address.id.seq) FROM user_address address WHERE address.id.useraccount_id = :user_id")
    Integer findLastSequenceAddressByUser(@Param("user_id") Integer user_id);

    @Query("SELECT address FROM user_address address WHERE address.id.useraccount_id = :user_id AND address.id.seq = :seq")
    user_address findAddressByUser(@Param("user_id") Integer user_id, @Param("seq") Integer seq);
    
}
