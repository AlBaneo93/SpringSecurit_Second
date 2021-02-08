package edu.security.second.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

  public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

  private static final String secret = "jwtpassword";

  public String getUsernameFromToken(String token) {
    return getClaimFromToken(token, Claims::getId);
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token) {
    //    TODO : read a JWT library's ChangeLog
    return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
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
               .signWith(SignatureAlgorithm.ES512, secret)
               .compact();
  }

  //  TODO : UserDetails???
  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = getUsernameFromToken(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }
}
