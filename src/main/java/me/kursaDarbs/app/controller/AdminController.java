package me.kursaDarbs.app.controller;


import com.sun.javafx.collections.MappingChange;
import me.kursaDarbs.app.custom.Validation;
import me.kursaDarbs.app.model.*;
import me.kursaDarbs.app.multiforms.CinemaForm;
import me.kursaDarbs.app.multiforms.MovieForm;
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
import java.lang.reflect.Array;
import java.util.*;

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
        List<List<Session>> cinemaSessions =  new ArrayList<List<Session>>();
        Date date = new Date();
        for(int i = 0; i < movies.size(); ++i) {
            List<Session> temp = sessionRepository.findByMovieIdAndTimeAfter(movies.get(i).GetId(), date);
            cinemaSessions.add(temp);
        }

        Map<Movie, List<Session>> movieMap = new HashMap<>();
        for(int i = 0; i < movies.size(); ++i) {
            movieMap.put(movies.get(i), cinemaSessions.get(i));
        }
        mav.getModelMap().addAttribute("cinemas", cinemas);
        mav.getModelMap().addAttribute("movies", movieMap);
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
     CINEMA POST GET
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


    /**
     MOVIE  INPUT
     */
    @Autowired
    GenresRepository genresRepository;

    @RequestMapping(value = "/admin/movie", method = RequestMethod.GET)
    public ModelAndView GetCinema() {
        ModelAndView mav = new ModelAndView();


        List<Genres> genres =  genresRepository.findAll();
        Map<Genres, Boolean> genreMap = new HashMap<>();


        for(int i = 0; i < genres.size(); ++i) {
            genreMap.put(genres.get(i), false);
        }


        mav.setViewName("admin/movie");
        mav.getModelMap().addAttribute("method", "/admin/movie/add");
        mav.getModelMap().addAttribute("movieForm", new MovieForm());
        mav.getModelMap().addAttribute("imgRequired", true);
        mav.getModelMap().addAttribute("movieGenres", genreMap);
        String pageTitle =  "Movie add";
        mav.getModelMap().addAttribute("pageTitle", pageTitle);

        return mav;
    }
    @RequestMapping(value = "/admin/movie/{id}", method = RequestMethod.GET)
    public ModelAndView EditMovie(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView();
        Optional<Movie> movieRepo = movieRepository.findById(id);
        if(movieRepo.isPresent()) {
            List<Genres> genres =  genresRepository.findAll();
            Map<Genres, Boolean> genreMap = new HashMap<>();

            List<Integer> genreList = new ArrayList<>();
            Movie movie = movieRepo.get();

            for(int i = 0; i < movie.GetGenres().size(); ++i) {
                genreList.add(movie.GetGenres().get(i).GetId());
            }
            for(int i = 0; i < genres.size(); ++i) {
                Boolean isChecked = false;
                for(int j = 0; j < movie.GetGenres().size(); ++j) {
                    if(genres.get(i).GetId() == movie.GetGenres().get(j).GetId()) {
                        isChecked = true;
                        break;
                    }
                }
                genreMap.put(genres.get(i), isChecked);
            }


            mav.setViewName("admin/cinema");
            MovieForm movieForm = new MovieForm();
            movieForm.setMinutes(movie.GetTime().getMinutes());
            movieForm.setDescription(movie.GetDescription());
            movieForm.setDirector(movie.GetDirector());
            movieForm.setGenres(genreList);
            movieForm.setHours(movie.GetTime().getHours());
            movieForm.setId(movie.GetId());
            movieForm.setImdb(movie.GetImdbUrl());
            movieForm.setMovieName(movie.GetName());
            movieForm.setYear(movie.GetYear());
            movieForm.setYoutube(movie.GetTrailerUrl());


            mav.setViewName("admin/movie");
            mav.getModelMap().addAttribute("method", "/admin/movie/update");
            mav.getModelMap().addAttribute("movieForm", movieForm);
            mav.getModelMap().addAttribute("imgRequired", false);
            mav.getModelMap().addAttribute("movieGenres", genreMap);

            String pageTitle = movie.GetName() + " - edit";
            mav.getModelMap().addAttribute("pageTitle", pageTitle);
        }
        return mav;
    }



    @PostMapping("/admin/movie/update")
    public String UpdateMovie(@Valid @ModelAttribute("movieForm") MovieForm movieForm, BindingResult bindingResult,
                              RedirectAttributes attributes)  {

        Optional<Movie> movieRepo = movieRepository.findById(movieForm.getId());
        Validation validation = new Validation();
        if(movieRepo.isPresent()){
            Movie movie = movieRepo.get();

            if(movieForm.getGenres().isEmpty()) {
                attributes.addFlashAttribute("failed", "Plese choose genres.");
            } else if(!validation.isValidURI(movieForm.getImdb()) && !validation.isValidURI(movieForm.getImdb(), "imdb")) {
                attributes.addFlashAttribute("failed", "Imdb url is not valid.");
            } else if(!validation.isValidURI(movieForm.getYoutube()) && !validation.isValidURI(movieForm.getYoutube(), "youtube")) {
                attributes.addFlashAttribute("failed", "Youtube url is not valid.");
            } else if(!validation.HaveOnlyLetters(movieForm.getDirector())) {
                attributes.addFlashAttribute("failed", "Director name is not valid.");
            } else if(movieForm.getYear() < 1990 || movieForm.getYear() > 2020) {
                attributes.addFlashAttribute("failed", "Year is not valid.");
            } else if(movieForm.getHours() < 0 || movieForm.getHours() > 12) {
                attributes.addFlashAttribute("failed", "Hours is not valid.");
            } else if(movieForm.getMinutes() < 0 || movieForm.getMinutes() > 59) {
                attributes.addFlashAttribute("failed", "Minutes is not valid.");
            } else {

                // name check
                Boolean isValid = false;
                if(movieForm.getMovieName().equals(movie.GetName())) {
                    System.out.println("1111111");
                    if(!movieForm.getImg().isEmpty()) {
                        fileStorage.store(movieForm.getImg(), "movies", movie.GetName());
                    }
                    isValid = true;
                } else if(cinemaRepository.findByName(movieForm.getMovieName()) == null) {
                    System.out.println("22222");
                    // adds new file and deletes old one
                    System.out.println("FORM : " + movieForm.getImg());
                    if(!movieForm.getImg().isEmpty()) {
                        fileStorage.store(movieForm.getImg(), "movies", movieForm.getMovieName());
                        fileStorage.delete("movies", movie.GetName());

                        // renames existing
                    } else {
                        System.out.println("333333");
                        fileStorage.update("cinema", movie.GetName(), movieForm.getMovieName());
                    }
                    isValid = true;
                } else {
                    attributes.addFlashAttribute("failed", "Cinema name already exist.");
                }

                // assigns new values
                if(isValid == true) {
                    attributes.addFlashAttribute("success", "Movie added.");
                    List<Genres> genres = new ArrayList<>();

                    for(int i = 0; i < movieForm.getGenres().size(); ++i) {
                        Optional<Genres> temp = genresRepository.findById(movieForm.getGenres().get(i));
                        genres.add(temp.get());
                    }
                    Date time = new Date();
                    time.setHours(movieForm.getHours());
                    time.setMinutes(movieForm.getMinutes());
                    movie = new Movie(movieForm.getMovieName(), movieForm.getDescription(), movieForm.getYear(), time,
                            movieForm.getDirector(), movieForm.getYoutube(), movieForm.getImdb(), genres);
                    movie.SetId(movieForm.getId());
                    movieRepository.saveAndFlush(movie);
                }
            }
        } else {
            attributes.addFlashAttribute("failed", "Something went wrong.");
        }
        return "redirect:/admin/movie/" + movieForm.getId();
    }


    @PostMapping("/admin/movie/add")
    public String AddCinema(@Valid @ModelAttribute("movieForm") MovieForm movieForm, BindingResult bindingResult,
                            RedirectAttributes attributes)  {
        Validation validation = new Validation();
        Movie tempMovie = movieRepository.findByName(movieForm.getMovieName());
        if(movieForm.getGenres().isEmpty()) {
            attributes.addFlashAttribute("failed", "Plese choose genres.");
        } else if(tempMovie != null) {
            attributes.addFlashAttribute("failed", "Movie name is not valid.");
        } else if(!validation.isValidURI(movieForm.getImdb()) && !validation.isValidURI(movieForm.getImdb(), "imdb")) {
            attributes.addFlashAttribute("failed", "Imdb url is not valid.");
        } else if(!validation.isValidURI(movieForm.getYoutube()) && !validation.isValidURI(movieForm.getYoutube(), "youtube")) {
            attributes.addFlashAttribute("failed", "Youtube url is not valid.");
        } else if(!validation.HaveOnlyLetters(movieForm.getDirector())) {
            attributes.addFlashAttribute("failed", "Director name is not valid.");
        } else if(movieForm.getYear() < 1990 || movieForm.getYear() > 2020) {
            attributes.addFlashAttribute("failed", "Year is not valid.");
        } else if(movieForm.getHours() < 0 || movieForm.getHours() > 12) {
            attributes.addFlashAttribute("failed", "Hours is not valid.");
        } else if(movieForm.getMinutes() < 0 || movieForm.getMinutes() > 59) {
            attributes.addFlashAttribute("failed", "Minutes is not valid.");
        } else {
            attributes.addFlashAttribute("success", "Movie added.");
            List<Genres> genres = new ArrayList<>();

            for(int i = 0; i < movieForm.getGenres().size(); ++i) {
                Optional<Genres> temp = genresRepository.findById(movieForm.getGenres().get(i));
                genres.add(temp.get());
            }
            Date time = new Date();
            time.setHours(movieForm.getHours());
            time.setMinutes(movieForm.getMinutes());
            Movie movie = new Movie(movieForm.getMovieName(), movieForm.getDescription(), movieForm.getYear(), time,
                    movieForm.getDirector(), movieForm.getYoutube(), movieForm.getImdb(), genres);
            fileStorage.store(movieForm.getImg(), "movies", movieForm.getMovieName());

            movieRepository.saveAndFlush(movie);
        }
        return "redirect:/admin/movie/";
    }


    @RequestMapping(value = "/admin/session/{id}", method = RequestMethod.GET)
    public String GetAdminSession() {
        return "admin/session";
    }
    // ===============================
}
