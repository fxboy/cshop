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
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public String login() {
        //AuthStateUtils.createState()
       return authRequest.authorize("1");
    }

    @Override
    public Object getInfo(AuthCallback callback) {
        AuthResponse authResponse = authRequest.login(callback);
        if (authResponse.getCode() == 2000){
            System.out.println("登录成功,开始做点事情，生成token");
            //转换成LoginBean
            QQLoginBean qqbean = JSONObject.parseObject(JSON.toJSONString(authResponse.getData()), QQLoginBean.class);
           // System.out.println(qqbean.getToken().getOpenId());
            String access_token = JWTUtils.create(qqbean.getToken(),qqbean.getRawUserInfo(),"qq");
            qqbean.getRawUserInfo().setAvatar(qqbean.getAvatar());
            qqbean.getRawUserInfo().setUsername(qqbean.getUsername());
            qqbean.getRawUserInfo().setLocation(qqbean.getLocation());
            qqbean.getRawUserInfo().setSource(qqbean.getSource());
            Map map = new ConcurrentHashMap();
            map.put("access_token",access_token);
            map.put("userinfo",qqbean.getRawUserInfo());
            return new AuthResponse(2000,"get ok",map);
        }else{
            System.out.println("登录失败");
            return authResponse;
        }

      // return authRequest.login(callback);
    }

    @Override
    public ResultVO verification(String access_token,String role) {
        return JWTUtils.Vercheck(access_token,role);
    }

    @Override
    public ResultVO login(String username, String password) {
        password = PasswordUtils.encypt(password);
        SysUser sysUser= sysUserDao.login(username, password);
        if(sysUser == null){
            return new ResultVO(5000,"error[null]");
        }
        // 根据查出用户创建Token
        Token token = new Token(UUID.randomUUID().toString(),720000,sysUser.getUserId().toString(),UUID.randomUUID().toString(),720000 * 30);
        String access_token = JWTUtils.create(token,sysUser,sysUser.getSysRole().getRoleName());
        Map map = new ConcurrentHashMap();
        map.put("access_token",access_token);
        map.put("userinfo",sysUser);
        return new ResultVO(2000,"登录成功",map);
    }

    @Override
    public ResultVO getinfo(String token) {

        return JWTUtils.getTokenInfo(token);
    }


}
