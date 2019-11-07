package com.springboot.demo.controller;

import com.springboot.demo.model.LoginLog;
import com.springboot.demo.service.LogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private LogService logService;

    @RequestMapping("/welcome")
    public ModelAndView index(Model model){
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping("/login")
    public String login(LoginLog loginLog, HttpServletRequest request,Model model){
        ModelAndView mav = new ModelAndView();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String username = loginLog.getUsername();
        String password = loginLog.getPassword();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        System.out.println(username+password);
        System.out.println(token);
        Subject subject= SecurityUtils.getSubject();
        try{
            subject.login(token);
            /*
             * 这里其实是一个登录操作，创建一个subject对象，并使用其中的login方法，传入一个带有用户名密码的对象
             * 进行验证，如果验证失败，那么就进入catch，这样就可以向正常的验证失败一样，返回到login页面，并且带有一段登录失败的语句显示在页面上
             * */
            return "index";
        }catch (AuthenticationException e){
            //model.addAttribute("error","用户名密码错误，请重新登录");
            model.addAttribute("msg",e.getMessage());
            return "login";
        }
    }
}
