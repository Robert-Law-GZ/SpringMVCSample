package com.robert.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    private static final String SECRET = "secret";

    /**
     * 创建token
     *
     * @return
     */
    public static String token(String userId) {
        String token = "";

        try {
            Map<String, Object> headerClaims = new HashMap();
            headerClaims.put("owner", userId);

            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            token = JWT.create()
                    .withIssuer("auth0")
                    .sign(algorithm);
        } catch (UnsupportedEncodingException exception) {
        } catch (JWTCreationException exception) {
        }

        return token;
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    public static boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException exception) {
        } catch (JWTVerificationException exception) {
        }

        return false;
    }

}
