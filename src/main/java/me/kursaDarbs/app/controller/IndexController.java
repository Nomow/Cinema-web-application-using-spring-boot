package me.kursaDarbs.app.controller;

import me.kursaDarbs.app.model.Cinema;
import me.kursaDarbs.app.repository.CinemaRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {
    @Autowired CinemaRepository cinemaRepository;
    @RequestMapping("/")
    public ModelAndView Index() {
        ModelAndView mav = new ModelAndView();
        List<Cinema> cinemas = cinemaRepository.findAll();
        mav.setViewName("home");
        mav.getModelMap().addAttribute("cinemas", cinemas);
        return mav;
    }
}

