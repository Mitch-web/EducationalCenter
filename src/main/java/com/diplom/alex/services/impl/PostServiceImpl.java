package com.diplom.alex.services.impl;

import com.diplom.alex.dao.PostDAO;
import com.diplom.alex.model.FileModel;
import com.diplom.alex.model.PostModel;
import com.diplom.alex.services.PostService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.diplom.alex.constants.ApplicationConstants.DATE_PATTERN;

public class PostServiceImpl implements PostService {

    private final PostDAO postDAO;

    public PostServiceImpl(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    @Override
    public PostModel getPostById(int id) {
        Optional<PostModel> postById = postDAO.getPostById(id);
        return postById.orElseGet(PostModel::new);
    }

    @Override
    public List<PostModel> getPostsByTitle(String title) {
        return postDAO.getPostsByTitle(title);
    }

    @Override
    public List<PostModel> getPosts() {
        return postDAO.getPosts();
    }

    @Override
    public List<PostModel> getPostsByCourse(String courseName) {
        return postDAO.getPostsByCourse(courseName);
    }

    @Override
    public List<PostModel> getPostsByDeadline(String deadline) {
        return postDAO.getPostsByDeadline(deadline);
    }

    @Override
    public List<PostModel> getPostsByDate() {
        return postDAO.getPostsByDate();
    }

    @Override
    public boolean addNewPost(PostModel post, int courseId, FileModel file) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        Date deadLineDate;
        try {
            deadLineDate = sdf.parse(post.getDeadline());
            if (deadLineDate.before(new Date())) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
        return postDAO.addNewPost(post, courseId, file);
    }
}
