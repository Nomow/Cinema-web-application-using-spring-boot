package me.kursaDarbs.app.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AdminController {
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String GetCinemaList() {
        return "index";
    }

}
