package com.diplom.alex.controllers;

import com.diplom.alex.model.PostModel;
import com.diplom.alex.services.CourseService;
import com.diplom.alex.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.diplom.alex.constants.ApplicationConstants.*;

@Controller
@RequestMapping("*/courses")
public class CourseController {

    @Autowired
    private PostService postService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/math")
    public ModelAndView getMathCourses(HttpServletRequest request) {
        return createAndPopulateModel(MATH_PAGE, MATH, request);
    }

    @GetMapping("/physic")
    public ModelAndView getPhysicsCourses(HttpServletRequest request) {
        return createAndPopulateModel(PHYSIC_PAGE, PHYSIC, request);
    }

    @GetMapping("/chemistry")
    public ModelAndView getChemistryCourses(HttpServletRequest request) {
        return createAndPopulateModel(CHEMISTRY_PAGE, CHEMISTRY, request);
    }

    @GetMapping("/*/posts/{id}")
    public ModelAndView getPostPage(@PathVariable(value = "id") String id) {
        ModelAndView maw = new ModelAndView("post");
        PostModel postById = postService.getPostById(Integer.parseInt(id));
        if (postById.getId() == Integer.parseInt(id)) {
            maw.addObject("post", postById);
        } else {
            maw.addObject("incorrectPost", true);
        }
        return maw;
    }

    private ModelAndView createAndPopulateModel(String page, String param, HttpServletRequest request) {
        ModelAndView maw = new ModelAndView(page);
        String courseName = courseService.getCourseNameByParam(param);
        maw.addObject("posts", postService.getPostsByCourse(courseName));
        maw.addObject("requestUri", request.getRequestURI());
        return maw;
    }
}
