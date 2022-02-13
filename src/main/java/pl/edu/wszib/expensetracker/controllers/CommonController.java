package pl.edu.wszib.expensetracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.expensetracker.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class CommonController {
    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model) {
        if (!sessionObject.isLogged()) {
            return "redirect:/login";
        }

        model.addAttribute("isLogged", sessionObject.isLogged());
        model.addAttribute("user", sessionObject.getUser());

        return "index";
    }
}
