package com.div.spring.web.controllers;

import com.div.spring.web.dao.Offer;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Div on 2018-04-16.
 */

@Controller
public class HomeController {

    Logger logger = Logger.getLogger(HomeController.class);

    @RequestMapping("/")
    public String showHome() {

        logger.info("Using logging from home controller.");

        return "home";
    }
}
