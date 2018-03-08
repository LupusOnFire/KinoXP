package dk.kinoxp.web.controller;

import dk.kinoxp.web.model.repositories.CinemaRepository;
import dk.kinoxp.web.model.repositories.SeatRepository;
import dk.kinoxp.web.model.services.CinemaCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    @Autowired
    SeatRepository seatRepository;

    @Autowired
    CinemaRepository cinemaRepository;

    public MainController() {
    }

    @RequestMapping(value = {"", "/", "index"}, method = RequestMethod.GET)
    public String index(HttpServletRequest request){
        return "index";
    }
    @RequestMapping(value = {"create-cinema"}, method = RequestMethod.GET)
    public String createCinema(Model model) {
        CinemaCreator cinemaCreator = new CinemaCreator(seatRepository, cinemaRepository);
        cinemaCreator.createCinemaAndSeats(2, 25, 16);
        return "index";
    }
}
