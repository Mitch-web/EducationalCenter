package com.diplom.alex.dao;

import com.diplom.alex.model.HomeworkModel;

import java.util.List;

public interface HomeworkDAO {
    List<HomeworkModel> getHomeworksByUser(int userId);
    HomeworkModel getHomeworkByPostAndUser(int postId, int userId);
    void createHomeworkToPost(HomeworkModel homework, int postId, int userId);
}
