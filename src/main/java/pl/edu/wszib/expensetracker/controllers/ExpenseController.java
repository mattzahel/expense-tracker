package pl.edu.wszib.expensetracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.wszib.expensetracker.service.impl.ExpenseService;
import pl.edu.wszib.expensetracker.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class ExpenseController {
    @Resource
    SessionObject sessionObject;

    @Autowired
    ExpenseService expenseService;

    @RequestMapping(value = "/expenses/new", method = RequestMethod.GET)
    public String newExpensePage(Model model) {
        if (!sessionObject.isLogged()) {
            return "redirect:/login";
        }

        model.addAttribute("isLogged", sessionObject.isLogged());

        return "expense-new";
    }

    @RequestMapping(value = "/expenses/new", method = RequestMethod.POST)
    public String newExpense(@RequestParam double value,
                            @RequestParam String title,
                           @RequestParam String category,
                           @RequestParam String date
    ) {
        if (!sessionObject.isLogged()) {
            return "redirect:/login";
        }

        expenseService.create(title, value, category, date);

        return "expense-new";
    }
}
