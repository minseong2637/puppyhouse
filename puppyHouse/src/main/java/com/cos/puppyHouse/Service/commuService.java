package com.cos.puppyHouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.puppyHouse.controller.CommuController;
import com.cos.puppyHouse.model.Community;
import com.cos.puppyHouse.model.Reply;
import com.cos.puppyHouse.model.Users;
import com.cos.puppyHouse.repository.commuRepository;
import com.cos.puppyHouse.repository.replyRepository;

@Service
public class commuService {
	
	@Autowired
	private commuRepository commuRepository;
	
	@Autowired
	private replyRepository replyRepository;
	
	//게시글 작성
	@Transactional
	public void write(Community commu, Users user) {
		commu.setCount(0);
		commu.setUsers(user);
		commuRepository.save(commu);
	}
	
	//댓글 작성
	@Transactional
	public void writeReply(int id, Reply requestReply, Users user) {
		Community commu = commuRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("댓글 쓰기 실패: 게시글 아이디를 찾을 수 없습니다.");
				});
		requestReply.setUsers(user);
		requestReply.setCommunity(commu);
		replyRepository.save(requestReply);
		replyRepository.plusReplyCount(id);
	}
	
	@Transactional(readOnly=true)
	public Page<Community> list(Pageable pageable) {
		return commuRepository.findAll(pageable);
	}

	@Transactional(readOnly=true)
	public Community detail(int id) {
		commuRepository.updateCount(id);
		
		return commuRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
				});
		
	}
	
	//게시글 삭제
	@Transactional
	public void delete(int id) {
		commuRepository.deleteById(id);
	}
	
	//댓글 삭제
	@Transactional
	public void replyDelete(int commuId, int replyId) {
		Community commu = commuRepository.findById(commuId)
				.orElseThrow(()->{
					return new IllegalArgumentException("댓글 삭제 실패: 게시글 아이디를 찾을 수 없습니다.");
		});

		int cnt=commu.getReplycount();
		if(cnt>0) {
			cnt--;
			commu.setReplycount(cnt);
		}
		replyRepository.deleteById(replyId);
	}
	
	
	//게시글 수정
	@Transactional
	public void update(int id, Community requestCommunity) {
		Community commu = commuRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
				});
		commu.setTitle(requestCommunity.getTitle());
		commu.setContent(requestCommunity.getContent());
		commu.setImgName(requestCommunity.getImgName());
		commu.setImgOriName(requestCommunity.getImgOriName());
		commu.setImgUrl(requestCommunity.getImgUrl());
	}
	
	
}