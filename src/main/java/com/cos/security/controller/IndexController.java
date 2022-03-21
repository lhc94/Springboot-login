package com.cos.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.security.model.User;
import com.cos.security.repository.UserRepository;

@Controller
public class IndexController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; 
	//BCrypt 패스워드 암호화
	
	@GetMapping({"", "/"})
	public String index() {
		return "index"; 
		// src/main/resources/templates/index.mustache 이 경로를 찾음
	}
	
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin";
	}
	
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "manager";
	}
	// 스프링시큐리티 해당주소를 낚아채버림 - SecurityConfig 파일 생성 후 낚아채지않음
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	@GetMapping("/joinForm")
	public String join() {
		return "joinForm";
	}
	@PostMapping("/join")
	public @ResponseBody String joinProc(User user) {
		user.setRole("ROLE_USER");
		// id와 timeStemp는 어노테이션과 오토 임플리먼트에의해 자동으로 넣어짐
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		
		userRepository.save(user);  // 회원가입 잘됨. 비밀번호 : 1234 => 시큐리티로 로그인을 할 수 없다. 이유는 패스워드가 암호화가 안되었기때문에
		return "redirect:/loginForm";
	}
	
}
