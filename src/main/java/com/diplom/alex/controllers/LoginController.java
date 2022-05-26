package com.diplom.alex.controllers;

import com.diplom.alex.model.LoginForm;
import com.diplom.alex.model.RoleModel;
import com.diplom.alex.model.UserModel;
import com.diplom.alex.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.diplom.alex.constants.ApplicationConstants.*;

@Controller
@RequestMapping(produces = "text/html; charset=UTF-8")
@SessionAttributes({"user","role"})
public class LoginController {

    @Autowired
    private UserService userService;

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
        model.addAttribute("user", userModel);
        model.addAttribute("role", RoleModel.getRole(userModel).getName());
        return ResponseEntity.ok(String.format("/%s%s", RoleModel.getRole(userModel).getName(), CABINET));
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        ModelAndView maw = new ModelAndView("");
        session.removeAttribute("user");
        return maw;
    }

}
