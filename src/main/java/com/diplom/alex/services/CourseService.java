package com.diplom.alex.services;

import com.diplom.alex.model.CourseModel;

import java.util.List;

public interface CourseService {

    List<CourseModel> getAllCourses();
    CourseModel getByName(String name);
    CourseModel getById(int id);
    List<CourseModel> getByUserId(int userId);
    void removeById(int id);

}
