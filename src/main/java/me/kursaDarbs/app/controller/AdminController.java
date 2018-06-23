package me.kursaDarbs.app.controller;


import me.kursaDarbs.app.model.*;
import me.kursaDarbs.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

        return mav;
    }

    @RequestMapping(value = "/admin/halls/cinema/{id}", method = RequestMethod.GET)
    public ModelAndView GetCinemaHalls(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView();
        List<Hall> halls = hallRepository.findByCinemaId(id);
        Optional<Cinema> cinema = cinemaRepository.findById(id);
        if(cinema.get() != null) {
            mav.setViewName("admin/halls");
            mav.getModelMap().addAttribute("halls", halls);
            String pageTitle = cinema.get().GetName() + " - halls";
            mav.getModelMap().addAttribute("pageTitle", pageTitle);
        }

        return mav;

    }

    @RequestMapping(value = "/admin/sessions/cinema/{id}", method = RequestMethod.GET)
    public ModelAndView GetAllCinemaSessions(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView();
        Date date = new Date();
        List<Session> upcomingSessions = sessionRepository.findByCinemaIdAndTimeAfter(id, date);
        List<Session> endedSessions = sessionRepository.findByCinemaIdAndTimeBefore(id, date);
        Optional<Cinema> cinema = cinemaRepository.findById(id);
        if(cinema.get() != null) {
            mav.setViewName("admin/sessions");
            mav.getModelMap().addAttribute("upcomingSessions", upcomingSessions);
            mav.getModelMap().addAttribute("endedSessions", endedSessions);
            String pageTitle = cinema.get().GetName() + " - sessions";
            mav.getModelMap().addAttribute("pageTitle", pageTitle);
        }

        return mav;

    }
    // ===============================

    // ===== Get individual ==========
    // ===== (For editing form) ======
    @RequestMapping(value = "/admin/cinema/{id}", method = RequestMethod.GET)
    public String GetCinema() {
        return "admin/cinema";
    }



    @RequestMapping(value = "/admin/session/{id}", method = RequestMethod.GET)
    public String GetAdminSession() {
        return "admin/session";
    }
    // ===============================
}
