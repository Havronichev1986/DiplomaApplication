package AutoService.DiplomaApplication.services;

import AutoService.DiplomaApplication.entities.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 час
    private static final String SECRET_KEY = "your-secret-key-your-secret-key-your-secret-key!";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String username, Role role) {
        return Jwts.builder()
                .setSubject(username)//имя пользователя
                .claim("role",role.name())
                .setIssuedAt(new Date())// когда выпущен
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))//когда истекает
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();// возврощает jwt  в виде строки
    }


    // достает имя пользователя из токена
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    //проверяет подпись и изввлекает содержимое
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }


    //проверяет что имя пользователя в токене совподает с настоящим и что токен не просрочен
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    //генерирует секретный ключ из строки
    // Важно: SECRET_KEY должен быть достаточно длинным (не меньше 32 байт), иначе будет ошибка
    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}

