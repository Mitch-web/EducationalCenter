package com.diplom.alex.services.impl;

import com.diplom.alex.dao.UserDAO;
import com.diplom.alex.model.UserMarkingModel;
import com.diplom.alex.model.UserModel;
import com.diplom.alex.services.UserService;

import java.io.UnsupportedEncodingException;
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
        userMarkings.forEach(userMarkingModel -> {
            userMarkingModel.setContent("data:" + userMarkingModel.getContentType() + ";base64," +
                    new String(userMarkingModel.getContent().getBytes(), StandardCharsets.UTF_8));
        });
        return userMarkings;
    }

    @Override
    public void createUser(UserModel user, int[] coursesIds) {
        userDAO.createUser(user, coursesIds);
    }
}
