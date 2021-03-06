package com.diplom.alex.services;

import com.diplom.alex.model.UserMarkingModel;
import com.diplom.alex.model.UserModel;

import java.util.List;

public interface UserService {

    UserModel getUserById(int id);
    UserModel getUserByLogin(String login);
    List<UserModel> getUsers();
    List<UserMarkingModel> getByPostId(int postId);
    List<UserModel> getStudents();
    void createUser(UserModel user, int[] coursesIds);
    void deleteById(int id);

}
