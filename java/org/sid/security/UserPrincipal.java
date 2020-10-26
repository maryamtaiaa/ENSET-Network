package org.sid.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.sid.entities.Login;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

	private Login login;
	

	public UserPrincipal(Login login) {
		this.login = login;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList();
		GrantedAuthority auth = new SimpleGrantedAuthority("ROLE_"+login.getRole().getRole());
		authorities.add(auth);
		return authorities;
	}
	

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return login.getPwd();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return login.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return login.isActive();
	}

}
