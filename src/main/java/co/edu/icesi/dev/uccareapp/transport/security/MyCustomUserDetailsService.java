package co.edu.icesi.dev.uccareapp.transport.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.model.hr.UserApp;
import co.edu.icesi.dev.uccareapp.transport.repository.UserRepository;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {
	
	UserRepository userrepository;
	
	@Autowired
	public MyCustomUserDetailsService(UserRepository userrepository) {
		this.userrepository=userrepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserApp user = userrepository.findByUsername(username).next();
		if (user != null) {
			System.out.println("------------------------"+user.getPassword());
			User.UserBuilder builder = User.withUsername(username).password(user.getPassword()).roles("");
			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}
}