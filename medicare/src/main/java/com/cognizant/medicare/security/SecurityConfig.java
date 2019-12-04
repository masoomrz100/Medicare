package com.cognizant.medicare.security;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

/* --	@Autowired
//	AppUserDetailsService appUserDetailsService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("pwd")).roles("ADMIN").and()
				.withUser("user").password(passwordEncoder().encode("pwd")).roles("USER");
		auth.userDetailsService(inMemoryUserDetailsManager());*/
/* --		System.out.println("start sec-config 0000");
		auth.userDetailsService(appUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println("start sec-config 0001");
		return new BCryptPasswordEncoder();
	} -- */

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
	
		
		System.out.println("start sec-config 0002");
	              httpSecurity.cors();
	             /* httpSecurity.csrf().disable().httpBasic().and().authorizeRequests().antMatchers("/menu-items").permitAll()
	                           .antMatchers("/users").permitAll().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest()
	                            .authenticated().antMatchers("/authenticate").permitAll().antMatchers(HttpMethod.OPTIONS, "/**")
	                           .permitAll().anyRequest().authenticated().and()
	                           .addFilter(new JwtAuthorizationFilter(authenticationManager()));
		// .anyRequest().authenticated().and().addFilter(new
		// JwtAuthorizationFilter(authenticationManager()));  */
	              
	        
	/*            	  httpSecurity.csrf().disable().httpBasic().and().authorizeRequests().antMatchers("/menu-items").permitAll()
	            	  				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().antMatchers("/users").permitAll()
	            	  				.antMatchers(HttpMethod.POST, "/**").permitAll().antMatchers("/authenticate").permitAll()
	            	  				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated().and()
	            	  				.addFilter(new JwtAuthorizationFilter(authenticationManager()));   */
	            	   
	          	  httpSecurity.csrf().disable().httpBasic().and().authorizeRequests().antMatchers("/medicare").permitAll()
	          	  	.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().antMatchers("/users").permitAll()
	          	  .antMatchers(HttpMethod.OPTIONS, "/**").permitAll().antMatchers("/users/admin").permitAll()
	          	 .antMatchers(HttpMethod.OPTIONS, "/**").permitAll().antMatchers("/users/doctor").permitAll()
	          	.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().antMatchers("/users/patient").permitAll()
	          	.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().antMatchers("/medicare/doctor").permitAll()
	          	.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().antMatchers("/medicare/patient").permitAll()
	          	.antMatchers(HttpMethod.PUT, "/**").permitAll().antMatchers("/medicare/patient").permitAll()
	          	.antMatchers(HttpMethod.GET, "/**").permitAll().antMatchers("/medicare/medicalTestHistory").permitAll()
	          	.antMatchers(HttpMethod.PUT, "/**").permitAll().antMatchers("/medicare/saveReport").permitAll()
	  				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated().and()
	  				.addFilter(new JwtAuthorizationFilter(authenticationManager()));
	}

/*	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		LOGGER.info("Start");
		List<UserDetails> userDetailsList = new ArrayList<>();

		userDetailsList.add(User.withUsername("user").password(passwordEncoder().encode("pwd")).roles("USER").build());

		userDetailsList
				.add(User.withUsername("admin").password(passwordEncoder().encode("pwd")).roles("ADMIN").build());

		LOGGER.info("End");
		return new InMemoryUserDetailsManager(userDetailsList);
	}  */

}
