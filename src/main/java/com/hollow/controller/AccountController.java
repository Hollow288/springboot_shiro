package com.hollow.controller;

import com.hollow.entity.Account;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 刘继涛
 * @version 1.0
 */

@Controller
public class AccountController {

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url ){
        return url;
    }

    @PostMapping("/login")
    public  String login(String username, String password, Model model){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //这样我们就将账号密码交给了shiro来处理,他会进入到我们自定义的Realm方法中
        //并且在这里进入方法后，如果账号不存在会抛出异常，密码错误则会抛出另一个异常，所以在这里要捕获
        try {
            subject.login(token);
            //在这里认证和授权已经结束
            Account account = (Account) subject.getPrincipal();
            //可以把用户信息存到session中,以便于展示到界面上
            subject.getSession().setAttribute("account",account);
            return "index";
        } catch (UnknownAccountException e) {
           e.printStackTrace();
           model.addAttribute("msg","用户名错误！");
           return "login";
        } catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误！");
            e.printStackTrace();
            return "login";
        }
    }

    @GetMapping("/unauth")
    @ResponseBody
    public String unauth(){
        return "未授权，无法访问";
    }

    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        //销毁session
        subject.logout();
        return "login";
    }
}
