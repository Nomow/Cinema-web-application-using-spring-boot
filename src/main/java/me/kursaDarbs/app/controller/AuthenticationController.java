package me.kursaDarbs.app.controller;


import me.kursaDarbs.app.service.SecurityService;
import me.kursaDarbs.app.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String Login() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
        } else {
             username = principal.toString();
        }

        System.out.println("asssddd "+ username);
        System.out.println("asssddd ");


        if(username.isEmpty() || username.equals("anonymousUser")) {
            return "login";
        } else {
            return "redirect:/";
        }
    }
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String Logout() {
//        return "redirect:/";
//    }
}
