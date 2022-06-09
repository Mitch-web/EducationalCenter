package com.diplom.alex.dao.impl;

import com.diplom.alex.dao.CourseDAO;
import com.diplom.alex.model.CourseModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static com.diplom.alex.constants.ApplicationConstants.COURSES_HAVE_USERS_TABLE;
import static com.diplom.alex.constants.ApplicationConstants.COURSE_TABLE;

public class CourseDAOImpl implements CourseDAO {

    private final JdbcTemplate jdbcTemplate;

    public CourseDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CourseModel> getAllCourses() {
        return jdbcTemplate.query(String.format("SELECT * FROM %s", COURSE_TABLE),
                new BeanPropertyRowMapper<>(CourseModel.class));
    }

    @Override
    public Optional<CourseModel> getByName(String name) {
        return Optional.ofNullable(extractCourse(String.format("SELECT * FROM %s WHERE name=?", COURSE_TABLE),
                new Object[]{name}));
    }

    @Override
    public Optional<CourseModel> getById(int id) {
        return Optional.ofNullable(extractCourse(String.format("SELECT * FROM %s WHERE id=?", COURSE_TABLE),
                new Object[]{id}));
    }

    @Override
    public List<CourseModel> getByUserId(int userId) {
        return jdbcTemplate.query(String.format("SELECT c.id, c.name FROM %s AS c JOIN %s AS chu" +
                        " WHERE c.id=chu.course_id AND chu.user_id=?", COURSE_TABLE, COURSES_HAVE_USERS_TABLE),
                new BeanPropertyRowMapper<>(CourseModel.class), userId);
    }

    private CourseModel extractCourse(String query, Object[] params) {
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(CourseModel.class), params)
                .stream()
                .findAny()
                .orElse(null);
    }
}
