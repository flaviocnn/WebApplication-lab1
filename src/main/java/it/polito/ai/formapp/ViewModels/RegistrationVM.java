package it.polito.ai.formapp.ViewModels;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class RegistrationVM {

    @Size(min=3,max = 20)
    private String name;

    @Size(min=3,max = 20)
    private String surname;

    @Size(min=3,max = 255)
    @Email
    private String email;

    @Size(min=8,max = 20)
    private String password1;

    @Size(min=8,max = 20)
    private String password2;

    @AssertTrue
    boolean privacy;
}


