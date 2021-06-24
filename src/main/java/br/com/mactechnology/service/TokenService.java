package br.com.mactechnology.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.mactechnology.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${maccourses.jwt.expiracao}")
	private String expiracao;
	
	@Value("${maccourses.jwt.secret}")
	private String secret;
	
	public String gerarToken(Authentication auth) {

		Date dataCriacao = new Date();
		Date dataExpiracao = new Date(dataCriacao.getTime() + Long.parseLong(expiracao));
		Usuario logado = (Usuario) auth.getPrincipal();

		return Jwts.builder().setIssuer("mac-courses").setSubject(logado.getId().toString()).setIssuedAt(dataCriacao)
				.setExpiration(dataExpiracao).signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getUsuarioId(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		Long usuarioId = Long.parseLong(claims.getSubject());
		return usuarioId;
	}
}