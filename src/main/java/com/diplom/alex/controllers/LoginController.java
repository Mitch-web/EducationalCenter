package com.diplom.alex.controllers;

import com.diplom.alex.model.LoginForm;
import com.diplom.alex.model.RoleModel;
import com.diplom.alex.model.UserModel;
import com.diplom.alex.services.CourseService;
import com.diplom.alex.services.GroupService;
import com.diplom.alex.services.RoleService;
import com.diplom.alex.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
@SessionAttributes({"user","role","courses","group"})
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private GroupService groupService;

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<Object> login(@ModelAttribute("loginForm") @Valid LoginForm loginForm,
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

        String role = RoleModel.getRole(userModel).getName();
        model.addAttribute("user", userModel);
        model.addAttribute("role", role);
        model.addAttribute("courses", courseService.getByUserId(userModel.getId()));
        if (!"admin".equals(role)) {
            model.addAttribute("group", groupService.getUserGroup(userModel));
        }
        return ResponseEntity.ok(String.format("/%s%s", RoleModel.getRole(userModel).getName(), CABINET));
    }

    @PostMapping("/registration")
    public ResponseEntity<Object> addUser(@ModelAttribute("newUser") UserModel user, String groupName) {
        user.setRoleId(roleService.getIdByName("student"));
        user.setGroupId(groupService.getIdByName(groupName));
        try {
            userService.createUser(user, groupService.getCoursesIdByGroupName(groupName));
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().body(REGISTRATION_ERROR);
        }
        return ResponseEntity.ok(MAIN_PAGE);
    }

    @GetMapping("/logout")
    public ResponseEntity logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("success");
    }
}
