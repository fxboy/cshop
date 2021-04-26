package cn.l404.oauth.controller;

import cn.l404.oauth.service.OAuthService;
import com.alibaba.fastjson.JSON;
import me.zhyd.oauth.model.AuthCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther Fanxing
 * 这是一个简介
 */
@RestController
@RequestMapping("/login")
public class OAuthController {
  @Qualifier("oauthservice")
  @Autowired
  OAuthService oAuthService;

  @RequestMapping("/render")
  public void renderAuth(HttpServletResponse response) throws IOException {
    response.sendRedirect(oAuthService.login());
  }

  @RequestMapping("/qq")
  public String getInfo(AuthCallback callback) {
    return JSON.toJSONString(oAuthService.getInfo(callback));
  }

  @RequestMapping("/verification")
  public String verification(String access_token,String role) {
    return JSON.toJSONString(oAuthService.verification(access_token, role));
  }

  @RequestMapping("/login")
  public String login(String username,String password){
    return  JSON.toJSONString(oAuthService.login(username, password));
  }

  @RequestMapping("/getinfo")
  public String getinfo(String access_token){
    return JSON.toJSONString(oAuthService.getinfo(access_token));
  }
}
