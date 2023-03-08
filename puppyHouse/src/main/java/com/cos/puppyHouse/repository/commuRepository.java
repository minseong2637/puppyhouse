package com.cos.puppyHouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.puppyHouse.model.Community;

public interface commuRepository extends JpaRepository<Community,Integer>{
	
	//조회수 추가
	@Modifying
	@Query("update Community commu set commu.count = commu.count + 1 where commu.commuId = :id")
	void updateCount(int id);
	
	
}
