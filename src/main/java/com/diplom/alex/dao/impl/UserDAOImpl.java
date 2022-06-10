package com.diplom.alex.dao.impl;

import com.diplom.alex.dao.UserDAO;
import com.diplom.alex.model.UserMarkingModel;
import com.diplom.alex.model.UserModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static com.diplom.alex.constants.ApplicationConstants.*;

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
    public List<UserMarkingModel> getByPostId(int postId) {
        String selectQuery = "SELECT g.name, u.first_name, u.last_name, h.content_type, uhp.mark FROM " + USERS_HAVE_POSTS_TABLE +
                " AS uhp JOIN " + USER_TABLE + " AS u ON uhp.user_id=u.id JOIN " + HOMEWORKS_TABLE +
                " AS h ON uhp.homework_id=h.id JOIN " + GROUPS_TABLE + " AS g ON u.group_id=g.id " +
                " WHERE uhp.post_id=?";
        BeanPropertyRowMapper<UserMarkingModel> rowMapper = new BeanPropertyRowMapper<>(UserMarkingModel.class);
        rowMapper.setPrimitivesDefaultedForNullValue(true);
        return jdbcTemplate.query(selectQuery, rowMapper, postId);
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
