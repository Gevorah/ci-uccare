package co.edu.icesi.dev.uccareapp.transport.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
    }

    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(6);
    }

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