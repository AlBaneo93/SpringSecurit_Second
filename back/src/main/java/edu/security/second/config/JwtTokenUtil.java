package edu.security.second.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Slf4j
@Component
public class JwtTokenUtil {

  public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

  private static final SecretKey skey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  private static final String secret = Encoders.BASE64.encode(skey.getEncoded());

  public String getUsernameFromToken(String token) {
    return getClaimFromToken(token, Claims::getId);
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token) {
    //    TODO : read a JWT library's ChangeLog
    return Jwts.parserBuilder()
               .setSigningKey(skey)
               .build()
               .parseClaimsJws(token)
               .getBody();
  }

  private Boolean isTokenExpired(String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  public Date getExpirationDateFromToken(String token) {
    //    TODO : "::" --> className::method
    return getClaimFromToken(token, Claims::getExpiration);
  }

  public String generateToken(String id) {
    return generateToken(id, new HashMap<>());
  }

  private String generateToken(String id, HashMap<String, Object> claims) {
    return doGenerateToken(id, claims);
  }

  private String doGenerateToken(String id, HashMap<String, Object> claims) {
    return Jwts.builder()
               .setClaims(claims)
               .setId(id)
               .setIssuedAt(new Date(System.currentTimeMillis()))
               .setExpiration(new Date((System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)))
               .signWith(skey)
               .compact();
  }

  //  TODO : UserDetails???
  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = getUsernameFromToken(token);
    log.debug("validate Token - Username: " + userDetails.getUsername());
    log.debug("validate Token - UserDetails: " + userDetails.toString());
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }


}
