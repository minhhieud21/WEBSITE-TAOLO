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
				.antMatchers(HttpMethod.POST, "/api/v1/product/addProduct","/api/v1/product/changestatus","/api/v1/product/updateProduct","/api/v1/product/addListProduct").access("hasAuthority('Admin')")
				.antMatchers(HttpMethod.POST, "/api/v1/image/add","/api/v1/image/mainImage").access("hasAuthority('Admin')")
				.antMatchers(HttpMethod.DELETE, "/api/v1/image/deleteImage").access("hasAuthority('Admin')")
				//JWT category
				.antMatchers(HttpMethod.GET,"/api/v1/category/getAllCategoryAdmin").access("hasAuthority('Admin')")
//				.antMatchers(HttpMethod.GET,"/api/v1/category/getAllCategoryUser").access("hasAnyAuthority('Admin','User')")
				.antMatchers(HttpMethod.GET,"/api/v1/category/getCategoryByID").access("hasAuthority('Admin')")
				.antMatchers(HttpMethod.POST,"/api/v1/category/addCategory").access("hasAuthority('Admin')")
				.antMatchers(HttpMethod.POST,"/api/v1/category/updateCategory").access("hasAuthority('Admin')")
				//JWT Cart
				.antMatchers(HttpMethod.GET,"/api/v1/cart/getCartByAccID").access("hasAnyAuthority('Admin','User')")
				.antMatchers(HttpMethod.GET,"/api/v1/cart/getAllCartReadyCheckOut").access("hasAnyAuthority('Admin')")
				.antMatchers(HttpMethod.GET,"/api/v1/cart/getCartReadyCheckOutByAccID").access("hasAnyAuthority('Admin','User')")
				.antMatchers(HttpMethod.POST,"/api/v1/cart/addCart").access("hasAnyAuthority('Admin','User')")
				.antMatchers(HttpMethod.POST,"/api/v1/cart/readyCheckout").access("hasAnyAuthority('Admin','User')")
				.antMatchers(HttpMethod.POST,"/api/v1/cart/updateCart").access("hasAnyAuthority('Admin','User')")
				.antMatchers(HttpMethod.DELETE,"/api/v1/cart/deleteCart").access("hasAnyAuthority('Admin','User')")
				//JWT cart detail
				.antMatchers(HttpMethod.DELETE,"/api/v1/cartdetail/deleteCartDetail").access("hasAnyAuthority('Admin','User')")
				.antMatchers(HttpMethod.DELETE,"/api/v1/cartdetail/deleteAllCartDetail").access("hasAnyAuthority('Admin','User')")
				.antMatchers(HttpMethod.POST,"/api/v1/cartdetail/updateCartDetail").access("hasAnyAuthority('Admin','User')")
				//JWT Bill
				.antMatchers(HttpMethod.GET,"/api/v1/bill").access("hasAnyAuthority('Admin')")
				.antMatchers(HttpMethod.GET,"/api/v1/bill/getBillByBillID").access("hasAnyAuthority('Admin')")
				.antMatchers(HttpMethod.GET,"/api/v1/bill/getBillByAccID").access("hasAnyAuthority('Admin','User')")
				.antMatchers(HttpMethod.POST,"/api/v1/bill/changeStatusBill").access("hasAnyAuthority('Admin')")
				.antMatchers(HttpMethod.POST,"/api/v1/bill/addBill").access("hasAnyAuthority('Admin')")
				.antMatchers(HttpMethod.DELETE,"/api/v1/bill/deleteBill").access("hasAnyAuthority('Admin')")
				//JWT Bill Detail
				.antMatchers(HttpMethod.GET,"/api/v1/billDetail").access("hasAnyAuthority('Admin')")
				.antMatchers(HttpMethod.GET,"/api/v1/billDetailByID").access("hasAnyAuthority('Admin')")
				.antMatchers(HttpMethod.GET,"/api/v1/billDetailBillID").access("hasAnyAuthority('Admin','User')")
				//JWT receipt
				.antMatchers(HttpMethod.GET,"/api/v1/receipt/getAllReceipt").access("hasAnyAuthority('Admin')")
				.antMatchers(HttpMethod.GET,"/api/v1/receipt/getReceiptByID").access("hasAnyAuthority('Admin')")
				.antMatchers(HttpMethod.POST,"/api/v1/receipt/showReceiptExcelFile").access("hasAnyAuthority('Admin')")
				.antMatchers(HttpMethod.POST,"/api/v1/receipt/changeReceiptStatus").access("hasAnyAuthority('Admin')")
				.antMatchers(HttpMethod.DELETE,"/api/v1/receipt/deleteReceipt").access("hasAnyAuthority('Admin')")
				//JWT receipt detail
				.antMatchers(HttpMethod.GET,"/api/v1/receiptdetail/getReceiptDetailByRecID").access("hasAnyAuthority('Admin')")
				.antMatchers(HttpMethod.GET,"/api/v1/receiptdetail/getReceiptDetailByID").access("hasAnyAuthority('Admin')")
				.antMatchers(HttpMethod.GET,"/api/v1/receiptdetail/getAllReceiptDetail").access("hasAnyAuthority('Admin')")
				.antMatchers(HttpMethod.POST,"/api/v1/receiptdetail/addReceiptDetail").access("hasAnyAuthority('Admin')")

				.antMatchers(HttpMethod.GET, "/api/v1/statistical/getStatisticalService").access("hasAnyAuthority('Admin')")

				.and()
				.addFilter(new JwtAuthorizationFilter(
						authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)),
						jwtService, accountService))
				.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
		return http.build();
	}
}