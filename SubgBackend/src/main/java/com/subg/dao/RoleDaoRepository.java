package com.subg.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subg.model.Role;
import com.subg.model.RoleName;

public interface RoleDaoRepository extends JpaRepository<Role , Long>{

	Optional<Role> findByName(RoleName roleName);
}
