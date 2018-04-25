package com.div.spring.web.controllers;

import com.div.spring.web.dao.Offer;
import com.div.spring.web.service.OffersService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

/**
 * Created by Div on 2018-04-16.
 */

@Controller
public class HomeController {

    private static Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    private OffersService offersService;

    @RequestMapping("/")
    public String showHome(Model model, Principal principal) {

        List<Offer> offers = offersService.getCurrentOffers();
        model.addAttribute("offers", offers);

        boolean hasOffer = false;

        if(principal != null) {
            hasOffer = offersService.hasOffer(principal.getName());
        }

        model.addAttribute("hasOffer", hasOffer);

        return "home";
    }
}
