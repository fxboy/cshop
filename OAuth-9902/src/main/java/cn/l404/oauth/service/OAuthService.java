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

    //QQ登录后可获取的信息，仅在服务器保存3分钟
    String callback(AuthCallback callback) throws Exception;

    // 后台管理员登录
    ResultVO login(String username,String password) throws Exception;

    ResultVO getinfo(String token);

}
