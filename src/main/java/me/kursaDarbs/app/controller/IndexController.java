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

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RestController
    public static class MovieController {

        // post method to get all cinema data
        @Autowired
        CinemaRepository repository;
        @RequestMapping(value = "/movies", method = RequestMethod.POST)
        public @ResponseBody
        String GetCinemaData() {
            JSONObject obj = new JSONObject();
            int i = 0;
            for (Cinema cinema : repository.findAll()) {
                try {
                    obj.put(Integer.toString(i), cinema.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ++i;
            }
            return obj.toString();
        }





    }
}

