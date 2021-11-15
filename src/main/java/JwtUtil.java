import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwtUtil {

    private String secret="143";
    //generate token
    public String generateToken(String id, String subject, String secretKey){
        String token = Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuer("Ranjeet")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(10)))
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(secretKey.getBytes()))
                .compact();
        return token;

    }
    public Claims getClaims(String secret,String token){
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encode(secret.getBytes()))
                .parseClaimsJws(token)
                .getBody();

    }
    public String getUsername(String secret,String token){
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encode(secret.getBytes()))
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
    public boolean validateToken(String username,String token){
        String tokenUsername = getUsername(secret, token);
        return (username.equals(tokenUsername)&&!isTokenExp(token));

    }
    public Date getExpDate(String token){
        return getClaims(secret,token).getExpiration();
    }
    public boolean isTokenExp(String token){
        Date expDate = getExpDate(token);
        return expDate.before(new Date(System.currentTimeMillis()));
    }
}
