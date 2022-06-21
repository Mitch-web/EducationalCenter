package com.diplom.alex.dao;

import com.diplom.alex.model.UserMarkingModel;
import com.diplom.alex.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    Optional<UserModel> getUserById(int id);
    Optional<UserModel> getUserByLogin(String login);
    List<UserModel> getUsers();
    List<UserMarkingModel> getByPostId(int postId);
    List<UserModel> getStudents();
    void createUser(UserModel user, int[] coursesIds);
}
