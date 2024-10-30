package com.deluxe.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.deluxe.service.IUsuarioService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class JwtService {
	private static final String SECRET_KEY="alsdkjalksdjalksdjalksdjlaskdjalskdjlaskdjalskdjalskdjalskdjaslkdj";

	private final IUsuarioService usuarioServiceImp;
	public String getToken(UserDetails usuario) {
		Map<String,Object> extraClaims = new HashMap<>(); 
		extraClaims.put("id", usuarioServiceImp.getUsuarioValidation(usuario.getUsername()).getId());
		extraClaims.put("rol", usuarioServiceImp.getUsuarioValidation(usuario.getUsername()).getRol());
		return getToken(extraClaims,usuario);
	}

	private String getToken(Map<String,Object> extraClaims, UserDetails usuario) {
		
		return Jwts.builder()
				.setClaims(extraClaims)
				.setSubject(usuario.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2)) // 2 horas
				.signWith(getKey(),SignatureAlgorithm.HS256)
				.compact();
	}

	private Key getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String getUsernameFromToken(String token) {
		//System.out.println("username en token "+getClaim(token,Claims::getSubject));
		return getClaim(token,Claims::getSubject);
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		//System.out.println("username token valid "+username);
		return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
	}
	
	private Claims getAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	public <T>T getClaim(String token,Function<Claims,T> claimsResolver){
		final Claims claims=getAllClaims(token);
		return claimsResolver.apply(claims);
		
	}
	
	private Date getExpiration(String token) {
		return getClaim(token, Claims::getExpiration);
	}
	
	private boolean isTokenExpired(String token) {
		return getExpiration(token).before(new Date());
	}
}
