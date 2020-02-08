package com.labor.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.labor.model.User;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter{
	
	private AuthenticationManager authenticationManager;
	
	public JwtLoginFilter(String defaultFilterStringProcessUrl, AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(defaultFilterStringProcessUrl));
		this.authenticationManager = authenticationManager;
//		setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		BufferedReader in=new BufferedReader(new InputStreamReader(request.getInputStream()));
		StringBuilder sb = new StringBuilder(); 
		String xmlHead = "";
		String xmlContent="";
		String line = null; 
		while ((line = in.readLine()) != null) { 
			sb.append(line); 
		}
		ArrayList<String> userList = getStr(sb.toString());
		User user2 = new User(userList.get(1), userList.get(3));
		System.out.println(user2.getUsername());
		try {
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user2.getUsername(), user2.getPassword()));
		} catch (Exception e) {
			// TODO: handle exception
			response.setContentType("application/json;charset=utf-8");

			PrintWriter out = response.getWriter();
			out.write("Login failed!");
			out.flush();
			out.close();
			return null;
		}
		
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
				.setExpiration(new Date(System.currentTimeMillis() + 60*1000*30))
				.signWith(SignatureAlgorithm.HS512, "Liao@Labor")
				.compact();
//		String username = authResult.getName();
		response.setContentType("application/json;charset=utf-8");
		PrintWriter wr = response.getWriter();
		wr.write(new ObjectMapper().writeValueAsString(jwt));
//		wr.write(new ObjectMapper().writeValueAsString(username));
		wr.flush();
		wr.close();
		System.out.println(jwt);
	}
	
//	protected void unsuccessfulAuthentocation(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
//		throws IOException, ServletException{
//		System.out.println("44444444444444444444");
//		response.setContentType("application/json;charset=utf-8");
//
//		PrintWriter out = response.getWriter();
//		out.write("Login failed!");
//		out.flush();
//		out.close();
//		System.out.println("222222222222223456789087654324567898765432");
//	}
	
	public ArrayList<String> getStr(String str) {
		 
		Pattern p1=Pattern.compile("\"(.*?)\"");
		 
		Matcher m = p1.matcher(str);
		 
		ArrayList<String> list = new ArrayList<String>();
		while (m.find()) {
			list.add(m.group().trim().replace("\"",""));
		}
		return list;
		        
	}

}
