package cn.l404.oauth.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.*;

/**
 * @Author Fanxing
 * 自定义权限校验的注解
 * */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping()
@Documented
public @interface HasRoleMapping {
    @AliasFor(annotation = RequestMapping.class)
    String name() default "";
    @AliasFor(annotation = RequestMapping.class)
    String[] value() default {};
    String role() default "fffff";
    boolean allow() default true;

}
