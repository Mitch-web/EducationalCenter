package com.diplom.alex.controllers;

import com.diplom.alex.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.diplom.alex.constants.ApplicationConstants.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private PostService postService;

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
    public ResponseEntity<Object> getCalendarPosts(HttpServletRequest request) {
        return new ResponseEntity<>(
                postService.getPostsByDate()
                , HttpStatus.OK);
    }
}