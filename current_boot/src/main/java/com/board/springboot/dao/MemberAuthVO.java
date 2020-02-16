package com.board.springboot.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "authorities")
public class MemberAuthVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idx;
    @Column(name="user_idx")
	private int userIdx;
	@Column(name="authority")
	private String authority;
	@Column(name="token")
	private String token;
}
