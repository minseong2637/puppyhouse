package com.cos.puppyHouse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="Reserv")
@SequenceGenerator(
		name = "USER_SEQ_GENERATOR5"
		, sequenceName = "USER_SEQ5"
		, initialValue = 1
		, allocationSize = 1
		)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reserv {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQ_GENERATOR5")
	private int reservId;
	
	@Column(nullable=false, length=100)
	private String reservdate;
	
	@JsonBackReference
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="PetId")
	private Pet pet;
	
	@JsonBackReference
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="teacherId")
	private Users teacher;
	
}
