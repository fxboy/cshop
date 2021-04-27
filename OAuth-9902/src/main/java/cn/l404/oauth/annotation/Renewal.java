package cn.l404.oauth.annotation;

import java.lang.annotation.*;

/**
 *  是否续签注解，如果类上加这个注解，将会自动续签
 *  通过拦截注解
 * */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Renewal {

}
