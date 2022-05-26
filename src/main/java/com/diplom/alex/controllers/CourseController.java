package com.diplom.alex.controllers;

import com.diplom.alex.services.CourseService;
import com.diplom.alex.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.diplom.alex.constants.ApplicationConstants.*;

@Controller
@RequestMapping("*/courses")
public class CourseController {

    @Autowired
    private PostService postService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/math")
    public ModelAndView getMathCourses() {
        return createAndPopulateModel(MATH_PAGE, MATH);
    }

    @GetMapping("/physic")
    public ModelAndView getPhysicsCourses() {
        return createAndPopulateModel(PHYSIC_PAGE, PHYSIC);
    }

    @GetMapping("/chemistry")
    public ModelAndView getChemistryCourses() {
        return createAndPopulateModel(CHEMISTRY_PAGE, CHEMISTRY);
    }

    private ModelAndView createAndPopulateModel(String page, String param) {
        ModelAndView maw = new ModelAndView(page);
        String courseName = courseService.getCourseNameByParam(param);
        maw.addObject("posts", postService.getPostsByCourse(courseName));
        return maw;
    }
}
