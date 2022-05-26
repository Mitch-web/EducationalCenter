package com.diplom.alex.dao.impl;


import com.diplom.alex.dao.UserDAO;
import com.diplom.alex.model.UserModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static com.diplom.alex.constants.ApplicationConstants.USER_TABLE;

public class UserDAOImpl implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<UserModel> getUserById(int id) {
        return Optional.ofNullable(extractUser(String.format("SELECT * FROM %s WHERE id=?", USER_TABLE),
                new Object[]{id}));
    }

    @Override
    public Optional<UserModel> getUserByLogin(String login) {
        return Optional.ofNullable(extractUser(String.format("SELECT * FROM %s WHERE login=?", USER_TABLE),
                new Object[]{login}));
    }

    @Override
    public List<UserModel> getUsers() {
        return jdbcTemplate.query(String.format("SELECT * FROM %s", USER_TABLE),
                new BeanPropertyRowMapper<>(UserModel.class));
    }

    @Override
    public void createUser(UserModel user) {
        String sqlToInsert = "INSERT INTO " + USER_TABLE +
                "(login, password, role_id, first_name, last_name) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sqlToInsert, user.getLogin(), user.getPassword(), user.getRoleId(),
                user.getFirstName(), user.getLastName());
    }

    private UserModel extractUser(String query, Object[] params) {
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(UserModel.class), params)
                .stream()
                .findAny()
                .orElse(null);
    }
}
