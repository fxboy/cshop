package cn.l404.oauth.aop;

import cn.l404.common.pojo.ResultVO;
import cn.l404.oauth.annotation.HasRoleMapping;
import cn.l404.oauth.util.JWTUtils;
import cn.l404.oauth.util.RequstUtils;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.sql.ResultSet;

/**
 * @auther Fanxing
 * 这是一个简介
 */
@Aspect
@Component
public class HasRoleHandler {
    @Autowired
    RequstUtils requstUtils;
    @Autowired
    JWTUtils jwtUtils;

    @Around("@annotation(cn.l404.oauth.annotation.HasRoleMapping)")
    public Object MethodhashRole(ProceedingJoinPoint pjp) throws Throwable {
        // 获取当前方法上的注解
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        HasRoleMapping hasRoleMapping = targetMethod.getAnnotation(HasRoleMapping.class);
        // 获取到方法的权限
        String role = hasRoleMapping.role();
        ResultVO resultVO = jwtUtils.Vercheck(requstUtils.getToken(),role.equals("fffff")?null:role);
        Integer code = resultVO.getCode();
        //这个为true是只允许
        if((hasRoleMapping.allow() && code == 2000) || (hasRoleMapping.allow()) == false && code == 403){
            System.out.println("放行");
            return pjp.proceed();
        }
        if(code == 401 || ((code == 403) && hasRoleMapping.allow()) || ((hasRoleMapping.allow()) == false && code == 2000)){
            System.out.println("被拦截");
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletResponse response = attributes.getResponse();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Type","text/html;character=utf-8");
            response.getWriter().print(JSON.toJSONString(resultVO));
            return null;
        }

        throw new Exception("服务异常");
    }
}
