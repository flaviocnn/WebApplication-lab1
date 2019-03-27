package it.polito.ai.formapp;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Scope("singleton")
@Component
@Data
public class Users {

    private HashMap<String,String> users = new HashMap<>();

    public void newUser(String email, String password){
        this.users.put(email,password);
    }
}
