package com.cos.security.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.security.model.User;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
// 로그인을 진행이 완료가 되면 시큐리티 session을 만들어줍니다. ( Security ContextHolder)
// 단, 오브젝트 => Authentication 타입의 객체 이여만 한다
// Authentication 안에 User정보가 있어야 됨.
// User 오브젝트타입 => UserDetails 타입 객체이여야됨

// Security Session => Authentication => UserDetails (User오브젝트 접근 방법)

public class PrincipalDetails implements UserDetails{

	private User user; // 콤포지션
	
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	// 해당 User의 권한을 리턴하는 곳!!
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { // 계정 완료확인
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // 잠금확인
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { //  패스워드가 만료되지 않았는지 확인
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		// 우리 사이트 !! 1년동안 회원이 로그인을 안하면!! 휴먼 계정으로 하기로 함
		// 현재시간 - 고르인시간 => 1년을 초과하면 return false;
		// 이런 기간설정할때 사용
		
		return true;
	}
}
	
