package com.diplom.alex.services.impl;

import com.diplom.alex.dao.HomeworkDAO;
import com.diplom.alex.model.HomeworkModel;
import com.diplom.alex.services.HomeworkService;

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
        homeworkDAO.createHomeworkToPost(homework, postId, userId);
    }

    @Override
    public boolean updateWithMark(int postId, int userId, int mark, String comment) {
        return homeworkDAO.updateWithMark(postId, userId, mark, comment);
    }
}
