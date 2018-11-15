package com.daqsoft.web;

import com.daqsoft.entity.User;
import com.daqsoft.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 简单描述一下功能
 *
 * @author LaiBin
 * @version 1.0.0
 * @date 2018-10-11 13:58
 * @since JDK 1.8
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "save")
    public void save(User user) {
        userService.save(user);
    }

}
