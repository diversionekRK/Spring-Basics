package com.div.spring.web.controllers;

import com.div.spring.web.dao.Offer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Div on 2018-04-16.
 */

@Controller
public class HomeController {

    @RequestMapping("/")
    public String showHome(Model model) {
        return "home";
    }
}
