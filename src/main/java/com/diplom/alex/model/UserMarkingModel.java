package com.diplom.alex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMarkingModel {

    private int userId;
    private String name;
    private String firstName;
    private String lastName;
    private String contentType;
    private int mark;

}
