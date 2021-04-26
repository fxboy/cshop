package cn.l404.oauth.util;

import cn.l404.common.pojo.ResultVO;
import cn.l404.common.pojo.Token;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther Fanxing
 * 这是一个简介
 */

public class JWTUtils {
    private static String secret = "Fanxing";
    private static Long expiration = 7200000L;
    private static String header = "authentication";

    // 获取本系统的token
    public static String create(Token token,Object info,String role){
        //此处查询权限并授权
        Map<String, Object> claims = new HashMap();
        claims.put("sub", JSON.toJSONString(token));
        claims.put("role", role);
        claims.put("info", info);
        claims.put("created", new Date());
        System.out.println("创建：" + claims);
        return generateToken(claims);
    }

    /**
     * 从claims生成令牌,如果看不懂就看谁调用它
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private static String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);
        return Jwts.builder().setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public static ResultVO getTokenInfo(String token){
        if(check(token)) {
            Claims claims = getClaimsFromToken(token);
            return new ResultVO(2000,"ok",claims.get("info"));
        }
        return new ResultVO(401,"Not logged in",false);
    }


    // 解析本系统的token
    public static ResultVO<Boolean> Vercheck(String token,String role){
        // 开始解析出token包含的信息，解析出所携带的权限
        //这里是从token解析出的权限
        if(check(token)) {
            // 开始解析token
            Claims claims = getClaimsFromToken(token);
            String roles = claims.get("role").toString();

            if(role == null){
                return new ResultVO(2000,"ok",true);
            }
            if(roles.equals(role)){
                return new ResultVO(2000,"ok",true);
            }
            return new ResultVO(403,"no permission ",false);
        }
        return new ResultVO(401,"Not logged in",false);

    }

    public static boolean check(String token){
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            System.out.println("checkToken:过期了吗？:"+ expiration.getTime() +"||"+System.currentTimeMillis());
            return (expiration.getTime() > System.currentTimeMillis());
        } catch (Exception e) {
            System.out.println("checkToken:"+ e.getMessage());
            return false;
        }
    }

    /**
     * 从令牌中获取数据声明,如果看不懂就看谁调用它
     *
     * @param token 令牌
     * @return 数据声明
     */
    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
}
