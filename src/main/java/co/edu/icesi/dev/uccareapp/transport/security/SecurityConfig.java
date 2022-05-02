package co.edu.icesi.dev.uccareapp.transport.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;

//	@Autowired
//	private MyCustomUserDetailsService userDetailsService;

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider());
//	}

//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsService);
//		authProvider.setPasswordEncoder(encoder());
//		return authProvider;
//	}
//
//	@Bean
//	public PasswordEncoder encoder() {
//		return new BCryptPasswordEncoder(11);
//	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//.antMatchers("/").hasAnyRole("admin","operator")
            .antMatchers("/vendors/**","/shipmethods/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        	
            .and().formLogin().and().httpBasic().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
            
            .and().logout()
            .invalidateHttpSession(true).clearAuthentication(true)
            //.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login?logout").permitAll();
    }
}