package com.cos.puppyHouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.puppyHouse.model.Community;
import com.cos.puppyHouse.repository.commuRepository;
import com.cos.puppyHouse.repository.likesRepository;

@Service
public class likeService {
	@Autowired
	private commuRepository commuRepository;
	@Autowired
	private likesRepository likesRepository;
	
	//좋아요 저장
	@Transactional
	public void insertLikes(int id, int userid) {
		likesRepository.insertLikes(id, userid);
		likesRepository.plusLikesCount(id);	
	}
	
	//좋아요 취소
	@Transactional
	public void deleteLikes(int id, int commuid) {
		System.out.println("deletelike"+commuid);
		Community commu = commuRepository.findById(commuid)
				.orElseThrow(()->{
					return new IllegalArgumentException("댓글 쓰기 실패: 게시글 아이디를 찾을 수 없습니다.");
				});
		
		int cnt=commu.getLikescount();
		if(cnt>0) {
			cnt--;
			commu.setLikescount(cnt);
		}
		likesRepository.deleteById(id);
	}
	
	
}
