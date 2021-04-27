package cn.l404.shoppingcart.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @auther Fanxing
 * 这是一个简介
 */

@Component
public class RequstUtils {
    @Value("${token.name}")
    String tname;

    @Resource
    HttpServletRequest httpServletRequest;

    @Autowired
    RedisUtils redisUtils;
    // 获取头后从redis查出token信息
    public String getToken(){
        System.out.println("Request:" + httpServletRequest.getHeader(tname));
        return httpServletRequest.getHeader(tname);
    }
}
