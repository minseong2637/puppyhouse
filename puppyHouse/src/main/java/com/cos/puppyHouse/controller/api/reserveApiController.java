package com.cos.puppyHouse.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.puppyHouse.Service.PetService;
import com.cos.puppyHouse.Service.reserveService;
import com.cos.puppyHouse.config.auth.PrincipalDetail;
import com.cos.puppyHouse.dto.ResponseDto;
import com.cos.puppyHouse.dto.ResponseReservDto;
import com.cos.puppyHouse.model.Pet;
import com.cos.puppyHouse.model.Reserv;
import com.cos.puppyHouse.model.Users;

@RestController
public class reserveApiController {
	@Autowired
	private PetService petService;
	
	@Autowired
	private reserveService reserveService;
	
	@PostMapping("/api/petReserve/{petId}/{teacherId}")
	public ResponseDto<Integer> svae(@RequestBody Reserv reserve,@PathVariable int petId, @PathVariable int teacherId){
		Pet pet = petService.강아지수첩상세보기(petId);
		Users user = reserveService.선생님상세보기(teacherId);
		System.out.println(reserve);
		
		reserveService.예약하기(reserve,pet,user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("/api/revser/{reservId}/{pet}/{teacher}")
	public ResponseDto<Integer> update(@PathVariable int reservId, @RequestBody Reserv reserve,@PathVariable int pet, @PathVariable int teacher){
		System.out.println("예약 controller 출력"+pet+teacher);
		Pet pets = petService.강아지수첩상세보기(pet);
		Users user = reserveService.선생님상세보기(teacher);
		reserveService.예약수정하기(reservId,reserve,pets,user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/api/revserdelete/{reservId}")
	public ResponseDto<Integer> deleteById(@PathVariable int reservId){
		reserveService.예약삭제하기(reservId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	
	@PostMapping("/api/petReserve/test")
	public ResponseReservDto<List<String>,List<Integer>> test(@RequestBody Reserv reserve, @AuthenticationPrincipal PrincipalDetail principal){
		System.out.println("예약test controller 출력");
		List<Reserv> reserv = reserveService.예약한강아지호출(reserve.getReservdate(),principal.getUser());
		System.out.println("확인용"+reserv.get(0));
		List<Integer> reservdogsInx=new ArrayList<Integer>();
		List<String> reservdogs =new ArrayList<String>();
		for(int i=0;i<reserv.size();i++) {
			reservdogsInx.add(reserv.get(i).getPet().getPetId());
			reservdogs.add(reserv.get(i).getPet().getPetName());
			System.out.println("예약강아지list"+reservdogs.get(i));
		}
		return new ResponseReservDto<List<String>,List<Integer>>(HttpStatus.OK.value(),reservdogs,reservdogsInx);
	}
}
