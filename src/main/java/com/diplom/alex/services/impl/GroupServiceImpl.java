package com.diplom.alex.services.impl;

import com.diplom.alex.dao.GroupDAO;
import com.diplom.alex.model.GroupModel;
import com.diplom.alex.model.UserModel;
import com.diplom.alex.services.GroupService;

import java.util.List;

public class GroupServiceImpl implements GroupService {

    private final GroupDAO groupDAO;

    public GroupServiceImpl(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    @Override
    public GroupModel getUserGroup(UserModel user) {
        return groupDAO.getUserGroup(user);
    }

    @Override
    public List<GroupModel> getAllGroups() {
        return groupDAO.getAllGroups();
    }
}
