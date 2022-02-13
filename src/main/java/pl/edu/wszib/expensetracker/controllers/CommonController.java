package pl.edu.wszib.expensetracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model) {
//        model.addAttribute("isLogged", sessionObject.isLogged());

        return "index";
    }
}
