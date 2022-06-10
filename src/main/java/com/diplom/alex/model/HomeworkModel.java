package com.diplom.alex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkModel {

    private int id;
    private String contentType;
    private String fileName;
    private byte[] content;

}
