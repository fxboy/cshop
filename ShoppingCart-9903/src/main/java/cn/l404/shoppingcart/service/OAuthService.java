package cn.l404.shoppingcart.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("oauth")
public interface OAuthService {
    @GetMapping("/login/verification")
    String verification(@RequestParam("access_token") String access_token, @RequestParam("role")String role);

}
