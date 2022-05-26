package com.diplom.alex.controllers;

import com.diplom.alex.model.CourseModel;
import com.diplom.alex.model.PostModel;
import com.diplom.alex.model.UserModel;
import com.diplom.alex.services.CourseService;
import com.diplom.alex.services.PostService;
import com.diplom.alex.services.RoleService;
import com.diplom.alex.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.diplom.alex.constants.ApplicationConstants.*;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CourseService courseService;

    @GetMapping(CABINET)
    public ModelAndView getCabinet() {
        return new ModelAndView(MAIN_PAGE);
    }


    @PostMapping("/registration-process")
    public ModelAndView addUser(@ModelAttribute("user") UserModel user, String role) {
        user.setRoleId(roleService.getIdByName(role));
        userService.createUser(user);
        return new ModelAndView(TEACHER_PAGE, "newUser", user);
    }

}
