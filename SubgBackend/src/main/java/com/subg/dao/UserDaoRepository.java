package com.subg.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.subg.model.Users;

public interface UserDaoRepository  extends JpaRepository<Users, Long> {
	
	@Query("from Users where email = :email")
	Optional<Users> findByEmail(String email);
	    Boolean existsByEmail(String email);
	    
	    
	    @Query("from Users where email = :email")
		Users findByResetEmail(String email);
	    
	    @Modifying
	    @Query("update Users u set u.password = :password where u.id = :id")
	    void updatePassword(@Param("password") String password, @Param("id") Long id);
}
