package dev.j.api.restful.common.component;

import dev.j.api.restful.common.property.PropertyJwtToken;
import dev.j.api.restful.common.property.PropertyLog;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ComponentJwtToken {

    @Value("${ENC_PWD}")
    private String secretKey;

    private long tokenValidMilisecond = 1000L * 60 * 60 * 12; // 토큰 유효 시간 (12시간)

    private Marker marker = MarkerFactory.getMarker( PropertyLog.MARKER_BLOG);

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
    
    @SuppressWarnings("unchecked")
    public List<String> getUserRole(String jwtToken){

        Claims claims = getData(jwtToken);

        if(claims == null || claims.isEmpty()){
            return null;
        }

        List<String> userRoles = new ArrayList<>();

        try {
            userRoles = (List<String>)claims.get(PropertyJwtToken.STR_USER_ROLE);
        } catch (Exception e) {
            log.error(marker, "getUserRole.Exception : " + e.getMessage());
        }

        return userRoles;
    }

    public String getUserId(String jwtToken){

        Claims claims = getData(jwtToken);

        if(claims == null || claims.isEmpty()){
            return "";
        }

        String tokenUserId = "";

        try {
            tokenUserId = (String) claims.get(PropertyJwtToken.STR_USER_ID);
        } catch (Exception e) {
            log.error(marker, "getUserId.Exception : " + e.getMessage());
        }

        return tokenUserId;
    }

    public Claims getData(String jwtToken){
        Jws<Claims> claims;

        try {
            claims = Jwts
                        .parser()
                        .setSigningKey(secretKey)
                        .parseClaimsJws(jwtToken);
            return claims.getBody();
        } catch (ExpiredJwtException e) {
            log.error(marker, "getData.ExpiredJwtException : " + e.getMessage());
            return null;
        } catch (UnsupportedJwtException e) {
            log.error(marker, "getData.UnsupportedJwtException : " + e.getMessage());
            return null;
        } catch (MalformedJwtException e) {
            log.error(marker, "getData.MalformedJwtException : " + e.getMessage());
            return null;
        } catch (SignatureException e) {
            log.error(marker, "getData.SignatureException : " + e.getMessage());
            return null;
        } catch (IllegalArgumentException e) {
            log.error(marker, "getData.IllegalArgumentException : " + e.getMessage());
            return null;
        } catch (Exception e) {
            log.error(marker, "getData.Exception : " + e.getMessage());
            return null;
        } 

    }

	public boolean isValidToken(String jwtToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
        } catch (ExpiredJwtException e) {
            log.error(marker, "isValidToken.ExpiredJwtException : " + e.getMessage());
            return false;
        } catch (SignatureException e) {
            log.error(marker, "isValidToken.SignatureException : " + e.getMessage());
            return false;
        } catch (Exception e) {
            log.error(marker, "isValidToken.Exception : " + e.getMessage());
            return false;
        }

		return true;
    }
    
    public boolean isExpiredToken(String jwtToken) {
 
        if(jwtToken == null || jwtToken.isEmpty() || jwtToken.equals("undefined")){
            return true;
        }

        return !isValidToken(jwtToken);
    }

    public String createToken(String userId) {
        Claims claims = Jwts.claims();
        claims.put(PropertyJwtToken.STR_USER_ID, userId);

        return getJwtToken(claims);
    }

    public String createToken(String userId, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put(PropertyJwtToken.STR_USER_ID, userId);
        claims.put(PropertyJwtToken.STR_USER_ROLE, roles);

        return getJwtToken(claims);
    }

    private String getJwtToken(Claims claims) {
        Date now = new Date();
        
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMilisecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}