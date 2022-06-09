package com.diplom.alex.dao.impl;

import com.diplom.alex.dao.GroupDAO;
import com.diplom.alex.model.GroupModel;
import com.diplom.alex.model.UserModel;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static com.diplom.alex.constants.ApplicationConstants.GROUPS_TABLE;

public class GroupDAOImpl implements GroupDAO {

    private final JdbcTemplate jdbcTemplate;

    private GroupDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public GroupModel getUserGroup(UserModel user) {
        List<GroupModel> groups = jdbcTemplate.query("SELECT * FROM " + GROUPS_TABLE + " WHERE id=?",
                new BeanPropertyRowMapper<>(GroupModel.class), user.getGroupId());
        return groups.isEmpty() ? null : groups.get(0);
    }

    @Override
    public List<GroupModel> getAllGroups() {
        return jdbcTemplate.query("SELECT * FROM " + GROUPS_TABLE, new BeanPropertyRowMapper<>(GroupModel.class));
    }
}
