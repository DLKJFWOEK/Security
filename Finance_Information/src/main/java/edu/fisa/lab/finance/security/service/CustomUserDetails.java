package edu.fisa.lab.finance.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import edu.fisa.lab.finance.client.domain.entity.Client;
import edu.fisa.lab.finance.role.domain.Role;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
	private User user;
	private final String name;
	private final String loginId;
	private final String pwd;
	private final Collection<? extends GrantedAuthority> roles;

	public CustomUserDetails(Client client){
		this.roles = user.getAuthorities();
		this.loginId = client.getLoginId();
		this.pwd= client.getPassword();
		this.name= client.getName();

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		return this.pwd;
	}

	@Override
	public String getUsername() {
		return this.loginId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}


	public String getName(){

		return this.name
	}
	private Collection<GrantedAuthority> getGrantedAuthorities() {
		Collection<GrantedAuthority>  authorities = new ArrayList<>();

		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));

		}
		return authorities;

	}

}
