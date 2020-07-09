package com.subg.servicedao;

import java.util.List;
import java.util.Optional;

import com.subg.model.Users;

public interface UserServiceDao {

	Users addUser(Users user);
	Users updateUser(Users user);
	List<Users> getUsersList();
	Optional<Users> getUser(Long userId);
	void deleteUser(Long userId);
	Optional<Users> findByEmail(String email);
    void updatePassword(String password, Long userId);
    Users findByResetEmail(String email);
}
