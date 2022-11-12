package com.example.JavaSpring.service;

import java.util.Date;
import org.springframework.stereotype.Service;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Service
public class JwtService {

	public static final String SECRET_KEY = "secret_pass@taolo_create_by_minhhieu";
	public static final int EXPIRE_TIME = 3600000;

	public String createTokenUser(String accID) {
		String token = null;
		try {
			JWSSigner signer = new MACSigner(createUserSecret());
			JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
			builder.claim("accID", accID);
			builder.expirationTime(createExpirationDateUser());
			JWTClaimsSet claimsSet = builder.build();
			SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256),
					claimsSet);
			signedJWT.sign(signer);
			token = signedJWT.serialize();
		} catch (Exception e) {
			return null;
		}
		return token;
	}

	private JWTClaimsSet getClaimsFromToken(String token) {
		JWTClaimsSet claims = null;
		try {
			SignedJWT signedJWT = SignedJWT.parse(token);
			JWSVerifier verifierAdminStaff = new MACVerifier(createUserSecret());
			JWSVerifier verifierUser = new MACVerifier(createUserSecret());
			if (signedJWT.verify(verifierAdminStaff)) {
				claims = signedJWT.getJWTClaimsSet();
			} else if (signedJWT.verify(verifierUser)) {
				claims = signedJWT.getJWTClaimsSet();
			}
		} catch (Exception e) {
			return null;
		}
		return claims;
	}

	private byte[] createUserSecret() {
		byte[] user_secret = new byte[32];
		user_secret = SECRET_KEY.getBytes();
		return user_secret;
	}

	private Date createExpirationDateUser() {
		return new Date(System.currentTimeMillis() + EXPIRE_TIME);
	}

	private Date getExpirationDateFromToken(String token) {
		Date expiration = null;
		JWTClaimsSet claims = getClaimsFromToken(token);
		expiration = claims.getExpirationTime();
		return expiration;
	}

	public String getAccIDFromToken(String token) {
		String accID = null;
		try {
			JWTClaimsSet claims = getClaimsFromToken(token);
			accID = claims.getStringClaim("accID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accID;
	}

	private Boolean isTokenExpired(String token) {
		Date expiration = getExpirationDateFromToken(token);
		if (expiration.before(new Date())) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean validateTokenLogin(String token) {
		if (token == null || token.trim().length() == 0) {
			return false;
		}
		String accID = getAccIDFromToken(token);
		if (accID == null || accID.isEmpty()) {
			return false;
		}
		if (isTokenExpired(token)) {
			return false;
		}
		return true;
	}
}