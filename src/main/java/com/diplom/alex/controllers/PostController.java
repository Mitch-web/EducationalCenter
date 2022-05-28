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
@RequestMapping(value = "/teacher/courses", produces = "text/html; charset=UTF-8")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/*/add-post", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<Object> addNewPost(HttpServletRequest request, String title,
                                             String subtitle, String deadline) {
        String[] params = request.getRequestURI().split("/");
        String courseName = courseService.getCourseNameByParam(params[3]);
        return postService.addNewPost(createAndPopulatePost(title, subtitle, deadline),
                courseService.getByName(courseName).getId())
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().body("Wrong");
    }

    private PostModel createAndPopulatePost(String title, String subtitle, String deadline) {
        PostModel postModel = new PostModel();
        postModel.setTitle(title);
        postModel.setSubtitle(subtitle);
        postModel.setDeadline(deadline);
        return  postModel;
    }
}
