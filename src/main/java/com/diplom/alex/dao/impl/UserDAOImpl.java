package com.diplom.alex.dao.impl;

import com.diplom.alex.dao.UserDAO;
import com.diplom.alex.model.UserMarkingModel;
import com.diplom.alex.model.UserModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
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
        String selectQuery = "SELECT u.id, g.name, u.first_name, u.last_name,  h.file_name, h.content, uhp.mark FROM " + USERS_HAVE_POSTS_TABLE +
                " AS uhp JOIN " + USER_TABLE + " AS u ON uhp.user_id=u.id JOIN " + HOMEWORKS_TABLE +
                " AS h ON uhp.homework_id=h.id JOIN " + GROUPS_TABLE + " AS g ON u.group_id=g.id " +
                " WHERE uhp.post_id=?";
        BeanPropertyRowMapper<UserMarkingModel> rowMapper = new BeanPropertyRowMapper<>(UserMarkingModel.class);
        rowMapper.setPrimitivesDefaultedForNullValue(true);
        return jdbcTemplate.query(selectQuery, rowMapper, postId);
    }

    @Override
    public void createUser(UserModel user, int[] coursesIds) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sqlToInsert = "INSERT INTO " + USER_TABLE +
                "(login, password, role_id, first_name, last_name, group_id) VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlToInsert, new String[]{"id"});
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getRoleId());
            ps.setString(4, user.getFirstName());
            ps.setString(5, user.getLastName());
            ps.setInt(6, user.getGroupId());
            return ps;
        }, keyHolder);

        String updateSql = "INSERT INTO " + COURSES_HAVE_USERS_TABLE + " VALUES(?,?)";
        for (int courseId : coursesIds) {
            jdbcTemplate.update(updateSql, keyHolder.getKey(), courseId);
        }
    }

    private UserModel extractUser(String query, Object[] params) {
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(UserModel.class), params)
                .stream()
                .findAny()
                .orElse(null);
    }
}
