package com.springboot.demo.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //使用匿名内部类实现接口
        HandlerInterceptor handlerInterceptor=new HandlerInterceptor() {
            public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
                System.out.println("自定义拦截器");
                //必须返回true
                return true;
            }

            public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
                    throws Exception {
            }

            public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
                    throws Exception {
            }
        };
        //配置拦截路径
        registry.addInterceptor(handlerInterceptor).addPathPatterns("/**");
    }
}
