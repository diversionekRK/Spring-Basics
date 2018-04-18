package com.div.spring.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Div on 2018-04-18.
 */

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String showLogin() {
        return "login";
    }
}
