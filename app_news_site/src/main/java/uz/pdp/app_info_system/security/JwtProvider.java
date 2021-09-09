package uz.pdp.app_info_system.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.pdp.app_info_system.entity.Lavozim;

import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {
   private static final long expireDate=1000*60*60*24;
   private static final String kalitsoz="blbalblbalbalbfjnsfsdfwiuh21232t4t423t528y187364tudfvhsjdcv";

    public String generateToken(String username, Lavozim lavozims){
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireDate))
                .claim("roles", lavozims.getName())
                .signWith(SignatureAlgorithm.HS512, kalitsoz)
                .compact();

    }

    public String getUserName(String token){
        try {

            return Jwts
                    .parser()
                    .setSigningKey(kalitsoz)
                    .parseClaimsJws(token)
                    .getBody().getSubject();

        }catch (Exception exception){
            return null;
        }

    }
}
