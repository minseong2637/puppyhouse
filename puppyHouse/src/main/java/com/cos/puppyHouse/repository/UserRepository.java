package com.cos.puppyHouse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.puppyHouse.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{
	Optional<Users> findByUserid(String userId);
	
	//이메일 검사
	Optional<Users> findByUsernameAndPhone(String username, String phone);
	
	Optional<Users> findByEmail(String email);

	//id 중복검사
    	Long countByUserid(String id);
	
}
