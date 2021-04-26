package cn.l404.oauth.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther Fanxing
 * 这是一个简介
 */

@Component
public class RequstUtils {
    @Value("${token.name}")
    String tname;
    @Autowired
    HttpServletRequest httpServletRequest;
    public String getToken(){
        return httpServletRequest.getHeader(tname);
    }
}
