package com.daqsoft.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping(value = "/admin")
public class AdminController {

    @GetMapping(value = "/index")
    public String index() {
        return "/admin/index";
    }

}
