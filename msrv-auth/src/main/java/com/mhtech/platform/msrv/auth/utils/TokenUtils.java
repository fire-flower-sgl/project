package com.mhtech.platform.msrv.auth.utils;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * json web token
 * @author GM
 *
 */
public class TokenUtils {

	private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);
	
	private static final String  SECRET = "MH_JSON_WEB_TOKEN";
	
	private static final String CLAIM_KEY = "Bearer";
	
	private TokenUtils() {}
	
	public static String createToken(String info, long expiresMillis) {
		Algorithm algorithm;
		try {
			algorithm = Algorithm.HMAC256(SECRET);
			long ctm = System.currentTimeMillis();
			Builder builder = JWT.create().withClaim(CLAIM_KEY, info).withExpiresAt(new Date(ctm + expiresMillis));
			return builder.sign(algorithm);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "";
	}
	
	public static String verifyToken(String token) throws Exception {
		Algorithm algorithm;
		try {
			algorithm = Algorithm.HMAC256(SECRET);
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT decoder = verifier.verify(token);
			return decoder.getClaim(CLAIM_KEY).asString();
		} catch (Exception e) {
			throw e;
		}
	}
}
