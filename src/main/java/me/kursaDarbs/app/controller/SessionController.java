package me.kursaDarbs.app.controller;

import java.io.IOException;
import java.util.*;

import me.kursaDarbs.app.custom.CinemaMovieSessions;
import me.kursaDarbs.app.custom.SeatPurchaseValidator;
import me.kursaDarbs.app.custom.SessionProcessing;
import me.kursaDarbs.app.model.BoughtSeats;
import me.kursaDarbs.app.model.Session;
import me.kursaDarbs.app.repository.BoughtSeatsRepository;
import me.kursaDarbs.app.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;


@Controller
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
            mav.getModelMap().addAttribute("sesija", sessionProcessing);
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

    @Autowired
    BoughtSeatsRepository boughtSeatsRepository;

    @RequestMapping(value = "/buySeats", method = RequestMethod.POST)
    public String testRequest(@RequestParam String firstname, @RequestParam String lastname,
                              @RequestParam String email, @RequestParam String paymentSystem,
                              @RequestParam List<Integer> seatArray, @RequestParam Integer sessionId,
                                RedirectAttributes attributes)  {


        System.out.println("firstname: "+firstname);
        System.out.println("lastname: "+lastname);
        System.out.println("email: "+email);
        System.out.println("paymentSystem: "+paymentSystem);
        System.out.println("seatArray: "+ seatArray);
        System.out.println("seatArray: "+ seatArray);
        System.out.println("sessionId: "+ sessionId);
        List<BoughtSeats> boughtSeats = boughtSeatsRepository.findBySessionId(sessionId);
        Boolean validSeats = true;
        SeatPurchaseValidator validator = new SeatPurchaseValidator();
        if(!validator.HaveOnlyLetters(firstname)) {
            attributes.addFlashAttribute("failed", "First name is not valid.");
        } else if(!validator.HaveOnlyLetters(lastname)) {
            attributes.addFlashAttribute("failed", "Last name is not valid.");
        } else if(!validator.IsValidEmail(email)) {
            attributes.addFlashAttribute("failed", "Email is not valid.");
        } else if(!validator.seatsAreValid(seatArray, boughtSeats)) {
            attributes.addFlashAttribute("failed", "Sorry, someone bought some seats before you.");
        } else {
            attributes.addFlashAttribute("succcess", "Tickets bought.");
        }
        return "redirect:session/"+ sessionId;

    }
}

