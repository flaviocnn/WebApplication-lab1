package it.polito.ai.formapp.controllers;

import it.polito.ai.formapp.Users;
import it.polito.ai.formapp.ViewModels.LoginVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ConcurrentHashMap<String, Users> users;

    @GetMapping("/login")
    public String login(@ModelAttribute("access") LoginVM vm){
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@Valid @ModelAttribute("access") LoginVM vm,
                               BindingResult res,
                               Model m){
        if(res.hasErrors()){
            logger.info("Error login");
            m.addAttribute("message","Try again");
        }

        String email = vm.getEmail();
        String p = vm.getPassword();
        logger.info("Inserted User:" + email + " Password: " + p);

        if (this.users.containsKey(email)){
            logger.info(this.users.get(email.toString()) + "");
            if (this.users.get(email).getPassword().equals(p)){
                logger.info("Successful login");
                return "private/privatepage";
            }
        }

        logger.info("Wrong credentials");
        return "login";
    }
}
