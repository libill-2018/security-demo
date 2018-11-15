package com.daqsoft.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录控制器
 *
 * @author LaiBin
 * @version 1.0.0
 * @date 2018-5-16 11:08
 * @since JDK 1.8
 */
@Controller
public class LoginController {

    @RequestMapping(value = {"/index"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = {"/loginIndex"})
    public String loginIndex() {
        return "/user/index";
    }

    @RequestMapping(value = {"/login", "/"})
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

}
