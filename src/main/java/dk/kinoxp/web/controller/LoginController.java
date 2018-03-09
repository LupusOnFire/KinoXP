package dk.kinoxp.web.controller;

import dk.kinoxp.web.model.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, Model model, HttpSession session){
        if(user.getUsername().equals("anna") && user.getPassword().equals("1234")){
            session.setAttribute("session", "1");
            System.out.println("Logget på");
        } else {
            session.setAttribute("session", "0");
            System.out.println("Ikke logget på");
        }
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, HttpSession session){
        model.addAttribute("user", new User());
        return "login";
    }

}
