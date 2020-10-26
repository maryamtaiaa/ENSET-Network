package org.sid.security;



import org.sid.dao.LoginRepository;
import org.sid.entities.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class MyLoginServiseDetails implements UserDetailsService{
    @Autowired
    LoginRepository loginRepository ;
    Login login ;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		login = loginRepository.findByUsername(username);
		if(login == null) {
			throw new UsernameNotFoundException("User notFound");
			}
		return new UserPrincipal(login);
		
	}

	public Login getLogin() {
		return login;
	}

	

}
