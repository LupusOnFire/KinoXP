package dk.kinoxp.web.controller;

import dk.kinoxp.web.model.entities.Booking;
import dk.kinoxp.web.model.entities.Cinema;
import dk.kinoxp.web.model.entities.Showing;
import dk.kinoxp.web.model.entities.User;
import dk.kinoxp.web.model.repositories.*;
import dk.kinoxp.web.model.entities.*;
import dk.kinoxp.web.model.repositories.ShowingRepository;
import dk.kinoxp.web.model.services.*;
import dk.kinoxp.web.model.services.dto.ShowingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController {
    @Autowired
    SeatRepository seatRepository;

    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ShowingRepository showingRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ActorRepository actorRepository;


    public MainController() {
    }


    @RequestMapping(value = {"", "/", "index"}, method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpSession session){
        if (sessionController(session)){
            return "index";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = {"create-cinema"}, method = RequestMethod.GET, params = {"cinemaId", "cinemaHeight", "cinemaWidth", "rowCount", "columnCount"})
    public String createCinema(Model model, HttpSession session, @RequestParam int cinemaId, @RequestParam double cinemaHeight, @RequestParam double cinemaWidth, @RequestParam int rowCount, @RequestParam int columnCount) {
        /* Example usage :
           http://127.0.0.1:8080/create-cinema?cinemaId=3&cinemaWidth=0&cinemaHeight=0&rowCount=20&columnCount=10
         */
        Cinema cinema = cinemaRepository.findById(cinemaId);
        if (cinema == null) {
            cinema = cinemaRepository.save(new Cinema(cinemaId, cinemaHeight, cinemaWidth));
        }
        CinemaCreator cinemaCreator = new CinemaCreator();
        seatRepository.saveAll(cinemaCreator.createSeats(cinema, rowCount, columnCount));

        if (sessionController(session)){
            return "index";
        } else {
            return "login";
        }
    }


    @RequestMapping(value = {"create-showing-info"}, method = RequestMethod.GET)
    public String createShowingInfo(Model model, HttpSession session) {

        model.addAttribute("movieList", movieRepository.findAll());
        model.addAttribute("showing", new ShowingDto());
        model.addAttribute("cinemaList", cinemaRepository.findAll());


        //   System.out.println(movieRepository.findAll().toString());

        if (sessionController(session)){
            return "create-showing-info";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = {"create-showing-info"}, method = RequestMethod.POST)
    public String getShowingInfo(@ModelAttribute ShowingDto showingDto){
        Showing showing = new Showing();
        showing.setMovie(movieRepository.findById(showingDto.getMovieId()));
        showing.setCinema(cinemaRepository.findById(showingDto.getCinemaId()));
        showing.setTime((showingDto.getTime()));
        showingRepository.save(showing);
        return "/index";
    }


    @RequestMapping(value = {"show-available-seats"}, method = RequestMethod.GET, params = {"showingId"})
    public String getAvailableSeatsForShowing(Model model, @RequestParam int showingId) {
        ShowingService showingService = new ShowingService();

        Showing showing = showingRepository.findById(showingId);
        List<Booking> bookings = bookingRepository.findAllByShowing(showing);

        List<Seat> seats = showingService.setSeatState(showing.getCinema().getSeats(), bookings);

        model.addAttribute("seats", seats);
        model.addAttribute("showing", showing);
        model.addAttribute("cinema", showing.getCinema());
        return "/show-available-seats";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, Model model, HttpSession session){
        User dbUser = userRepository.findByUsername(user.getUsername());
        if (dbUser != null){

            PasswordService pwdService = new PasswordService();

            if (pwdService.checkMatch(user.getPassword(), dbUser.getPassword())){
                session.setAttribute("status", "1");
                System.out.println("Logget på");
            } else {
                session.setAttribute("status", "0");
                System.out.println("Ikke logget på");
                return "login";
            }
        }
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, HttpSession session){
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpSession session){
        session.invalidate();
        System.out.println("Logget af");
        return "login";
    }


    @RequestMapping(value = "/create-user", method = RequestMethod.GET)
    public String createUser(Model model, HttpSession session){
        model.addAttribute("user", new User());

        if (sessionController(session)){
            return "create";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/create-movie", method = RequestMethod.GET)
    public String createMovie(Model model, HttpSession session){
        model.addAttribute("movie", new Movie());

        if (sessionController(session)){
            return "create-movie";
        } else {
            return "login";
        }
    }
    @RequestMapping(value = "/create-movie", method = RequestMethod.POST)
    public String createMovie(Model model, Movie movie) {

        MovieFetchService movieFetchService = new MovieFetchService(actorRepository);

        Movie omdbMovie = movieFetchService.getMovieFromTitle(movie.getTitle());
        movieRepository.save(omdbMovie);

        return "redirect:/index";
    }

    @RequestMapping(value = "/create-user", method = RequestMethod.POST)
    public String createUser(Model model, User user) {
        PasswordService passwordService = new PasswordService();
        UserCreator userCreator = new UserCreator();
        userRepository.save(userCreator.createUser(user.getUsername(), passwordService.encodePassword(user.getPassword())));
        return "create";
    }

    @RequestMapping(value = "/create-booking", method = RequestMethod.POST)
    public String createBooking(Booking booking){
        BookingCreator bookingCreator = new BookingCreator();
        bookingRepository.save(bookingCreator.createBooking(booking.getSeats(), booking.getCinema(), booking.getShowing(), booking.getTelephone(), booking.isPaid()));

        // Form missing in HTML

        return "create-booking";
    }

    @RequestMapping(value = "/create-booking", method = RequestMethod.GET)
    public String createBooking(){

        // Might need an empty Booking object...

        return "create-booking";
    }

    @RequestMapping(value = "/create-actor", method = RequestMethod.GET)
    public String createActor(Model model, HttpSession session){
        model.addAttribute("actor", new Actor());

        if (sessionController(session)){
            return "create-actor";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/create-actor", method = RequestMethod.POST)
    public String createActor(Model model, Actor actor) {
        ActorCreator actorCreator = new ActorCreator();
        actorRepository.save(actorCreator.createActor(actor.getName()));
        return "redirect:/index";
    }

    @RequestMapping(value = {"view-showings"}, method = RequestMethod.GET)
    public String viewShowings(Model model, HttpServletRequest request, HttpSession session){
        model.addAttribute("showings",showingRepository.findAll());

        if (sessionController(session)){
            return "view-showings";
        } else {
            return "login";
        }
    }

    private boolean sessionController(HttpSession session){
        if(session.getAttribute("status") != null && session.getAttribute("status").equals("1")){
            return true;
        } else {
            return false;
        }
    }


}