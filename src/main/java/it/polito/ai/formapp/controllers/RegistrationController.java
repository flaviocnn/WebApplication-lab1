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
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class RegistrationController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ConcurrentHashMap<String,Users> users;

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
                for (Users u:this.users.values()) {
                    if (u.getPassword().equals(p1)) duplicates = true;
                }

                if (!duplicates){
                    logger.info("No duplicates");
                    Users myu = new Users(vm.getName(),vm.getSurname(),vm.getEmail(),vm.getPassword1());
                    this.users.put(vm.getEmail(),myu);
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
