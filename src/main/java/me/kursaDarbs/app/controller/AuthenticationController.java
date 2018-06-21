package me.kursaDarbs.app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String Login() {
        return "login";
    }
}
