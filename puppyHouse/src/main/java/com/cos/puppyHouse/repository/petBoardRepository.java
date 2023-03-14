package com.cos.puppyHouse.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cos.puppyHouse.model.PetBoard;
import com.cos.puppyHouse.model.PetBoardRoleType;


@Repository
public interface petBoardRepository extends JpaRepository<PetBoard,Integer> {
	public Page<PetBoard> findByRoles(PetBoardRoleType roles,Pageable pageable);
	
	public List<PetBoard> findByRoles(PetBoardRoleType roles);
}
