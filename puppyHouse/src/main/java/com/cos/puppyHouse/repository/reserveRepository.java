package com.cos.puppyHouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.puppyHouse.model.Pet;
import com.cos.puppyHouse.model.Reserv;
import com.cos.puppyHouse.model.Users;

public interface reserveRepository extends JpaRepository<Reserv, Integer> {
	//날짜 중복 제거
	@Query(value="select distinct reservdate from Reserv")
	List<String> findDistinctAll();
	
	//강아지 이름 정보 중복 제거 + 아이디 불러오기
	@Query(value="select distinct pet from Reserv")
	List<Pet> findDistinctAll2();

	List<Reserv> findByReservdateAndTeacher(String reservdate, Users teacher);
}
