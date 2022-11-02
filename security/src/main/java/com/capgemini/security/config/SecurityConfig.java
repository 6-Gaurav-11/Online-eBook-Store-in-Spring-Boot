package com.capgemini.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.capgemini.security.service.UserDetailsServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtAuthEntryPoint unauthorizedHandler;

	@Bean
	public JwtAuthTokenFilter authenticationJwtTokenFilter() {
		return new JwtAuthTokenFilter();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().authorizeRequests()

		//for login/signup
		.antMatchers("/bookstore/auth/**").permitAll()

		//for book content service
		.antMatchers("/bookstore/content/get/**").hasAnyRole("USER", "ADMIN")
		.antMatchers("/bookstore/content/add/**").hasRole("ADMIN")

		//for books service
		.antMatchers("/bookstore/books/get/**").permitAll()
		.antMatchers("/bookstore/books/add/**").hasRole("ADMIN")
		.antMatchers("/bookstore/books/delete/**").hasRole("ADMIN")
		.antMatchers("/bookstore/books/update/**").hasRole("ADMIN")

		//for library service
		.antMatchers("/bookstore/library/**").hasAnyRole("ADMIN", "USER")

		//for users service
		.antMatchers("/bookstore/users/getall/**").hasRole("ADMIN")
		.antMatchers("/bookstore/users/get/**").hasAnyRole("ADMIN", "USER")
		.antMatchers("/bookstore/users/add/**").hasRole("ADMIN")
		.antMatchers("/bookstore/users/update/**").hasAnyRole("ADMIN", "USER")
		.antMatchers("/bookstore/users/delete/**").hasAnyRole("ADMIN", "USER")
		
		//for order service
		.antMatchers("bookstore/orders/add").hasAnyRole("ADMIN", "USER")
		.antMatchers("bookstore/orders/getall").hasRole("ADMIN")
		.antMatchers("bookstore/orders/get/**").hasRole("USER")

		.anyRequest().authenticated()
		.and().csrf().disable();

		http.authenticationProvider(authenticationProvider());

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}