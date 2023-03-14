package com.cos.puppyHouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.puppyHouse.model.Reply;

public interface replyRepository extends JpaRepository<Reply,Integer> {
	
	//댓글 수 증가
	@Modifying
	@Query("update Community commu set commu.replycount = commu.replycount + 1 where commu.commuId = :id")
	void plusReplyCount(int id);
	
	//댓글 수 감소
	@Modifying
	@Query("update Community commu set commu.replycount = commu.replycount - 1 where commu.commuId = :id")
	void minusReplyCount(int id);
}
