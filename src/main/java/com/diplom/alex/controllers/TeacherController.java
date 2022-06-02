package com.diplom.alex.controllers;

import com.diplom.alex.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping(CABINET)
    public ModelAndView getCabinet() {
        return new ModelAndView(MAIN_PAGE);
    }

    @GetMapping("/calendar")
    public ModelAndView getCalendar() {
        return new ModelAndView(CALENDAR_PAGE);
    }

    @GetMapping("/calendar/posts")
    @ResponseBody public ResponseEntity getCalendarPosts(HttpServletRequest request) {
        return new ResponseEntity(
                postService.getPostsByDate()
                , HttpStatus.OK);
    }

}
