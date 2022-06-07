package com.diplom.alex.dao;

import com.diplom.alex.model.FileModel;
import com.diplom.alex.model.PostModel;

import java.util.List;
import java.util.Optional;

public interface PostDAO {

    Optional<PostModel> getPostById(int id);
    List<PostModel> getPostsByTitle(String title);
    List<PostModel> getPosts();
    List<PostModel> getPostsByCourse(String courseName);
    List<PostModel> getPostsByDeadline(String deadline);
    List<PostModel> getPostsByDate();
    boolean addNewPost(PostModel post, int courseId, FileModel file);
}
