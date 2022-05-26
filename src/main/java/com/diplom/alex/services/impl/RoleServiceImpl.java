package com.diplom.alex.services.impl;

import com.diplom.alex.dao.RoleDAO;
import com.diplom.alex.services.RoleService;

public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public int getIdByName(String roleName) {
        return roleDAO.getIdByName(roleName.toLowerCase());
    }
}
