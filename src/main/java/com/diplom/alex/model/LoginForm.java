package com.diplom.alex.model;

import com.diplom.alex.validators.annotations.LoginMatch;
import lombok.Data;

@Data
public class LoginForm {

    @LoginMatch
    private String login;
    private String password;

}
