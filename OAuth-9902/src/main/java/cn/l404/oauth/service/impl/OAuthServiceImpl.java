package cn.l404.oauth.service.impl;

import cn.l404.common.pojo.QQLoginBean;
import cn.l404.common.pojo.ResultVO;
import cn.l404.common.pojo.Token;
import cn.l404.common.util.PasswordUtils;
import cn.l404.oauth.configuration.AuthRequestConfiguration;
import cn.l404.oauth.dao.SysUserDao;
import cn.l404.oauth.entity.SysUser;
import cn.l404.oauth.service.OAuthService;
import cn.l404.oauth.util.JWTUtils;
import cn.l404.oauth.util.RedisUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @auther Fanxing
 * 这是一个简介
 */
@Service("oauthservice")
public class OAuthServiceImpl implements OAuthService {
    @Autowired
    AuthRequest authRequest;

    @Autowired
    SysUserDao sysUserDao;

    @Value("${fx.redirectUri}")
    String myRedirectUri;

    @Autowired
    JWTUtils jwtUtils;
    @Autowired
    RedisUtils redisUtils;
    @Override
    public String login() {
        //AuthStateUtils.createState()
       return authRequest.authorize(AuthStateUtils.createState());
    }

    @Override
    public Object getInfo(AuthCallback callback) {
        AuthResponse authResponse = authRequest.login(callback);
        if (authResponse.getCode() == 2000){
            System.out.println("登录成功,开始做点事情，生成token");

            //转换成LoginBean
            QQLoginBean qqbean = JSONObject.parseObject(JSON.toJSONString(authResponse.getData()), QQLoginBean.class);
           // System.out.println(qqbean.getToken().getOpenId());
            // 清除先前的token
            jwtUtils.clear(qqbean.getToken().getOpenId());
            // 将用户的accesstoken转成openid
            qqbean.getToken().setAccessToken(qqbean.getToken().getOpenId());
            // 重置为新的reftoken，注意的是用户信息一定要保存好，因为把刷新id和acctoken给替换成自己的了
            qqbean.getToken().setRefreshToken(UUID.randomUUID().toString());
            String access_token = jwtUtils.create(qqbean.getToken(),JSON.toJSONString(qqbean.getRawUserInfo()),"qq");
            qqbean.getRawUserInfo().setAvatar(qqbean.getAvatar());
            qqbean.getRawUserInfo().setUsername(qqbean.getUsername());
            qqbean.getRawUserInfo().setLocation(qqbean.getLocation());
            qqbean.getRawUserInfo().setSource(qqbean.getSource());
            Map map = new ConcurrentHashMap();
            map.put("access_token",access_token);
            map.put("userinfo",qqbean.getRawUserInfo());
            redisUtils.put(callback.getCode(),JSON.toJSONString(new AuthResponse(2000,"get ok",map)),60 * 3);
            return myRedirectUri + "?code="+callback.getCode();
        }else{
            System.out.println("登录失败");
            return authResponse;
        }
      // return authRequest.login(callback);
    }



    @Override
    public ResultVO verification(String access_token,String role) {
        System.out.println("vaeification:" + access_token);
        return jwtUtils.Vercheck(access_token,role);
    }

    @Override
    public String callback(AuthCallback callback) throws Exception {
       Object value = redisUtils.get(callback.getCode());
       if(value == null){
           throw new Exception("登录状态失效，请重新登录吧！");
       }
       return value.toString();
    }

    @Override
    public ResultVO login(String username, String password) throws Exception {
        password = PasswordUtils.encypt(password);
        SysUser sysUser= sysUserDao.login(username, password);
        if(sysUser == null){
            throw new Exception("请检查用户的账号密码是否错误");
        }
        // 根据查出用户创建Token,access_token为 用户名md5加密
        Token token = new Token(PasswordUtils.md5encypt(sysUser.getUserUsername()),720000,sysUser.getUserId().toString(),UUID.randomUUID().toString(),720000 * 30);
        jwtUtils.clear(token.getAccessToken()); // 清空token
        String access_token = jwtUtils.create(token,JSON.toJSONString(sysUser),sysUser.getSysRole().getRoleName());
        Map map = new ConcurrentHashMap();
        map.put("access_token",access_token);
        map.put("userinfo",sysUser);
        return new ResultVO(2000,"登录成功",map);
    }

    @Override
    public ResultVO getinfo(String token) {
        return jwtUtils.getTokenInfo(token);
    }


}
