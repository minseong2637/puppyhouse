package com.cos.puppyHouse.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cos.puppyHouse.Service.PetService;
import com.cos.puppyHouse.config.auth.PrincipalDetail;
import com.cos.puppyHouse.dto.ResponseDto;
import com.cos.puppyHouse.model.Pet;

@RestController
public class PetApiController {
	@Autowired
	private PetService petService;
	
	@RequestMapping(value = "/api/pet", method = { RequestMethod.PUT })

	public ResponseDto<Integer> save(@RequestBody Pet pet,  @AuthenticationPrincipal PrincipalDetail principal){

		System.out.println("PetApiController 호출됨"+pet.getAge());
		petService.펫등록(pet);


		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/api/petNoteInfo/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id) {
		petService.delete(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

}