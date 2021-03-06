package com.diplom.alex.services.impl;

import com.diplom.alex.dao.UserDAO;
import com.diplom.alex.model.UserMarkingModel;
import com.diplom.alex.model.UserModel;
import com.diplom.alex.services.UserService;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserModel getUserById(int id) {
        Optional<UserModel> userById = userDAO.getUserById(id);
        return userById.orElseGet(UserModel::new);
    }

    @Override
    public UserModel getUserByLogin(String login) {
        Optional<UserModel> userByLogin = userDAO.getUserByLogin(login);
        return userByLogin.orElseGet(UserModel::new);
    }

    @Override
    public List<UserModel> getUsers() {
        return userDAO.getUsers();
    }

    @Override
    public List<UserMarkingModel> getByPostId(int postId) {
        List<UserMarkingModel> userMarkings = userDAO.getByPostId(postId);
        userMarkings.forEach(userMarking-> {
            userMarking.setStringContent(new String(userMarking.getContent(), StandardCharsets.UTF_8));
        });
        return userMarkings;
    }

    @Override
    public List<UserModel> getStudents() {
        return userDAO.getStudents();
    }

    @Override
    public void createUser(UserModel user, int[] coursesIds) {
        userDAO.createUser(user, coursesIds);
    }

    @Override
    public void deleteById(int id) {
        userDAO.deleteById(id);
    }
}
