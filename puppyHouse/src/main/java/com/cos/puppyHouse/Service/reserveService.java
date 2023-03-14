package com.cos.puppyHouse.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.puppyHouse.model.Pet;
import com.cos.puppyHouse.model.Reserv;
import com.cos.puppyHouse.model.Users;
import com.cos.puppyHouse.repository.UserRepository;
import com.cos.puppyHouse.repository.reserveRepository;

@Service
public class reserveService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private reserveRepository reserveRepository;

	@Transactional
	public List<Users> 선생님정보() {
		return userRepository.findAll();
	}
	@Transactional
	public Users 선생님상세보기(int id) {
		System.out.println("선생님 상세보기 서비스 호출");
		return userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("에러남아아아");
		});
	}
	
	
	@Transactional
	public void 예약하기(Reserv reserve,Pet pet, Users user) {
		System.out.println("petBoardService 출력");
		reserve.setTeacher(user);
		reserve.setPet(pet);
		reserveRepository.save(reserve);
	}
	@Transactional
	public List<Reserv> 예약정보보기() {
		return reserveRepository.findAll();
	}
	
	@Transactional
	public List<String> 예약정보보기중복제거() {
		return reserveRepository.findDistinctAll();
	}
	
	@Transactional
	public List<Pet> 예약정보강아지중복제거() {
		return reserveRepository.findDistinctAll2();
	}
	
	@Transactional
	public Reserv 예약강아지목록상세보기(int reservId) {
		System.out.println("상세보기 서비스 호출");
		return reserveRepository.findById(reservId).orElseThrow(()->{
			return new IllegalArgumentException("강아지 상세 정보를 부를 수 없습니다.");
		});
	}
	@Transactional
	public List<Reserv> 예약한강아지호출(String reservdate,Users teacher) {
		System.out.println("예약한강아지호출 ");
		return reserveRepository.findByReservdateAndTeacher(reservdate,teacher);
	
	}
	
	@Transactional
	public void 예약수정하기(int reservId, Reserv requestReserv, Pet pets, Users user) {
		Reserv reserv = reserveRepository.findById(reservId).orElseThrow(() -> {
			return new IllegalArgumentException("예약 정보 찾기 실패");
		});
		
		System.out.println("예약수정서비스"+pets +","+user);
		
		reserv.setPet(pets);
		reserv.setTeacher(user);
		reserv.setReservdate(requestReserv.getReservdate());
	}
	
	@Transactional
	public void 예약삭제하기(int reservId) {
		reserveRepository.deleteById(reservId);
	}

}
