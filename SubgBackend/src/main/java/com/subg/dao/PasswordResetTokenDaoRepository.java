package com.subg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subg.model.PasswordResetToken;

public interface PasswordResetTokenDaoRepository extends JpaRepository<PasswordResetToken, Long>{
	 PasswordResetToken findByToken(String token);
}
