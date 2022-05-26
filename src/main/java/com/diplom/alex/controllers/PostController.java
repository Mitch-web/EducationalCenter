package com.diplom.alex.controllers;

import com.diplom.alex.model.PostModel;
import com.diplom.alex.services.CourseService;
import com.diplom.alex.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/teacher/courses")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private CourseService courseService;

    @PostMapping("/*/add-post")
    public ResponseEntity<Object> addNewPost(HttpServletRequest request, String title, String subtitle) {
        String[] params = request.getRequestURI().split("/");
        String courseName = courseService.getCourseNameByParam(params[3]);
        return postService.addNewPost(createAndPopulatePost(title, subtitle), courseService.getByName(courseName).getId())
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().body("Wrong");
    }

    private PostModel createAndPopulatePost(String title, String subtitle) {
        PostModel postModel = new PostModel();
        postModel.setTitle(title);
        postModel.setSubtitle(subtitle);
        return  postModel;
    }
}
