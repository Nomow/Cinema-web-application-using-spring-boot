package me.kursaDarbs.app.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import me.kursaDarbs.app.custom.CinemaMovieSessions;
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
    // gets information about specific session
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

    // gets all sessions of the movie for specific cinema
    @RequestMapping(value = "cinema/{cinemaId}/movie/{movieId}", method = RequestMethod.GET)
    public ModelAndView GetAllSessionsForMovie(@PathVariable("cinemaId") int cinemaId, @PathVariable("movieId") int movieId) {
        ModelAndView mav = new ModelAndView();
        Date currentDate = Calendar.getInstance().getTime();
        List<Session> sessionRepo = sessionRepository.findByCinemaIdAndMovieIdAndTimeAfter(cinemaId, movieId, currentDate);


        if(!sessionRepo.isEmpty()) {
            mav.setViewName("sessions");
            CinemaMovieSessions movieSessions = new CinemaMovieSessions(sessionRepo);
            mav.getModelMap().addAttribute("movie", movieSessions.GetMovies().get(0));
            mav.getModelMap().addAttribute("sessions", movieSessions.GetSessions().get(0));
            String pageTitle = movieSessions.GetSessions().get(0).get(0).GetCinema().GetName() + " - " +
                    movieSessions.GetMovies().get(0).GetName();
            mav.getModelMap().addAttribute("pageTitle", pageTitle);


        }
        return mav;
    }

}

