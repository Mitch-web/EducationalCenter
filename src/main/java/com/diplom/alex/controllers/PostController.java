package com.diplom.alex.controllers;

import com.diplom.alex.model.FileModel;
import com.diplom.alex.model.PostModel;
import com.diplom.alex.services.CourseService;
import com.diplom.alex.services.FileService;
import com.diplom.alex.services.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/teacher/courses", produces = "text/html; charset=UTF-8")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private FileService fileService;

    @PostMapping(value = "/{courseName}/add-post", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<Object> addNewPost(@PathVariable String courseName, String title,
                                             String subtitle, String deadline,
                                             String fileType, String file) {
        FileModel fileToUpload = getFileOrEmpty(file, fileType);
        return postService.addNewPost(createAndPopulatePost(title, subtitle, deadline),
                courseService.getByName(courseService.getCourseNameByParam(courseName)).getId(), fileToUpload)
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().body("Wrong");
    }

    private FileModel getFileOrEmpty(String file, String fileType) {
        return null != file ? new FileModel(0, fileType, file.getBytes()) : null;
    }

    private PostModel createAndPopulatePost(String title, String subtitle, String deadline) {
        PostModel postModel = new PostModel();
        postModel.setTitle(title);
        postModel.setSubtitle(subtitle);
        postModel.setDeadline(deadline);
        return  postModel;
    }
}
