package com.diplom.alex.services;

import com.diplom.alex.model.HomeworkModel;

import java.util.List;

public interface HomeworkService {

    List<HomeworkModel> getHomeworksByUser(int userId);
    HomeworkModel getHomeworkByPostAndUser(int postId, int userId);
    void createHomeworkToPost(HomeworkModel homework, int postId, int userId);
    boolean updateWithMark(int postId, int userId, int mark);

}
