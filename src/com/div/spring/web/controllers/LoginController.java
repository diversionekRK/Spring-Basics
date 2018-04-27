package com.div.spring.web.controllers;

import com.div.spring.web.dao.FormValidationGroup;
import com.div.spring.web.dao.Offer;
import com.div.spring.web.dao.User;
import com.div.spring.web.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Div on 2018-04-18.
 */

@Controller
public class LoginController {

    private UsersService usersService;

    @Autowired
    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping("/login")
    public String showLogin() {
        return "login";
    }

    @RequestMapping("/loggedout")
    public String showLoggedOut() {
        return "loggedout";
    }

    @RequestMapping("/accessdenied")
    public String showAccessDenied() {
        return "accessdenied";
    }

    @RequestMapping("/admin")
    public String showAdmin(Model model) {

        try {
            List<User> users = usersService.getAllUsers();

            model.addAttribute("users", users);
        } catch (Exception ex) {
            System.out.println(ex.getClass());
        }

        return "admin";
    }

    @RequestMapping("/newaccount")
    public String showNewAccount(Model model) {

        model.addAttribute("user", new User());

        return "newaccount";
    }

    @RequestMapping(value = "/createaccount", method = RequestMethod.POST)
    public String createAccount(@Validated(FormValidationGroup.class) User user, BindingResult result) {

        if(result.hasErrors()) {
            return "newaccount";
        }

        if(usersService.exists(user.getUsername())) {
            result.rejectValue("username", "DuplicateKey.user.username");
            return "newaccount";
        }

        user.setEnabled(true);
        user.setAuthority("ROLE_USER");

        usersService.create(user);

        return "accountcreated";
    }
}
