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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    public ModelAndView getPostPage(@PathVariable(value = "id") int id) throws ParseException {
        ModelAndView maw = new ModelAndView("post");
        PostModel postById = postService.getPostById(id);
        if (postById.getId() ==id) {
            maw.addObject("post", postById);
            maw.addObject("timeLeft", getDatesDifferenceInDays(postById.getDeadline()));
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

    private long getDatesDifferenceInDays(String deadline) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (null != deadline) {
            Date deadlineDate = sdf.parse(deadline);
            Date currentDate = sdf.parse(sdf.format(new Date()));
            long diff = deadlineDate.getTime() - currentDate.getTime();
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        }
        return -9999L;
    }
}
