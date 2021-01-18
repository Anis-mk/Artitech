package com.example.arti;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	public void globalConfig(AuthenticationManagerBuilder auth, DataSource datasource) throws Exception
	{
		auth.jdbcAuthentication()
			.passwordEncoder(new BCryptPasswordEncoder())
		    .dataSource(datasource)
		    .usersByUsernameQuery("select username as principal, password as credentials, true from users where username = ?")
		    .authoritiesByUsernameQuery("select user_username as principal, roles_role as role from users_roles where user_username = ?")
		    .rolePrefix("ROLE_");

	}

	@Override
	protected void configure (HttpSecurity http) throws Exception{
		
		http.
		     csrf().disable()
		     .authorizeRequests()
		     .antMatchers("/css/**", "/js/**", "/images/**")
		     .permitAll()
		     .anyRequest()
		     .authenticated()
		     .and()
		     .formLogin()
		     .loginPage("/login")
		     .permitAll()
		     .defaultSuccessUrl("/index.html")
		     .and()
		     .logout()
		     .invalidateHttpSession(true)
		     .logoutUrl("/logout")
		     .permitAll();
		     
		     
		     
		     
	
		
		
	}
	
	
	
	
}
