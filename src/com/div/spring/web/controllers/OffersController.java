package com.div.spring.web.controllers;

import com.div.spring.web.dao.Offer;
import com.div.spring.web.service.OffersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by Div on 2018-04-11.
 */

@Controller
public class OffersController {
    private OffersService offersService;

    @Autowired
    public void setOffersService(OffersService offersService) {
        this.offersService = offersService;
    }

    @RequestMapping("/offers")
    public String showOffers(Model model) {
        List<Offer> offers = offersService.getCurrentOffers();

        //offersService.throwTestException();

        model.addAttribute("offers", offers);

        return "offers";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String showTest(Model model, @RequestParam("id") String id) {

        System.out.println("ID is " + id);
        return "home";
    }

    @RequestMapping("/createoffer")
    public String createOffer(Model model) {

        model.addAttribute("offer", new Offer());

        return "createoffer";
    }

    @RequestMapping(value = "/docreate", method = RequestMethod.POST)
    public String doCreate(Model model, @Valid Offer offer, BindingResult result, Principal principal) {

        if(result.hasErrors()) {
            return "createoffer";
        }

        String username = principal.getName();

        offer.getUser().setUsername(username);

        offersService.create(offer);

        return "offercreated";
    }
}
