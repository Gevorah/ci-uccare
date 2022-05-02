package co.edu.icesi.dev.uccareapp.transport.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	UserRepository userrepository;
	
	@Autowired
	public CustomUserDetailsService(UserRepository userrepository) {
		this.userrepository = userrepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userrepository.findByUsername(username).get(0);
		if (user == null) {
			throw new UsernameNotFoundException("User not found.");
		}
		org.springframework.security.core.userdetails.User.UserBuilder builder = 
			org.springframework.security.core.userdetails.User
			.withUsername(username)
			.password(user.getPassword())
			.roles(user.getRole().name());
		
		return builder.build();
	}
}