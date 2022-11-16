package com.example.JavaSpring.config;

import com.example.JavaSpring.jwt.CustomAccessDeniedHandler;
import com.example.JavaSpring.jwt.JwtAuthorizationFilter;
import com.example.JavaSpring.jwt.RestAuthenticationEntryPoint;
import com.example.JavaSpring.service.AccountService;
import com.example.JavaSpring.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	@Autowired
	JwtService jwtService;

	@Autowired
	AccountService accountService;

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public RestAuthenticationEntryPoint restServicesEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}

	@Bean
	public CustomAccessDeniedHandler customAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().ignoringAntMatchers("/**");
		http.authorizeRequests().antMatchers("/api/v1/account/login", "/api/v1/account/addUser").permitAll();
		http.antMatcher("/**").httpBasic()
				.authenticationEntryPoint(restServicesEntryPoint()).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/api/v1/account/addUser")
				.access("hasAnyAuthority('Admin','User')")
				.antMatchers(HttpMethod.GET, "/api/v1/account/getAllAccount").access("hasAnyAuthority('Admin')")
				.antMatchers(HttpMethod.POST, "/api/v1/account/setPassword").access("hasAnyAuthority('Admin','User')")
				.antMatchers(HttpMethod.POST, "/api/v1/account/changestatus").access("hasAuthority('Admin')")
				.antMatchers(HttpMethod.GET, "/api/v1/user/getAllUser").access("hasAnyAuthority('Admin')")
				.antMatchers(HttpMethod.GET, "/api/v1/user/**").access("hasAnyAuthority('Admin','User')")
				.antMatchers(HttpMethod.POST, "/api/v1/user/updateUser").access("hasAnyAuthority('Admin','User')")
				.antMatchers(HttpMethod.POST, "/api/v1/product/addProduct","/api/v1/product/changestatus","/api/v1/product/updateProduct").access("hasAuthority('Admin')")
				.antMatchers(HttpMethod.POST, "/api/v1/image/add","/api/v1/image/mainImage").access("hasAuthority('Admin')")
				.antMatchers(HttpMethod.DELETE, "/api/v1/image/deleteImage").access("hasAuthority('Admin')")
				.antMatchers(HttpMethod.GET, "/api/v1/statistical/getStatisticalService").access("hasAnyAuthority('Admin')")
				.and()
				.addFilter(new JwtAuthorizationFilter(
						authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)),
						jwtService, accountService))
				.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
		return http.build();
	}
}