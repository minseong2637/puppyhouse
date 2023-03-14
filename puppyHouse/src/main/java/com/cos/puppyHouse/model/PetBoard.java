package com.cos.puppyHouse.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="petBoard")
@SequenceGenerator(
		name = "USER_SEQ_GENERATOR6"
		, sequenceName = "USER_SEQ6"
		, initialValue = 1
		, allocationSize = 1
		)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PetBoard {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQ_GENERATOR6")
	private int petBoardId;
	
	@Lob
	private String content;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PetId")
	private Pet pet;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="UserId")
	private Users user;
	
	@Enumerated(EnumType.STRING)
	private PetBoardRoleType roles;
	
	//diary 이미지 필드
	private String imgOriName;
		
	private String imgName;
		
	private String imgUrl;
		
}
