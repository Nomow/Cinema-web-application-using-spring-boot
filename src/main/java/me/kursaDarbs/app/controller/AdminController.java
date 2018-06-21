package me.kursaDarbs.app.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
    // ======== Get all =============
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String GetAdminPanel() {
        return "admin/index";
    }

    @RequestMapping(value = "/admin/halls/cinema/{cinemaID}", method = RequestMethod.GET)
    public String GetAdminCinemaHalls() {
        return "admin/halls";
    }
    // ===============================

    // ===== Get individual ==========
    // ===== (For editing form) ======
    @RequestMapping(value = "/admin/cinema/{cinemaID}", method = RequestMethod.GET)
    public String GetAdminCinema() {
        return "admin/cinema";
    }

    @RequestMapping(value = "/admin/hall/{hallID}", method = RequestMethod.GET)
    public String GetAdminHall() {
        return "admin/hall";
    }

    @RequestMapping(value = "/admin/session/{sessionID}", method = RequestMethod.GET)
    public String GetAdminSession() {
        return "admin/session";
    }
    // ===============================
}
