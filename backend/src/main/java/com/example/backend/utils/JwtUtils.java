package com.example.backend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${spring.security.jwt.key}")
    String key;

    @Value("${spring.security.jwt.expires}")
    int expires;

/**
 *方法返回一个已经签名的JWT字符串，代表了用户的一些基本信息，如用户ID、用户名、权限列表、令牌的签发和过期时间。
 * 这个令牌可以在客户端和服务端之间安全地传递用户的身份信息，服务端可以通过验证令牌的签名来验证令牌的真实性和完整性。
 *
 * JWT是一种用于双方之间安全传输信息的简洁的、URL安全的表示方法
 * 使用java-jwt库中的类和方法来创建和签名令牌
 *  添加id,name的声明,随着添加一个名为authorities的声明，其中包含了用户的权限列表
 *  设置令牌的过期时间和签发时间
 * **/
    public String createJwt(UserDetails details, int id, String username){
        Algorithm algorithm = Algorithm.HMAC256(key);
        Date expire = this.expireTime();
        return JWT.create()
                .withClaim("id",id)
                .withClaim("name",username)
                .withClaim("authorities", details.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .withExpiresAt(expire)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    public Date expireTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR,expires * 24);
        return calendar.getTime();
    }
}
