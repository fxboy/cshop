package cn.l404.oauth.service;

import cn.l404.common.pojo.ResultVO;
import me.zhyd.oauth.model.AuthCallback;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
@Service
public interface OAuthService {
    // 前台的QQ登录
    String login();
    Object getInfo(AuthCallback callback);
    ResultVO verification(String access_token,String role);


    // 后台管理员登录
    ResultVO login(String username,String password);

    ResultVO getinfo(String token);

}
