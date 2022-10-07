package com.example.JavaSpring.jwt.filter;

import com.example.JavaSpring.jwt.services.UserDetailsServiceImpl;
import com.example.JavaSpring.jwt.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserDetailsServiceImpl myUserDetailService;

    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = ((HttpServletRequest) request).getHeader("Authorization");
        String jwt=null;
        String username=null;

        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            jwt = authHeader.substring(7); // Bearer #####
            username = jwtUtil.extractUsername(jwt);
        }

        //123
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.myUserDetailService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }

        filterChain.doFilter(request, response);
    }

}
