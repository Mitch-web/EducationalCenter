package com.diplom.alex.dao.impl;

import com.diplom.alex.dao.RoleDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import static com.diplom.alex.constants.ApplicationConstants.ROLE_TABLE;

public class RoleDAOImpl implements RoleDAO {

    private final JdbcTemplate jdbcTemplate;

    public RoleDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int getIdByName(String roleName) {
        String query = "SELECT id FROM " + ROLE_TABLE + " WHERE name=?";
        return jdbcTemplate.queryForObject(query, new Object[]{roleName}, Integer.class);
    }
}
