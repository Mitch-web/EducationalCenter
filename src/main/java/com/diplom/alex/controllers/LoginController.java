package com.diplom.alex.controllers;

import com.diplom.alex.model.LoginForm;
import com.diplom.alex.model.RoleModel;
import com.diplom.alex.model.UserModel;
import com.diplom.alex.services.CourseService;
import com.diplom.alex.services.RoleService;
import com.diplom.alex.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.diplom.alex.constants.ApplicationConstants.*;

@Controller
@RequestMapping(produces = "text/html; charset=UTF-8")
@SessionAttributes({"user","role","courses"})
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CourseService courseService;

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<Object> login(@ModelAttribute("loginForm") @Valid LoginForm loginForm, HttpSession session,
                                                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(INCORRECT_LOGIN_FORMAT);
        }
        UserModel userModel = userService.getUserByLogin(loginForm.getLogin());

        if (!loginForm.getPassword().equals(userModel.getPassword())) {
            return ResponseEntity.badRequest()
                    .body(USER_NOT_FOUND);
        }

        model.addAttribute("user", userModel);
        model.addAttribute("role", RoleModel.getRole(userModel).getName());
        model.addAttribute("courses", courseService.getByUserId(userModel.getId()));
        return ResponseEntity.ok(String.format("/%s%s", RoleModel.getRole(userModel).getName(), CABINET));
    }

    @PostMapping("/registration")
    public ResponseEntity<Object> addUser(@ModelAttribute("newUser") UserModel user) {
        user.setRoleId(roleService.getIdByName("student"));
        user.setGroupId(0);
        userService.createUser(user);
        return ResponseEntity.ok(MAIN_PAGE);
    }

    @GetMapping("/logout")
    public ResponseEntity logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("success");
    }
}
