package com.diplom.alex.services;

import com.diplom.alex.model.PostModel;

import java.util.List;

public interface PostService {

    PostModel getPostById(int id);
    List<PostModel> getPostsByTitle(String title);
    List<PostModel> getPosts();
    List<PostModel> getPostsByCourse(String courseName);
    List<PostModel> getPostsByDeadline(String deadline);
    List<PostModel> getPostsByDate();
    boolean addNewPost(PostModel post, int courseId);
}