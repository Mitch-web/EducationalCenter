package com.diplom.alex.dao;

import com.diplom.alex.model.CourseModel;

import java.util.List;
import java.util.Optional;

public interface CourseDAO {

    List<CourseModel> getAllCourses();
    Optional<CourseModel> getByName(String name);
    Optional<CourseModel> getById(int id);
    List<CourseModel> getByUserId(int userId);
    void removeById(int id);
    void updateName(int id, String newName);

}
