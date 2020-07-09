package com.subg.dao;

import com.subg.model.Address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

 
public interface AddressDaoRepository extends JpaRepository<Address,Long> {

    
    @Query("from Address where userId = :userId")
    List<Address> getUserAddress(long userId);

}