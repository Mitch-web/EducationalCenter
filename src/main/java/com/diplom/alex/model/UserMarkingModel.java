package com.diplom.alex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMarkingModel {

    private int id;
    private String name;
    private String firstName;
    private String lastName;
    private String fileName;
    private String contentType;
    private byte[] content;
    private String stringContent = "";
    private int mark;
    private String comment;

}
