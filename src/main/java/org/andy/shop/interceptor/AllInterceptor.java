package org.andy.shop.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by Paul on 16/4/4.
 */
public class AllInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        return true;
//        if (httpServletRequest.getParameter("haha").equals("xixi")) {
//            return true;
//        }else {
//            httpServletResponse.setContentType("text/plain;charset=utf-8");
//            PrintWriter writer = httpServletResponse.getWriter();
//            writer.println("{\"msg\":\"请求失败\",\"code\":\"0\"}");
//            writer.flush();
//            writer.close();
//            return false;
//        }
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
