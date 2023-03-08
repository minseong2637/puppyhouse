package com.cos.puppyHouse.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cos.puppyHouse.model.NoticeFAQ;
import com.cos.puppyHouse.model.NoticeRoleType;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeFAQ,Integer>{

	//@Modifying
	//@Query("SELECT * FROM Notice WHERE ROLES=? ORDER BY noticeid DESC")
	public Page<NoticeFAQ> findByRoles(NoticeRoleType roles,Pageable pageable);
	
}