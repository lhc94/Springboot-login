package com.cos.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.security.model.User;

// CRUD 함수를 JpaRepository가 들고 있음.
// @Repository라는 어노테이션이 없어도 IoC되요. 이유는 JpaRepository를 상속했기 때문에
public interface UserRepository extends JpaRepository<User, Integer>{
	// find규칙 -> Username문법
	// select * from user where username = 1? (username)들어옴
	public User findByUsername(String username); // Jpa Qury method 따로 공부..

	
}
