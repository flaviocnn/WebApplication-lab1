package it.polito.ai.formapp.controllers;

import it.polito.ai.formapp.Users;
import it.polito.ai.formapp.ViewModels.LoginVM;
import it.polito.ai.formapp.ViewModels.RegistrationVM;
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

@Controller
public class formController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Users users;

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
        if (this.users.getUsers().containsKey(email) && this.users.getUsers().get(email).equals(p)){
            logger.info("Successful login");
            return "private/privatepage";
        }
        logger.info("Wrong credentials");
        return "login";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("command") RegistrationVM vm){
        return "register";
    }

    @PostMapping("/register")
    public String processForm(
            @Valid @ModelAttribute("command") RegistrationVM vm,
            BindingResult res,
            Model m){

        logger.info(vm.toString());

        if(res.hasErrors()){
            logger.info("Error 1");
            m.addAttribute("message","Try again");
        }
        else{
            String p1 = vm.getPassword1();
            if (p1.equals(vm.getPassword2())){
                logger.info("Passwords ok");
                boolean duplicates = false;
                for (String password:this.users.getUsers().values()) {
                    if (password.equals(p1)) duplicates = true;
                }
                if (!duplicates){
                    logger.info("No duplicates");
                    this.users.newUser(vm.getEmail(),vm.getPassword1());
                    logger.info("User inserted");
                    m.addAttribute("success",vm.getName() + " successfully registered!");
                }
                else vm.setPassword1(" ");
            }else
                vm.setPassword2(" ");
        }
        return "register";
    }

}
