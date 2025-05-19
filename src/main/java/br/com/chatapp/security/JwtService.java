package br.com.chatapp.security;

import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class JwtService implements Serializable {
  private static final long serialVersionUID = 1L;
  private final String ISSUER = "chatapp_001";
  private Algorithm algorithm = Algorithm.HMAC256("secret_key");
  private JWTVerifier verifier = JWT.require(this.algorithm).withIssuer(this.ISSUER).build();

  public String create(Long userId, String userName) {
    Date expiresAt = Date.from(Instant.now().plus(10, ChronoUnit.SECONDS));
    return JWT.create()
        .withIssuer(this.ISSUER)
        .withClaim("userId", userId)
        .withClaim("userName", userName)
        .withIssuedAt(new Date())
        .withExpiresAt(expiresAt)
        .sign(this.algorithm);
  }

  public DecodedJWT verify(String jwtToken) {
    try {
      return this.verifier.verify(jwtToken);
    } catch (JWTVerificationException e) {
      return null;
    }
  }
}
