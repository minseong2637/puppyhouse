package com.cos.puppyHouse.model;

import java.sql.Timestamp;

import javax.persistence.Column;
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

@Table(name="Notice")
@SequenceGenerator(
		name = "USER_SEQ_GENERATOR3"
		, sequenceName = "USER_SEQ3"
		, initialValue = 1
		, allocationSize =  1
		)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity



public class NoticeFAQ {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQ_GENERATOR3")
	private int noticeId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	
	@JoinColumn(name="UsersId")
	private Users users;
	
	@Column(nullable=false, length=40)
	private String title;
	
	@Lob 
	private String content;
		
	@Enumerated(EnumType.STRING)
	private NoticeRoleType roles;
	
	@CreationTimestamp
	private Timestamp createDate;
}
