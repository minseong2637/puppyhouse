package com.cos.puppyHouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cos.puppyHouse.model.Likes;

public interface likesRepository extends JpaRepository<Likes, Integer>{
	
	//좋아요
	@Modifying
	@Query("update Community commu set commu.likescount = commu.likescount + 1 where commu.commuId = :id")
	void plusLikesCount(int id);
	
	//좋아요 취소
	@Modifying
	@Query("update Community commu set commu.likescount = commu.likescount - 1 where commu.commuId = :id")
	void minusLikesCount(int id);
	
	//likes 테이블에 정보 저장
	@Modifying
	@Query(value = "INSERT INTO likes(id, CommunityId, UsersId) VALUES(USER_SEQ7.nextval, :CommunityId, :UsersId)", nativeQuery = true)
	void insertLikes(@Param("CommunityId") int commuid, @Param("UsersId") int userid);
}
