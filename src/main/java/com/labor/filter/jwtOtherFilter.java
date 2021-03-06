package com.labor.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class jwtOtherFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		if(!req.getRequestURL().toString().equals("http://localhost:8080/newUser")) {
			String jwToken = req.getHeader("authorization");
			Claims claims = Jwts.parser().setSigningKey("Liao@Labor").parseClaimsJws(jwToken.replace("Bearer", ""))
					.getBody();
			String username = claims.getSubject();
			List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, "123", authorities);
			SecurityContextHolder.getContext().setAuthentication(token);
		}
		chain.doFilter(req, response);
	}
	
}
