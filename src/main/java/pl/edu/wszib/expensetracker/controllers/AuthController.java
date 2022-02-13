package pl.edu.wszib.expensetracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.expensetracker.service.impl.AuthService;
import pl.edu.wszib.expensetracker.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class AuthController {
    @Resource
    SessionObject sessionObject;

    @Autowired
    AuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        model.addAttribute("isLogged", sessionObject.isLogged());

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String login, @RequestParam String password) {
        if (authService.login(login, password)) {
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        model.addAttribute("isLogged", sessionObject.isLogged());

        authService.logout();

        return "redirect:/login";
    }
}
