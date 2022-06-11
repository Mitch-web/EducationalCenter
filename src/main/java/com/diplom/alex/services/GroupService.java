package com.diplom.alex.services;

import com.diplom.alex.model.GroupModel;
import com.diplom.alex.model.UserModel;

import java.util.List;

public interface GroupService {

    GroupModel getUserGroup(UserModel user);
    List<GroupModel> getAllGroups();
    int getIdByName(String name);
    int[] getCoursesIdByGroupName(String groupName);

}
