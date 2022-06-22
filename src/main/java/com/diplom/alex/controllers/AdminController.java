package com.diplom.alex.controllers;

import com.diplom.alex.services.CourseService;
import com.diplom.alex.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.diplom.alex.constants.ApplicationConstants.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;

    @GetMapping(CABINET)
    public ModelAndView getCabinet() {
        return new ModelAndView(ADMIN_CABINET_PAGE);
    }

    @GetMapping("/students")
    public ModelAndView getStudents() {
        ModelAndView maw = new ModelAndView(STUDENTS_PAGE);
        maw.addObject("students", userService.getStudents());
        return maw;
    }

    @GetMapping("/courses")
    public ModelAndView getCoursesPage() {
        ModelAndView maw = new ModelAndView(COURSES_EDIT_PAGE);
        maw.addObject("coursesList", courseService.getAllCourses());
        return maw;
    }

    @PostMapping("/courses/{id}/remove")
    public ResponseEntity<Object> removeCourse(@PathVariable int id) {
        courseService.removeById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/courses/{id}/update", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<Object> updateCourse(@PathVariable int id, String newName) {
        courseService.updateName(id, newName);
        return ResponseEntity.ok().body(newName);
    }

    @PostMapping(value = "/students/{id}/remove", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<Object> removeUser(@PathVariable int id) {
        userService.deleteById(id);
        return ResponseEntity.ok("Студент був успішно видалений!");
    }
}
