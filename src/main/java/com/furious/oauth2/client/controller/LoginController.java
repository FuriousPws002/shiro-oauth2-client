package com.furious.oauth2.client.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @PostMapping("sign")
    public void login(String username, String password, HttpServletResponse response) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        SecurityUtils.getSubject().login(token);
        try {
            response.sendRedirect("/welcome.html");
        } catch (IOException e) {
        }
    }
}
