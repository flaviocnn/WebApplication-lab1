package it.polito.ai.formapp;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Data
public class Users {
    private String name;
    private String surname;
    private String email;
    private String password;


    public Users(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
}
