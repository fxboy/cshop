package cn.l404.oauth.configuration;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthQqRequest;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther Fanxing
 * 生成授权Request
 */
@Configuration
public class AuthRequestConfiguration {
    @Value("${qq.clientId}")
    String clientId;
    @Value("${qq.clientSecret}")
    String clientSecret;
    @Value("${qq.redirectUri}")
    String redirectUri;
    @Bean
    public AuthRequest authRequest(){
        return new AuthQqRequest(AuthConfig.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .redirectUri(redirectUri)
                .build());
    }
}
