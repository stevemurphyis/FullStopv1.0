package com.SMurphy.Fullstop;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	//notify Spring of BCryptPasswordEncoder, recommended by Spring Security
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	//notify Spring of Dao Authentication Provider
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	//Provide the user query & role query result, along with datasource and password encoder to be executed on login
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	//Define access controls and required permissions for each URL path
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//Enables SameSite and XXS protection and adds these to response header
		http.headers()
			.frameOptions().sameOrigin()
			.httpStrictTransportSecurity().disable()
			.xssProtection().block(true);
		
		//using antMathcers to assign specific roles access to specific pages
		http.authorizeRequests()
			.antMatchers("/users/**").hasAnyAuthority("ADMIN")
			.antMatchers("/add_period/**").hasAuthority("USER")
			.antMatchers("/period_added/**").hasAuthority("USER")
			.antMatchers("/view_period/**").hasAnyAuthority("ADMIN", "USER")
			.antMatchers("/period_edited/**").hasAnyAuthority("ADMIN", "USER")
			.antMatchers("/period_deleted/**").hasAnyAuthority("ADMIN")
			.antMatchers("/index/**").permitAll()
			.antMatchers("/").permitAll()
			.anyRequest().permitAll()
			.and()
			.formLogin()
				.usernameParameter("userName")
				.defaultSuccessUrl("/view_period")
				.permitAll()
			.and()
			.logout().logoutSuccessUrl("/logout_success").permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/error")
			;
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
	
}
