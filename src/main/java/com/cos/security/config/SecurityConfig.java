package com.cos.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	//패스워드 암호화 하기
	@Bean // 해당 메서드의 리턴되는 오브젝트를 IoC를 등록해준다
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); // csrf :  공격자가 의도한 행위(등록, 수정, 삭제 등)를 특정 웹사이트에 요청하도록 만드는 공격
		http.authorizeRequests()
			.antMatchers("/user/**").authenticated() //authenticated : 인증
			.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/loginForm") 
			.loginProcessingUrl("/login")// loginForm에서 login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해준다.
			// controller에 시큐리티 login을 만들지 않아도된다
			.defaultSuccessUrl("/");
			
		
	}

	
	
}
