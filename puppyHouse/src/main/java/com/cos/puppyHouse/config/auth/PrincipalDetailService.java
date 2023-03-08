package com.cos.puppyHouse.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.puppyHouse.model.Users;
import com.cos.puppyHouse.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Users principal = userRepository.findByUserid(userId)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.: " + userId);
					
				});
		System.out.println("로그인 유저 예약 가져오나"+principal.getReserv());
		return new PrincipalDetail(principal);
	}
}
