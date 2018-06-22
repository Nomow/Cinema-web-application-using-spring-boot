package me.kursaDarbs.app.controller;

import me.kursaDarbs.app.custom.*;
import me.kursaDarbs.app.model.BoughtSeats;
import me.kursaDarbs.app.model.PaymentSystem;
import me.kursaDarbs.app.model.Session;
import me.kursaDarbs.app.repository.BoughtSeatsRepository;
import me.kursaDarbs.app.repository.PaymentSystemRepository;
import me.kursaDarbs.app.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;


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
            String pageTitle = sessionRepo.get().GetCinema().GetName() + " - " + sessionRepo.get().GetMovie().GetName();
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
    @Autowired
    PaymentSystemRepository paymentSystemRepository;

    @RequestMapping(value = "/buySeats", method = RequestMethod.POST)
    public String testRequest(@RequestParam String firstname, @RequestParam String lastname,
                              @RequestParam String email, @RequestParam Integer paymentSystem,
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
        Optional<Session> sessionRepo = sessionRepository.findById(sessionId);
        Optional<PaymentSystem> paymentSystemRepo = paymentSystemRepository.findById(paymentSystem);
        if (sessionRepo.isPresent()) {
            SeatPurchaseProcessing processor = new SeatPurchaseProcessing();
            if (!processor.HaveOnlyLetters(firstname)) {
                attributes.addFlashAttribute("failed", "First name is not valid.");
            } else if (!processor.HaveOnlyLetters(lastname)) {
                attributes.addFlashAttribute("failed", "Last name is not valid.");
            } else if (!processor.IsValidEmail(email)) {
                attributes.addFlashAttribute("failed", "Email is not valid.");
            } else if (!processor.seatsAreValid(seatArray, boughtSeats)) {
                attributes.addFlashAttribute("failed", "Sorry, someone bought some seats before you.");
            } else {
                attributes.addFlashAttribute("succcess", "Tickets bought.");
                List<List<Integer>> rowCol = processor.ind2Sub(seatArray, sessionRepo.get().GetHall().GetRows());
                List<String> orderNumbers =  processor.GenerateOrderNumber(sessionId, rowCol);
                Email emailService = new Email();
                for(int i = 0; i < orderNumbers.size(); ++i) {
                    // gets data to put in pdf
                    String cinemaName = sessionRepo.get().GetCinema().GetName();
                    String city = sessionRepo.get().GetCinema().GetCity().GetName();
                    String address = sessionRepo.get().GetCinema().GetAddress();
                    String movie = sessionRepo.get().GetMovie().GetName();
                    Integer row = rowCol.get(i).get(0);
                    Integer col = rowCol.get(i).get(1);
                    String orderNumber = orderNumbers.get(i);
                    double price = sessionRepo.get().GetPrice();
                    Date date = sessionRepo.get().GetTime();
                    PdfProcessing pdf = new PdfProcessing(cinemaName, city, address, movie, date,
                                                          row + 1, col + 1, orderNumber, price);

                    // sends to email
                    String title = cinemaName + " - " + movie + " - " + orderNumber;
                    String content = "Thank you for purchasing ticket. \nYour order number: " + orderNumber +
                                     ".\n Below is pdf ticket file";
                    emailService.Send(email, title, content, pdf);

                    BoughtSeats newBoughtSeats = new BoughtSeats(sessionRepo.get(), paymentSystemRepo.get(),
                                                    row, col, email, orderNumber, firstname, lastname);
                    boughtSeatsRepository.save(newBoughtSeats);
                }
            }
            boughtSeatsRepository.flush();
        } else {
            attributes.addFlashAttribute("failed", "Something went wrong.");
        }
        return "redirect:session/"+ sessionId;

    }
}

