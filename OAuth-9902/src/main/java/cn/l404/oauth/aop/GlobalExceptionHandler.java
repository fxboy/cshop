package cn.l404.oauth.aop;


import cn.l404.common.pojo.ResultVO;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther Fanxing
 * 处理拦截全部异常 OAUTH模块（用户权限信息中心模块）
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVO doError(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                            Exception ex) {
        System.out.println("异常处理中心："+ex.getMessage());
        //ex.printStackTrace();
        return new ResultVO(5000,ex.getMessage());
    }
}
