package me.kursaDarbs.app.controller;


import me.kursaDarbs.app.model.Cinema;
import me.kursaDarbs.app.model.Movie;
import me.kursaDarbs.app.model.User;
import me.kursaDarbs.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    CinemaRepository cinemaRepository;
    @Autowired
    HallRepository hallRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SessionRepository sessionRepository;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)

    public ModelAndView GetAdminPanel() {
        ModelAndView mav = new ModelAndView();
        List<Cinema> cinemas = cinemaRepository.findAll();
        List<Movie> movies = movieRepository.findAll();
        List<User> users = userRepository.findAll();
        mav.setViewName("admin/index");
        mav.getModelMap().addAttribute("cinemas", cinemas);
        mav.getModelMap().addAttribute("movies", movies);
        mav.getModelMap().addAttribute("users", users);
        mav.getModelMap().addAttribute("pageTitle", "Admin panel");

        for(int i =0; i < users.size(); ++i) {
            if(users.get(i).GetCinema() == null) {
               System.out.println( users.get(i).getUsername() + " IS NULL");
            }
        }

        return mav;
    }

    @RequestMapping(value = "/admin/halls/cinema/{cinemaId}", method = RequestMethod.GET)
    public String GetCinemaHalls() {
        return "admin/halls";
    }

    @RequestMapping(value = "/admin/sessions/{cinemaId}", method = RequestMethod.GET)
    public String GetAllCinemaSessions() {
        return "admin/sessions";
    }

    // ===============================

    // ===== Get individual ==========
    // ===== (For editing form) ======
    @RequestMapping(value = "/admin/cinema/{cinemaID}", method = RequestMethod.GET)
    public String GetCinema() {
        return "admin/cinema";
    }



    @RequestMapping(value = "/admin/session/{sessionID}", method = RequestMethod.GET)
    public String GetAdminSession() {
        return "admin/session";
    }
    // ===============================
}
