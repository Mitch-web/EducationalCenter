package com.diplom.alex.dao;

import com.diplom.alex.model.GroupModel;
import com.diplom.alex.model.UserModel;

import java.util.List;

public interface GroupDAO {

    GroupModel getUserGroup(UserModel user);
    List<GroupModel> getAllGroups();
    int getIdByName(String name);

}
