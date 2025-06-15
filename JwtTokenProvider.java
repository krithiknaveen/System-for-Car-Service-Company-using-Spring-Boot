package com.cts.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.cts.exceptions.CarServiceSecurityException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

/*
 * Utility class for generating, parsing, and validating JWT tokens.
 */

@Component
public class JwtTokenProvider {

	@Value("${app.jwt.secret}")
	private String sceretKey;

	@Value("${app.jwt.expiry.millis}")
	private long expiryMill;
	

	/*
     * Generates a JWT token for the authenticated user.
	 * It return a signed JWT token containing the username and expiration time
     */

	public String generateToken(Authentication authentication) {
		String username = authentication.getName();
		Date currentDate = new Date();
		Date expiryDate = new Date(currentDate.getTime() + expiryMill);
		String token = Jwts.builder().subject(username).issuedAt(currentDate).expiration(expiryDate).signWith(key())
				.compact();
		return token;
	}
	

	/*
	 * Returns the cryptographic key used to sign and verify JWT tokens.     
	 */
	
	private Key key() {

		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(sceretKey));

	}
	
	
	/*
     * Extracts the username from a given JWT token.
     * It return the username embedded in the token
     */

	public String getUsername(String token) {

		String username = Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token).getPayload()
				.getSubject();

		return username;
	}
	

	/*
     * Validates the given JWT token for structure, signature, and expiration.
     * Return true if the token is valid
     * Throws CarServiceSecurityException if the token is malformed, expired, unsupported, or invalid
     */

	public boolean validateToken(String token) {

		try {
			Jwts.parser().verifyWith((SecretKey) key()).build().parse(token);

			return true;

		} catch (MalformedJwtException | SignatureException ex) {

			throw new CarServiceSecurityException(HttpStatus.BAD_REQUEST, "Invalid Token");
		} catch (ExpiredJwtException ex) {

			throw new CarServiceSecurityException(HttpStatus.BAD_REQUEST, "Token Expired");
		} catch (UnsupportedJwtException ex) {

			throw new CarServiceSecurityException(HttpStatus.BAD_REQUEST, "Unsupported token");
		}
	}
}
