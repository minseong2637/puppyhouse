package com.cos.puppyHouse.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
		uniqueConstraints = {
				@UniqueConstraint(
						name="likes",
						columnNames = {"CommunityId", "UsersId"}
			)			
		}
)
@SequenceGenerator(
		name = "USER_SEQ_GENERATOR7"
		, sequenceName = "USER_SEQ7"
		, initialValue = 1
		, allocationSize = 1
		)
public class Likes{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQ_GENERATOR7")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CommunityId")
	private Community community;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UsersId")
	private Users users;
	
	@ColumnDefault("0")
	private int likes_state;
	
	public Likes(Community commu, Users user) {
		this.community = commu;
		this.users = user;
	}
}
