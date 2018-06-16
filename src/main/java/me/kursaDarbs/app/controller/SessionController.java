package me.kursaDarbs.app.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import me.kursaDarbs.app.custom.SessionProcessing;
import me.kursaDarbs.app.model.Session;
import me.kursaDarbs.app.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;


@RestController
public class SessionController {

    @Autowired
    SessionRepository sessionRepository;

    @RequestMapping(value = "/session/{id}", method = RequestMethod.GET)
    public ModelAndView GetSession(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView();
        Date currentDate = Calendar.getInstance().getTime();
        Optional<Session> sessionRepo = sessionRepository.findByIdAndTimeAfter(id, currentDate);
        if (sessionRepo.isPresent()) {
            mav.setViewName("session");
            SessionProcessing sessionProcessing = new SessionProcessing(sessionRepo.get());
            // returnx m x n array of bool seats 0 not taken 1 taken and other methods
            mav.getModelMap().addAttribute("session", sessionProcessing);
            String pageTitle = sessionRepo.get().GetCinema() + " - " + sessionRepo.get().GetMovie().GetName();
            mav.getModelMap().addAttribute("pageTitle", pageTitle);
        }
        return mav;
    }


}

