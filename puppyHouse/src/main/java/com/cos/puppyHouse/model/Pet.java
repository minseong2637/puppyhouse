package com.cos.puppyHouse.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Table(name="pet")
@SequenceGenerator(
		name = "USER_SEQ_GENERATOR2"
		, sequenceName = "USER_SEQ2"
		, initialValue = 1
		, allocationSize = 1
		)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pet {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQ_GENERATOR2")
	private int petId;

	@ManyToOne
	@JoinColumn(name="UsersId")
	private Users users;
	
	@Column(length=30)
	private String petName;
	
	@Column(length=100)
	private String species;
	
	@Column(length=30)
	private String age;
	
	@Column(length=30)
	private String birthday;
	
	@Column(length=30)
	private int agetype;
	
	@Column(length=30)
	private String gender;
	
	@Column(length=30)
	private String weight;
	
	@Column(length=100)
	private String allergy;
	
	@Column(length=100)
	private String etc;
	
	@Column(length=30)
	private String neutered;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	//이미지 필드
	private String imgOriName;
	
	private String imgName;
	
	private String imgUrl;
}