package me.kursaDarbs.app.controller;


import me.kursaDarbs.app.custom.Validation;
import me.kursaDarbs.app.model.*;
import me.kursaDarbs.app.multiforms.CinemaForm;
import me.kursaDarbs.app.repository.*;
import me.kursaDarbs.app.service.FileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController  {

    @Autowired
    FileStorage fileStorage;

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
    @Autowired
    CityRepository cityRepository;


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

    /**
        edits
     */
    @RequestMapping(value = "/admin/cinema/{id}", method = RequestMethod.GET)
    public ModelAndView EditCinema(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView();
        Optional<Cinema> cinemaRepo = cinemaRepository.findById(id);
        List<City> cities = cityRepository.findAll();
        if(cinemaRepo.isPresent()) {
            Cinema cinema = cinemaRepo.get();
            mav.setViewName("admin/cinema");
            CinemaForm cinemaForm = new CinemaForm();
            cinemaForm.setId(cinema.GetId());
            cinemaForm.setAddress(cinema.GetAddress());
            cinemaForm.setcinemaName(cinema.GetName());
            cinemaForm.setCity(cinema.GetCity().GetId());
            cinemaForm.setEmail(cinema.GetEmail());
            cinemaForm.setLatitude(cinema.GetLatttidue());
            cinemaForm.setLongitude(cinema.GetLongitude());
            cinemaForm.setPhoneNumber(cinema.GetPhoneNumber());

            mav.getModelMap().addAttribute("cities", cities);
            mav.getModelMap().addAttribute("method", "/admin/cinema/update");
            mav.getModelMap().addAttribute("imgRequired", false);
            mav.getModelMap().addAttribute("cinemaForm", cinemaForm);

            String pageTitle = cinema.GetName() + " - edit";
            mav.getModelMap().addAttribute("pageTitle", pageTitle);
        }
        return mav;
    }

    @RequestMapping(value = "/admin/cinema", method = RequestMethod.GET)
    public ModelAndView AddCinema() {
        ModelAndView mav = new ModelAndView();
        List<City> cities = cityRepository.findAll();
        mav.setViewName("admin/cinema");
        mav.getModelMap().addAttribute("cities", cities);
        mav.getModelMap().addAttribute("method", "/admin/cinema/add");
        mav.getModelMap().addAttribute("cinemaForm", new CinemaForm());
        mav.getModelMap().addAttribute("imgRequired", true);

        String pageTitle = "Add cinema";
        mav.getModelMap().addAttribute("pageTitle", pageTitle);
        return mav;
    }


    @PostMapping("/admin/cinema/add")
    public String AddCinema(@Valid @ModelAttribute("cinemaForm") CinemaForm cinemaForm, BindingResult bindingResult,
                            RedirectAttributes attributes)  {

        Validation validation = new Validation();
        Cinema tempCinema = cinemaRepository.findByName(cinemaForm.getcinemaName());
        if(tempCinema != null) {
            attributes.addFlashAttribute("failed", "Cinema name is not valid.");
        } else if(!validation.IsValidEmail(cinemaForm.getEmail())) {
            attributes.addFlashAttribute("failed", "Email is not valid.");
        } else if(!validation.IsValidPhoneNumber(cinemaForm.getPhoneNumber())) {
            attributes.addFlashAttribute("failed", "Phone number is not valid.");
        } else {
            attributes.addFlashAttribute("success", "Cinema added.");
            Optional<City> city = cityRepository.findById(cinemaForm.getCity());
            Cinema cinema = new Cinema(cinemaForm.getcinemaName(), city.get(), cinemaForm.getAddress(),
                    cinemaForm.getPhoneNumber(), cinemaForm.getEmail(), cinemaForm.getLatitude(), cinemaForm.getLongitude());
            fileStorage.store(cinemaForm.getImg(), "cinema", cinemaForm.getcinemaName());
            cinemaRepository.saveAndFlush(cinema);
        }
        return "redirect:/admin/cinema/";
    }


    @PostMapping("/admin/cinema/update")
    public String UpdateCinema(@Valid @ModelAttribute("cinemaForm") CinemaForm cinemaForm, BindingResult bindingResult,
                            RedirectAttributes attributes)  {
        Optional<Cinema> cinemaRepo = cinemaRepository.findById(cinemaForm.getId());
        Validation validation = new Validation();
        if(cinemaRepo.isPresent()){
            Cinema cinema = cinemaRepo.get();

            if(!validation.IsValidEmail(cinemaForm.getEmail())) {
                attributes.addFlashAttribute("failed", "Email is not valid.");
            }  else if(!validation.IsValidPhoneNumber(cinemaForm.getPhoneNumber())) {
                attributes.addFlashAttribute("failed", "Phone number is not valid.");
            } else {
                // name check
                Boolean isValid = false;
                if(cinemaForm.getcinemaName().equals(cinema.GetName())) {
                    System.out.println("1111111");
                    if(!cinemaForm.getImg().isEmpty()) {
                        fileStorage.store(cinemaForm.getImg(), "cinema", cinema.GetName());
                    }
                    isValid = true;
                } else if(cinemaRepository.findByName(cinemaForm.getcinemaName()) == null) {
                    System.out.println("22222");
                    // adds new file and deletes old one
                    System.out.println("FORM : " + cinemaForm.getImg());
                    if(!cinemaForm.getImg().isEmpty()) {
                        fileStorage.store(cinemaForm.getImg(), "cinema", cinemaForm.getcinemaName());
                        fileStorage.delete("cinema", cinema.GetName());

                    // renames existing
                    } else {
                        System.out.println("333333");
                        fileStorage.update("cinema", cinema.GetName(), cinemaForm.getcinemaName());
                    }
                    isValid = true;
                } else {
                    attributes.addFlashAttribute("failed", "Cinema name already exist.");
                }

                // assigns new values
                if(isValid == true) {
                    Optional<City> city = cityRepository.findById(cinemaForm.getCity());
                    cinema.SetName(cinemaForm.getcinemaName());
                    cinema.SetAddress(cinemaForm.getAddress());
                    cinema.SetCity(city.get());
                    cinema.SetEmail(cinemaForm.getEmail());
                    cinema.SetLongitude(cinemaForm.getLongitude());
                    cinema.SetLattitude(cinemaForm.getLatitude());
                    cinema.SetPhoneNumber(cinemaForm.getPhoneNumber());
                    cinemaRepository.saveAndFlush(cinema);
                }
            }
        } else {
            attributes.addFlashAttribute("failed", "Something went wrong.");
        }
        return "redirect:/admin/cinema/" + cinemaForm.getId();
    }




    @RequestMapping(value = "/admin/session/{id}", method = RequestMethod.GET)
    public String GetAdminSession() {
        return "admin/session";
    }
    // ===============================
}
