package cn.l404.oauth.util;

import cn.l404.common.pojo.ResultVO;
import cn.l404.common.pojo.Token;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther Fanxing
 * 这是一个简介
 */
@Component
public class JWTUtils {
    private static String secret = "Fanxing";
    private static Long expiration = 7200000L;
    private static String header = "authentication";

    @Autowired
    RedisUtils redisUtils;

    public void clear(String access_token){
        redisUtils.del(access_token);
    }
    // 获取本系统的token
    public String create(Token token,Object info,String role){
        //此处查询权限并授权
        Map<String, Object> claims = new HashMap();

        claims.put("sub", JSON.toJSONString(token));
        claims.put("role", role);
        claims.put("info", info);
        claims.put("created", new Date());
        System.out.println("创建：" + claims);
        String access_token = generateToken(claims);
        //设置半小时权限
        redisUtils.put(token.getAccessToken(),token.getRefreshToken(),60 * 30);
        return access_token;
    }

    /**
     * 从claims生成令牌,如果看不懂就看谁调用它
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);
        return Jwts.builder().setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public ResultVO getTokenInfo(String token){
        if(check(token)) {
            Claims claims = getClaimsFromToken(token);
            return new ResultVO(2000,"ok",claims.get("info"));
        }
        return new ResultVO(401,"Not logged in",false);
    }


    // 解析本系统的token
    public ResultVO<Boolean> Vercheck(String token,String role){
        // 开始解析出token包含的信息，解析出所携带的权限
        //这里是从token解析出的权限
        if(check(token)) {
            // 开始解析token
            Claims claims = getClaimsFromToken(token);
            String roles = claims.get("role").toString();

            if(role == null || role.equals("")){
                return new ResultVO(2000,"ok",true);
            }
            if(roles.equals(role)){
                return new ResultVO(2000,"ok",true);
            }
            return new ResultVO(403,"no permission ",false);
        }
        return new ResultVO(401,"Not logged in",false);

    }

    public boolean check(String token){
        System.out.println(token);
        try {
            Claims claims = getClaimsFromToken(token);
            // 取出access_token和res_token
            System.out.println("SUB:" + claims.get("sub").toString());
            Token token1 = JSON.parseObject(claims.get("sub").toString(),Token.class);
            System.out.println("CheckToken:token1:" + token1.toString());
            Date expiration = claims.getExpiration();
            boolean da = (expiration.getTime() > System.currentTimeMillis());
            ;
            //获取日期是否处于正常状态
            if(da){
                // 日期处于正常状态，开始校验是否唯一登录,如果取空值的话，抛出异常
                String tkacc = redisUtils.get(token1.getAccessToken()) == null?null:redisUtils.get(token1.getAccessToken()).toString();
                System.out.println("checkToken:tkacc:"+tkacc);
                if(tkacc == null){
                    return false;
                }

                if(tkacc.equals(token1.getRefreshToken())){
                    return true;
                }
                return false;
            }else{
                clear(token1.getAccessToken());
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
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
    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
}
