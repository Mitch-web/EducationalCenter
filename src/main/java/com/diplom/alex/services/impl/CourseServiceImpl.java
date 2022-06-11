package com.diplom.alex.services.impl;

import com.diplom.alex.dao.CourseDAO;
import com.diplom.alex.model.CourseModel;
import com.diplom.alex.services.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private final CourseDAO courseDAO;

    public CourseServiceImpl(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    public List<CourseModel> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    @Override
    public CourseModel getByName(String name) {
        return courseDAO.getByName(name).orElseGet(CourseModel::new);
    }

    @Override
    public CourseModel getById(int id) {
        return courseDAO.getById(id).orElseGet(CourseModel::new);
    }

    @Override
    public List<CourseModel> getByUserId(int userId) {
        return courseDAO.getByUserId(userId);
    }

}
