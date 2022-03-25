package com.revelation.api.services;

import com.revelation.api.models.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    public String generatedToken(UserModel user) {
        return Jwts.builder().setSubject(user.getLogin()).signWith(SignatureAlgorithm.HS512, this.secret).compact();
    }

    public String generatedTokenWithExpirationSendEmail(String email, String expiracao) {
        long expString = Long.valueOf(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

        return Jwts.builder().setSubject(email.trim()).setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, this.secret).compact();
    }

    private Optional<Claims> getClaims(String token) throws ExpiredJwtException {
        return Optional.ofNullable((Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody()));
    }

    public Optional<Boolean> tokenIsValid(String token) {
        try {
            Claims claims = getClaims(token).get();
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return Optional.of(!LocalDateTime.now().isAfter(data));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<String> getLoginUser(String token) throws ExpiredJwtException {
        return Optional.ofNullable(this.getClaims(token).get().getSubject());
    }
}
