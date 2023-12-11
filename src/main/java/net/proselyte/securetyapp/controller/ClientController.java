package net.proselyte.securetyapp.controller;

import net.proselyte.securetyapp.model.Client;
import net.proselyte.securetyapp.model.Note;
import net.proselyte.securetyapp.service.ClientService;
import net.proselyte.securetyapp.service.NoteService;
import net.proselyte.securetyapp.service.SecurityService;
import net.proselyte.securetyapp.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ClientValidator clientValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("clientForm", new Client());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("clientForm") Client clientForm,
                               BindingResult bindingResult, Model model) {
        clientValidator.validate(clientForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        clientService.save(clientForm);
        securityService.autoLogin(clientForm.getName(), clientForm.getConfirmPassword());
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            if (!error.isEmpty()) {
                model.addAttribute("error", "Username or password is incorrect.");
            }
        }
        if (logout != null) {
            if (!logout.isEmpty()) {
                model.addAttribute("message", "Logger out successfully.");
            }
        }
        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.POST)
    public String claculate(@RequestParam("firstNumber") String firstNumber,
                            @RequestParam("secondNumber") String secondNumber, Model model) {
        try {
            double resultValue = Double.parseDouble(firstNumber) + Double.parseDouble(secondNumber);
            model.addAttribute("result", String.valueOf(resultValue));
        } catch (NumberFormatException e) {
            model.addAttribute("result", "Invalid input. Please enter valid numbers.");
        }
        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        List<Client> clientList = clientService.getAllClient();
        model.addAttribute("clientList", clientList);
        return "admin";
    }

    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public String calendar(Model model) {
        return "calendar";
    }
    @RequestMapping(value = "/calendar", method = RequestMethod.POST)
    public String calendar(@RequestParam("context")String context,
                           @RequestParam("day") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
                           Principal principal,
                           Model model) {
        String loggerInUsername = principal.getName();
        Client client = clientService.findByName(loggerInUsername);
        noteService.save(new Note(date, context, client));
        return "calendar";
    }
}
