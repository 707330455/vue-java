package com.example.demo.util.handler;

import com.example.demo.controller.user.TestController;
import com.example.demo.entity.User;
import com.example.demo.util.TokenUse;
import com.example.demo.util.annotation.CheckToken;
import com.example.demo.util.annotation.LoginToken;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 拦截器
 *
 * @author xinfeng
 * @version 1.0
 * @Description
 * @date 2019/7/30 17:54
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Resource
     TokenUse tokenUse;
    @Resource
    TestController testController;
    public static String tokenGet = "";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        // 从http请求头中取出token
        String token = request.getHeader("token");
        this.tokenGet = token;
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        // 如果有LoginToken注释，跳过认证
        if (method.isAnnotationPresent(LoginToken.class)) {
            LoginToken loginToken = method.getAnnotation(LoginToken.class);
            System.out.println(loginToken.required());
            if (loginToken.required()) {
                return true;
            }
        }
        // 检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(CheckToken.class)) {
            CheckToken checkToken = method.getAnnotation(CheckToken.class);
            if (checkToken.required()) {
                if (token == null) {
                    throw new RuntimeException("No Token，Please login again");
                }
            }
            // 获取token中的user id
            String userId = tokenUse.getUserID(token);
            User user =testController.getuser(userId);
            if (user == null) {
                throw new RuntimeException("User does not exist，Please login again");
            }
            boolean bo= TokenUse.tokenVerify(token);
            if (!bo) {
                throw new RuntimeException("Illegal visits！");
            }
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
