package dk.kinoxp.web.controller;

import dk.kinoxp.web.model.entities.Cinema;
import dk.kinoxp.web.model.entities.Showing;
import dk.kinoxp.web.model.entities.User;
import dk.kinoxp.web.model.repositories.CinemaRepository;
import dk.kinoxp.web.model.repositories.SeatRepository;
import dk.kinoxp.web.model.repositories.UserRepository;
import dk.kinoxp.web.model.repositories.ShowingRepository;
import dk.kinoxp.web.model.services.CinemaCreator;
import dk.kinoxp.web.model.services.PasswordService;
import dk.kinoxp.web.model.services.UserCreator;
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
import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    @Autowired
    SeatRepository seatRepository;

    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    ShowingRepository showingRepository;

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
        showing.setCinema(cinemaRepository.findById(showingDto.getCinemaId()));
        showing.setMovie(movieRepository.findById(showingDto.getMovieId()));
       // System.out.println(showing.getCinema().getSeats().size() + " " + showing.getMovie().getTitle());
       // System.out.println(showingDto.getTime());
       // showingRepository.saveAll(showingDto.createShowing(showing, bla bla, bla bla, bla bla));

        return "/index";
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
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, HttpSession session){
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model, HttpSession session){
        model.addAttribute("user", new User());
        if (sessionController(session)){
            return "create";
        } else {
            return "login";
        }

    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Model model, User user) {
        PasswordService passwordService = new PasswordService();

        UserCreator userCreator = new UserCreator();

        userRepository.save(userCreator.createuser(user.getUsername(), passwordService.encodePassword(user.getPassword())));
        return "redirect:/";
    }

    private boolean sessionController(HttpSession session){
        if(session.getAttribute("status") != null && session.getAttribute("status").equals("1")){
            return true;
        } else {
            return false;
        }
    }

}
