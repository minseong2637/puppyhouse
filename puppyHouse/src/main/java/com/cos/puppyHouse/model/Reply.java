package com.cos.puppyHouse.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(
		name = "USER_SEQ_GENERATOR4"
		, sequenceName = "USER_SEQ4"
		, initialValue = 1
		, allocationSize = 1
	)

public class Reply {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQ_GENERATOR4")
	private int replyId;
	
	@Column(nullable=false, length=200)
	private String content;
	
	@ManyToOne
	@JoinColumn(name="CommunityId")
	private Community community;
	
	@ManyToOne
	@JoinColumn(name="UsersId")
	private Users users;
	
	@CreationTimestamp
	private Timestamp createDate;
}
