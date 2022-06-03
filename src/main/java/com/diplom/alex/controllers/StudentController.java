package com.diplom.alex.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.diplom.alex.constants.ApplicationConstants.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping(CABINET)
    public ModelAndView getStudentCabinet() {
        return new ModelAndView(MAIN_PAGE);
    }

    @GetMapping("/calendar")
    public ModelAndView getCalendar() {
        return new ModelAndView(CALENDAR_PAGE);
    }
}