package com.diplom.alex.controllers;

import com.diplom.alex.model.UserModel;
import com.diplom.alex.services.CourseService;
import com.diplom.alex.services.PostService;
import com.diplom.alex.services.UserService;
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
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    @GetMapping(CABINET)
    public ModelAndView getCabinet() {
        return new ModelAndView(MAIN_PAGE);
    }

    @GetMapping("/courses/all")
    public ModelAndView getAllCourses() {
        return new ModelAndView(COURSES_EDIT_PAGE);
    }

    @PostMapping(CABINET + "/{courseId}/remove")
    public ResponseEntity removeCourseById(@PathVariable("courseId") int courseId) {
        courseService.removeById(courseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/calendar")
    public ModelAndView getCalendar() {
        return new ModelAndView(CALENDAR_PAGE);
    }

    @GetMapping("/calendar/posts")
    @ResponseBody public ResponseEntity getCalendarPosts() {
        return new ResponseEntity(
                postService.getPostsByDate()
                , HttpStatus.OK);
    }

    @GetMapping("/students")
    public ModelAndView getAllStudents() {
        ModelAndView maw = new ModelAndView("students");
        maw.addObject("students", userService.getUsers());
        return maw;
    }

}
