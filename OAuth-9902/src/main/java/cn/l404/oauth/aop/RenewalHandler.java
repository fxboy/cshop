package cn.l404.oauth.aop;

import cn.l404.common.pojo.ResultVO;
import cn.l404.common.pojo.Token;
import cn.l404.oauth.util.JWTUtils;
import cn.l404.oauth.util.RedisUtils;
import cn.l404.oauth.util.RequstUtils;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther Fanxing
 * 续签操作
 */
@Aspect
@Configuration
public class RenewalHandler {
    @Autowired
    RequstUtils requstUtils;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    JWTUtils jwtUtils;
    @After("@annotation(cn.l404.oauth.annotation.Renewal)")
    public Object renewal(){
        try{
            Claims claims = jwtUtils.getClaimsFromToken(requstUtils.getToken());
            Token token1 = JSON.parseObject(claims.get("sub").toString(),Token.class);
            long ti = redisUtils.getExpire(token1.getAccessToken());
            if(ti <= 60 * 5){
                redisUtils.expire(token1.getAccessToken(),60 * 30);
                System.out.println("续签成功");
            }else{
                System.out.println("无需续签，时间还剩下：" + ti+"s");
            }
        }catch (Exception ex){
            System.out.println("续签操作异常（无需处理）："+ex.getMessage());
        }
        return true;
    }
}
