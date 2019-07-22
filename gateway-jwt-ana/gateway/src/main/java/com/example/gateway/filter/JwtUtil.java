package com.example.gateway.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * jwt 功能
 * @author Ryze
 * @date 2019-07-22 15:01
 */
public class JwtUtil {

    public static final String SECRET = "qazwsx123444$#%#()*&& asdaswwi1235 ?;!@#kmmmpom in***xx**&";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_AUTH = "Authorization";


    public static String generateToken(String user) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", new Random().nextInt());
        map.put("user", user);
        String jst = Jwts.builder()
            .setSubject("user info")
            .setClaims(map)
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();
        String finalJwt = TOKEN_PREFIX + " " + jst;
        return finalJwt;
    }

    public static Map<String, String> validateToken(String token) {
        if (token != null) {
            HashMap<String, String> map = new HashMap<>();
            Map<String, Object> body = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody();
            String id = String.valueOf(body.get("id"));
            String user = (String) (body.get("user"));
            map.put("id", id);
            map.put("user", user);
            if (StringUtils.isEmpty(user)) {
                throw new PermissionException("用户信息不存在，请检查");
            }
            return map;
        } else {
            throw new PermissionException(" token 错误，请检查");
        }

    }
}
