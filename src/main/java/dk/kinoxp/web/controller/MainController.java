package dk.kinoxp.web.controller;

import dk.kinoxp.web.model.services.dto.DateTimeDto;
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
import java.util.Date;
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
    public String index(HttpServletRequest request, HttpSession session, Model model){

        List<Movie> movieList;
        PosterService posterService = new PosterService();

        movieList = movieRepository.findAll();
        for (Movie movie : movieList) {
            posterService.initializePoster(movie);
        }

        model.addAttribute("movieList",movieList);

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

    @RequestMapping (value = {"view-showings"}, method = RequestMethod.GET)
    public String viewShowing(Model model, HttpSession session)
    {
    model.addAttribute("showingList", showingRepository.findAll());
        if (sessionController(session)){
            return "view-showings";
        } else {
            return "login";
        }
    }

    @RequestMapping (value = {"update-showing"}, method = RequestMethod.GET)
    public String getUpdateShowing(Model model, @RequestParam int showingId)
    {
        Showing showing = showingRepository.findById(showingId);
        ShowingDto showingDto = new ShowingDto(showing.getId(), showing.getTime(), showing.getMovie().getId(), showing.getMovie().getTitle(), showing.getCinema().getId());
        model.addAttribute("showing", showingDto);
        return "/update-showing";
    }

    @RequestMapping (value = {"update-showing"}, method = RequestMethod.POST)
    public String setUpdateShowing(@ModelAttribute("showing") ShowingDto showing, @RequestParam int showingId)
    {
        Showing showingObj = showingRepository.findById(showingId);
        showingObj.setTime(showing.getTime());
        showingObj.setCinema(cinemaRepository.findById(showing.getCinemaId()));

        showingRepository.save(showingObj);
        return "redirect:/view-showings";
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
        return "redirect:/index";
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

    @RequestMapping (value = {"create-booking-select-showing"}, method = RequestMethod.GET)
    public String selectShowingForBooking(Model model)
    {
        List<Showing> showings = showingRepository.findAll();
        model.addAttribute(showings);
        return "/create-booking-select-showing";
    }

    @RequestMapping(value = "/create-booking", method = RequestMethod.GET)
    public String createBooking(){

        // Might need an empty Booking object...

        return "create-booking";
    }

    @RequestMapping(value = "/create-booking", method = RequestMethod.POST)
    public String createBooking(Booking booking){
        BookingCreator bookingCreator = new BookingCreator();
        bookingRepository.save(bookingCreator.createBooking(booking.getSeats(), booking.getCinema(), booking.getShowing(), booking.getTelephone(), booking.isPaid()));

        // Form missing in HTML

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

    @RequestMapping(value = "/view-movies", method = RequestMethod.GET)
    public String viewMovies(Model model) {
        model.addAttribute("movieList", movieRepository.findAll());
        return "view-movies";
    }

    @RequestMapping (value = {"update-movie"}, method = RequestMethod.GET)
    public String getUpdateMovie(Model model, @RequestParam int movieId)
    {
        Movie movie = movieRepository.findById(movieId);
        model.addAttribute("movie", movie);
        return "/update-movie";
    }

    @RequestMapping (value = {"update-movie"}, method = RequestMethod.POST)
    public String setUpdateShowing(@ModelAttribute("movie") Movie newMovie, @RequestParam int movieId)
    {
        Movie movie = movieRepository.findById(movieId);
        movie.setAge(newMovie.getAge());
        movie.setTitle(newMovie.getTitle());
        movie.setDescription(newMovie.getDescription());
        movie.setPosterPath(newMovie.getPosterPath());
        movie.setRuntime(newMovie.getRuntime());

        movieRepository.save(movie);
        return "redirect:/view-movies";
    }

    @RequestMapping (value ={"search-showing"}, method = RequestMethod.GET)
    public String searchShowing(Model model, HttpSession session){
        model.addAttribute("dateFormat", new DateTimeDto());

        if (sessionController(session)){
            return "search-showing";
        } else {
            return "login";
        }
    }

    @RequestMapping (value ={"search-showing"}, method = RequestMethod.POST)
    public String getSearchShowing(@ModelAttribute("dateFormat") DateTimeDto dateFormat, Model model){


        Date searchedDate = dateFormat.getDate();
        System.out.println(searchedDate);
        List<Showing> allshowings = showingRepository.findAll();
        ArrayList<Showing> searchResults = new ArrayList<>();
        for(Showing showing: allshowings){
            if(searchedDate.getDate() == showing.getTime().getDate() && searchedDate.getDay() == showing.getTime().getDay()) {
                searchResults.add(showing);
            }
        }
        model.addAttribute("searchedResults", searchResults);
        return "search-showing";
    }

    @RequestMapping (value = {"delete-movie"}, method = RequestMethod.GET)
    public String deleteMovie(@RequestParam int movieId) {
        movieRepository.deleteById(movieId);
        return "redirect:/view-movies";
    }




    private boolean sessionController(HttpSession session){
        if(session.getAttribute("status") != null && session.getAttribute("status").equals("1")){
            return true;
        } else {
            return false;
        }
    }

}