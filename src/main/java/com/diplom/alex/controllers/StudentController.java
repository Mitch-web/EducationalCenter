package com.diplom.alex.controllers;

import com.diplom.alex.model.HomeworkModel;
import com.diplom.alex.model.UserModel;
import com.diplom.alex.services.HomeworkService;
import com.diplom.alex.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.diplom.alex.constants.ApplicationConstants.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private PostService postService;
    @Autowired
    private HomeworkService homeworkService;

    @GetMapping(CABINET)
    public ModelAndView getStudentCabinet() {
        return new ModelAndView(MAIN_PAGE);
    }

    @GetMapping("/calendar")
    public ModelAndView getCalendar() {
        return new ModelAndView(CALENDAR_PAGE);
    }

    @GetMapping("/calendar/posts")
    @ResponseBody
    public ResponseEntity<Object> getCalendarPosts() {
        return new ResponseEntity<>(
                postService.getPostsByDate()
                , HttpStatus.OK);
    }

    @PostMapping("/courses/*/posts/{postId}/add-homework")
    public ResponseEntity addHomeWorkFile(HttpSession session, @PathVariable int postId,
                                          String fileType, String file) {
        if (null != file) {
            UserModel currentUser = (UserModel) session.getAttribute("user");
            HomeworkModel homeworkToUpload = new HomeworkModel(0, fileType, file.getBytes(), (short) 0);
            homeworkService.createHomeworkToPost(homeworkToUpload, postId, currentUser.getId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().body("Файл не був завантажений!");
    }
}