package com.diplom.alex.services.impl;

import com.diplom.alex.dao.GroupDAO;
import com.diplom.alex.model.GroupModel;
import com.diplom.alex.model.UserModel;
import com.diplom.alex.services.GroupService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupServiceImpl implements GroupService {

    private final GroupDAO groupDAO;
    private final Map<String, int[]> groupCourses;

    public GroupServiceImpl(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
        this.groupCourses = new HashMap<>();
        initializeGroupCoursesMap();
    }

    @Override
    public GroupModel getUserGroup(UserModel user) {
        return groupDAO.getUserGroup(user);
    }

    @Override
    public List<GroupModel> getAllGroups() {
        return groupDAO.getAllGroups();
    }

    @Override
    public int getIdByName(String name) {
        return groupDAO.getIdByName(name);
    }

    @Override
    public int[] getCoursesIdByGroupName(String groupName) {
        return groupCourses.get(groupName);
    }

    private void initializeGroupCoursesMap() {
        groupCourses.put("КР-19", new int[]{4,9,10});
        groupCourses.put("КН-18", new int[]{4,9,10});
        groupCourses.put("КІБ-18", new int[]{11,12,13});
        groupCourses.put("ОІП-18", new int[]{14,15,16});
        groupCourses.put("КІ-18", new int[]{17,19,20});
    }
}
