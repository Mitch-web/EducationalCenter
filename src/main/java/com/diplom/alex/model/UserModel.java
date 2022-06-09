package com.diplom.alex.model;

import lombok.Data;

@Data
public class UserModel {

    private int id;
    private String login;
    private String password;
    private int roleId;
    private String firstName;
    private String lastName;
    private int groupId;

}
