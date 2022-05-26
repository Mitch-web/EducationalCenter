package com.diplom.alex.services.impl;

import com.diplom.alex.dao.CourseDAO;
import com.diplom.alex.model.CourseModel;
import com.diplom.alex.services.CourseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseServiceImpl implements CourseService {

    private final CourseDAO courseDAO;
    private final Map<String, String> coursesNames;

    public CourseServiceImpl(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
        coursesNames = new HashMap<>();
        initializeMap();
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
    public String getCourseNameByParam(String param) {
        return coursesNames.get(param);
    }

    private void initializeMap() {
        coursesNames.put("math", "Математика");
        coursesNames.put("physic", "Фізика");
        coursesNames.put("chemistry", "Хімія");
    }
}
