package com.cos.puppyHouse.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.puppyHouse.Service.UserService;
import com.cos.puppyHouse.dto.ResponseDto;
import com.cos.puppyHouse.model.Users;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody Users user) {
		System.out.println("UserApiController 호출됨");
		
		userService.joinUser(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	@PutMapping("/user/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Users user) {
		userService.updateUser(user);
		//세션 등록
		Authentication authentication =
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserid(), user.getUserpassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PostMapping("/auth/user/findUserId")
	public ResponseDto<Users> findUserId(@RequestBody Users user) {
		Users foundUser = userService.findUserId(user.getUsername(),user.getPhone());
		return new ResponseDto<Users>(HttpStatus.OK.value(),foundUser);
	}

	
	@PostMapping("/auth/user/findUserEmail")
	public ResponseDto<Users> findUserEmail(@RequestBody Users user) {
		System.out.println("넘어온 data의 email: "+user.getEmail());
		Users foundUser = userService.findUserEmail(user.getEmail());
		System.out.println("findUserEmail"+foundUser);
			return new ResponseDto<Users>(HttpStatus.OK.value(),foundUser);
	}
	
	
	@PutMapping("/auth/user/resetUserPassword")
	public ResponseDto<Integer> resetUserPassword(@RequestBody Users user) {
		userService.resetUserPassword(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	//id 중복검사
	@PostMapping("/auth/idCheck/{id}")
	public ResponseDto<Integer> idCheck(@PathVariable String id) {
		System.out.println("idcheck 확인"+id);
		int result = (int)userService.idCheck(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),result);
	}
}