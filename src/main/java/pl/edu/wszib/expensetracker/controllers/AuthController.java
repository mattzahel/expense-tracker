package pl.edu.wszib.expensetracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.expensetracker.exceptions.AuthValidationException;
import pl.edu.wszib.expensetracker.exceptions.LoginAlreadyUseException;
import pl.edu.wszib.expensetracker.model.view.RegisterUser;
import pl.edu.wszib.expensetracker.service.impl.AuthService;
import pl.edu.wszib.expensetracker.session.SessionObject;
import pl.edu.wszib.expensetracker.validators.LoginValidator;
import pl.edu.wszib.expensetracker.validators.RegisterValidator;

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
        try {
            LoginValidator.validateLogin(login);
            LoginValidator.validatePass(password);
        } catch (AuthValidationException e) {
            return "redirect:/login";
        }

        this.authService.login(login, password);

        if (sessionObject.isLogged()) {
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

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("isLogged", sessionObject.isLogged());

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute RegisterUser registerUser) {
        try {
            RegisterValidator.validateName(registerUser.getName());
            LoginValidator.validateLogin(registerUser.getLogin());
            LoginValidator.validatePass(registerUser.getPassword());
            checkPasswords(registerUser.getPassword(), registerUser.getPasswordConfirmation());

            this.authService.register(registerUser);
        } catch (AuthValidationException | LoginAlreadyUseException e) {
            return "redirect:/register";
        }

        return "redirect:/";
    }

    private void checkPasswords(String password, String passwordConfirmation) {
        if (password == null || !password.equals(passwordConfirmation)) {
            throw new AuthValidationException("Incorrect password!");
        }
    }

}
