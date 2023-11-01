package ru.ishepelev.authenticationandauthorization.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;

@Component
public class JwtUtils {
    private final String secret = "984hg493gh0439rthr0429uruj2309yh937gc763fe87t3f89723gf";
    private final Duration lifeTime = Duration.ofMinutes(5);

    public String generateToken(UserDetails userDetails){ //TODO попробовать использовать user вместо UserDetails https://www.youtube.com/watch?v=NIv9TFTSIlg 51 минута если сам не разберусь
        Map<String, Object> claims = new HashMap<>();
        List<String> roleList = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        System.out.println(roleList);
        claims.put("roles",roleList);
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + lifeTime.toMillis());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256,secret).compact();
    }
    public String getLogin(String token){
        return getAllClaimsFromToken(token).getSubject();
    }
    public List<String> getRoles(String token){
        return getAllClaimsFromToken(token).get("roles", List.class);
    }

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
