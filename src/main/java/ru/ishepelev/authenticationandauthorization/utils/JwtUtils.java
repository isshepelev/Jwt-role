package ru.ishepelev.authenticationandauthorization.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.ishepelev.authenticationandauthorization.domain.Role;
import ru.ishepelev.authenticationandauthorization.domain.User;


import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    private String secret = "984hg493gh0439rthr0429uruj2309yh937gc763fe87t3f89723gf";
    private Duration lifeTime = Duration.ofMinutes(30);

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        Collection<Role> rolesList = user.getRoles();
        claims.put("roles", rolesList);
        claims.put("name",user.getName());
        claims.put("password", user.getPassword());

        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + lifeTime.toMillis());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getLogin())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getLogin(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public List<?> getRoles(String token) {
        return (List<?>) getAllClaimsFromToken(token).get("roles");
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
