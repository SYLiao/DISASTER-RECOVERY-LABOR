package com.labor.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labor.model.User;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter{
	
	public JwtLoginFilter(String defaultFilterStringProcessUrl, AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(defaultFilterStringProcessUrl));
		setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
		System.out.println(user.getUsername());
		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
		throws IOException, ServletException{
		Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
		StringBuffer stringBuffer = new StringBuffer();
		for(GrantedAuthority authority : authorities ) {
			stringBuffer.append(authority.getAuthority())
				.append(",");
		}
		String jwt = Jwts.builder()
				.claim("authorities", stringBuffer)
				.setSubject(authResult.getName())
				.setExpiration(new Date(System.currentTimeMillis() + 60*1000*15))
				.signWith(SignatureAlgorithm.HS512, "Liao@Labor")
				.compact();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter wr = response.getWriter();
		wr.write(new ObjectMapper().writeValueAsString(jwt));
		wr.flush();
		wr.close();
	}
	
	protected void unsuccessfulAuthentocation(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
		throws IOException, ServletException{
		response.setContentType("application/json;charset=utf-8");

		PrintWriter out = response.getWriter();
		out.write("Login failed!");
		out.flush();
		out.close();
	}

}
