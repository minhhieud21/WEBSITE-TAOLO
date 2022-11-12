package com.example.JavaSpring.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.example.JavaSpring.models.AccountModel;
import com.example.JavaSpring.service.AccountService;
import com.example.JavaSpring.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;


@Component
public class JwtAuthorizationFilter extends
        UsernamePasswordAuthenticationFilter {
        private final static String Token_header = "authorization";

    @Autowired
    JwtService jwtService;

    @Autowired
    AccountService accountService;

    public JwtAuthorizationFilter(AuthenticationManager authManager, JwtService jwtService, AccountService accountService) {
        super(authManager);
        this.jwtService = jwtService;
        this.accountService = accountService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String authToken = httpRequest.getHeader(Token_header);
            if (jwtService.validateTokenLogin(authToken)) {
                String accID = jwtService.getAccIDFromToken(authToken);
                AccountModel accountModel = accountService.getUserByAccID(accID);
                if (accountModel != null) {
                    boolean enabled = true;
                    boolean accountNonExpired = true;
                    boolean credentialsNonExpired = true;
                    boolean accountNonLocked = true;
                    UserDetails userDetail = new User(accID, accountModel.getPassword(), enabled,
                            accountNonExpired,
                            credentialsNonExpired, accountNonLocked, accountModel.getAuthorities());
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail,
                            null, userDetail.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            chain.doFilter(request, response);
        }
}
