package com.cos.puppyHouse.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="users")
@SequenceGenerator(
		name = "USER_SEQ_GENERATOR"
		, sequenceName = "USER_SEQ"
		, initialValue = 1
		, allocationSize = 1
		)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQ_GENERATOR")
	private int id;
	
	@Column(nullable=false, length=30, unique=true)
	private String userid;
	
	@Column(nullable=false, length=100)
	private String userpassword;
	
	@Column(nullable=false, length=30)
	private String username;
	
	@Column(nullable=false, length=30)
	private String phone;
	
	@Column(nullable=false, length=50)
	private String email;
	
	@Column(nullable=false, length=30)
	private String addr;
	
	@Column(nullable=false, length=30)
	private String addrdetail;
	
	@Enumerated(EnumType.STRING)
	private RoleType roles;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	@OneToMany (mappedBy="community",fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<Likes> likes;

	@OneToMany (mappedBy="users", fetch = FetchType.LAZY)
	private List<Pet> Pet;
	
	@JsonManagedReference
	@JsonIgnore
	@OneToMany (mappedBy="teacher", fetch = FetchType.LAZY ,cascade=CascadeType.REMOVE)
	private List<Reserv> Reserv;
	
}
