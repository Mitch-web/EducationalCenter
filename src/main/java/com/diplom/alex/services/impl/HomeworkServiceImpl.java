package com.diplom.alex.services.impl;

import com.diplom.alex.dao.HomeworkDAO;
import com.diplom.alex.model.HomeworkModel;
import com.diplom.alex.services.HomeworkService;

import java.util.Base64;
import java.util.List;

public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkDAO homeworkDAO;

    public HomeworkServiceImpl(HomeworkDAO homeworkDAO) {
        this.homeworkDAO = homeworkDAO;
    }

    @Override
    public List<HomeworkModel> getHomeworksByUser(int userId) {
        return homeworkDAO.getHomeworksByUser(userId);
    }

    @Override
    public HomeworkModel getHomeworkByPostAndUser(int postId, int userId) {
        return homeworkDAO.getHomeworkByPostAndUser(postId, userId);
    }

    @Override
    public void createHomeworkToPost(HomeworkModel homework, int postId, int userId) {
        homework.setContent(Base64.getEncoder().encode(homework.getContent()));
        homeworkDAO.createHomeworkToPost(homework, postId, userId);
    }
}