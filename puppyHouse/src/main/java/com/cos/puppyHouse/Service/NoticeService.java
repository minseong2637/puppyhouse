package com.cos.puppyHouse.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.puppyHouse.model.NoticeFAQ;
import com.cos.puppyHouse.model.NoticeRoleType;
import com.cos.puppyHouse.model.Users;
import com.cos.puppyHouse.repository.NoticeRepository;

@Service
public class NoticeService {
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	@Transactional
	public void 글쓰기(NoticeFAQ noticeFAQ, Users user) {
		noticeFAQ.setUsers(user);
		noticeRepository.save(noticeFAQ);
	}
	
	@Transactional(readOnly=true)
	public Page<NoticeFAQ> 글목록(Pageable pageable, NoticeRoleType roles){
		return noticeRepository.findByRoles(roles,pageable);
	}
	
	
	@Transactional(readOnly=true)
	public NoticeFAQ 글상세보기(int id) {
		return noticeRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
				});
	}
	
	@Transactional
	public void 글삭제하기(int id) {
		noticeRepository.deleteById(id);
	}
	
	@Transactional
	public void 글수정하기(int id, NoticeFAQ requestNoticeFAQ) {
		NoticeFAQ noticeFAQ = noticeRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
		});
		noticeFAQ.setTitle(requestNoticeFAQ.getTitle());
		noticeFAQ.setContent(requestNoticeFAQ.getContent());
	}
}
