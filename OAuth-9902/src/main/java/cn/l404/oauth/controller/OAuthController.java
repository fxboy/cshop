package cn.l404.oauth.controller;

import cn.l404.oauth.annotation.HasRoleMapping;
import cn.l404.oauth.annotation.Renewal;
import cn.l404.oauth.service.OAuthService;
import cn.l404.oauth.util.RequstUtils;
import com.alibaba.fastjson.JSON;
import me.zhyd.oauth.model.AuthCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  @Autowired
  RequstUtils requstUtils;

  @RequestMapping("/render")
  public void renderAuth(HttpServletResponse response) throws IOException {
    response.sendRedirect(oAuthService.login());
  }

  @RequestMapping("/qq")
  public void getInfo(AuthCallback callback,HttpServletResponse response) throws IOException {
    response.sendRedirect(oAuthService.getInfo(callback).toString());
  }

  @Renewal
  @RequestMapping(value = "/verification")
  public String verification(String access_token,String role) {
    return JSON.toJSONString(oAuthService.verification(access_token, role));
  }

  @RequestMapping("/login")
  public String login(String username, String password) throws Exception {
    System.out.println(username + "===" + password);
    return  JSON.toJSONString(oAuthService.login(username, password));
  }

  @Renewal
  @RequestMapping("/getinfo")
  public String getinfo(){
    return JSON.toJSONString(oAuthService.getinfo(requstUtils.getToken()));
  }

  @RequestMapping("/callback")
  public String callback(AuthCallback authCallback) throws Exception {
    return oAuthService.callback(authCallback);
  }
}
