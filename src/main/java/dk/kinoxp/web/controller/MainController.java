package dk.kinoxp.web.controller;

import dk.kinoxp.web.model.entities.Cinema;
import dk.kinoxp.web.model.entities.Showing;
import dk.kinoxp.web.model.repositories.CinemaRepository;
import dk.kinoxp.web.model.repositories.SeatRepository;
import dk.kinoxp.web.model.services.CinemaCreator;
import dk.kinoxp.web.model.services.dto.ShowingDto;
import org.springframework.beans.factory.annotation.Autowired;
import dk.kinoxp.web.model.entities.Movie;
import dk.kinoxp.web.model.repositories.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    @Autowired
    SeatRepository seatRepository;

    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    MovieRepository movieRepository;

    public MainController() {
    }




    @RequestMapping(value = {"", "/", "index"}, method = RequestMethod.GET)
    public String index(HttpServletRequest request){
        return "index";
    }
    @RequestMapping(value = {"create-cinema"}, method = RequestMethod.GET, params = {"cinemaId", "cinemaHeight", "cinemaWidth", "rowCount", "columnCount"})
    public String createCinema(Model model, @RequestParam int cinemaId, @RequestParam double cinemaHeight, @RequestParam double cinemaWidth, @RequestParam int rowCount, @RequestParam int columnCount) {
        /* Example usage :
           http://127.0.0.1:8080/create-cinema?cinemaId=3&cinemaWidth=0&cinemaHeight=0&rowCount=20&columnCount=10
         */
        Cinema cinema = cinemaRepository.findById(cinemaId);
        if (cinema == null) {
            cinema = cinemaRepository.save(new Cinema(cinemaId, cinemaHeight, cinemaWidth));
        }
        CinemaCreator cinemaCreator = new CinemaCreator();
        seatRepository.saveAll(cinemaCreator.createSeats(cinema, rowCount, columnCount));
        return "index";
    }


    @RequestMapping(value = {"create-showing-info"}, method = RequestMethod.GET)
        public String createShowingInfo(Model model) {

        model.addAttribute("movieList", movieRepository.findAll());
        model.addAttribute("showing", new ShowingDto());
        model.addAttribute("cinemaList", cinemaRepository.findAll());


     //   System.out.println(movieRepository.findAll().toString());
        return "create-showing-info";
    }

    @RequestMapping(value = {"create-showing-info"}, method = RequestMethod.POST)
    public String getShowingInfo(@ModelAttribute ShowingDto showingDto){
        Showing showing = new Showing();
        showing.setCinema(cinemaRepository.findById(showingDto.getCinemaId()));
        showing.setMovie(movieRepository.findById(showingDto.getMovieId()));
        System.out.println(showing.getCinema().getSeats().size() + " " + showing.getMovie().getTitle());
        return "/index";
    }


}
