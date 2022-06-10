package com.diplom.alex.controllers;

import com.diplom.alex.model.CourseModel;
import com.diplom.alex.model.FileModel;
import com.diplom.alex.model.PostModel;
import com.diplom.alex.services.CourseService;
import com.diplom.alex.services.FileService;
import com.diplom.alex.services.PostService;
import com.diplom.alex.services.UserService;
import com.diplom.alex.services.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.diplom.alex.constants.ApplicationConstants.*;

@Controller
@RequestMapping("*/courses")
public class CourseController {

    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private FileService fileService;
    @Autowired
    private HomeworkService homeworkService;

    @GetMapping("/{courseId}")
    public ModelAndView getMathCourses(HttpServletRequest request, @PathVariable int courseId) {
        ModelAndView maw = new ModelAndView("course_page");
        CourseModel course = courseService.getById(courseId);
        maw.addObject("course", course);
        maw.addObject("posts", postService.getPostsByCourse(course.getName()));
        maw.addObject("requestUri", request.getRequestURI());
        return maw;
    }

    @GetMapping("/{courseId}/posts/{id}")
    public ModelAndView getPostPage(@PathVariable(value = "id") int id, @PathVariable(value = "courseId") int courseId)
                                                                                                throws ParseException {
        ModelAndView maw = new ModelAndView("post");
        PostModel postById = postService.getPostById(id);
        if (postById.getId() == id) {
            maw.addObject("course", courseService.getById(courseId));
            maw.addObject("userMarkings", userService.getByPostId(id));
            getAndShowPost(maw, postById);
        } else {
            maw.addObject("incorrectPost", true);
        }
        return maw;
    }

    @PostMapping("/{courseId}/posts/{id}")
    public ResponseEntity<Object> postMark(@PathVariable("id") int id, int userId, int mark) {
        return homeworkService.updateWithMark(id, userId, mark)
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().body("Виникла помилка в процесі виконання запиту");
    }

    private long getDatesDifferenceInDays(String deadline) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        if (null != deadline) {
            Date deadlineDate = sdf.parse(deadline);
            Date currentDate = sdf.parse(sdf.format(new Date()));
            long diff = deadlineDate.getTime() - currentDate.getTime();
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        }
        return -9999L;
    }

    private void getAndShowPost(ModelAndView maw, PostModel postById) throws ParseException {
        maw.addObject("post", postById);
        maw.addObject("timeLeft", getDatesDifferenceInDays(postById.getDeadline()));
        getAndShowFile(maw, postById);
    }

    private void getAndShowFile(ModelAndView maw, PostModel postById) {
        FileModel file = fileService.getFileById(postById.getFileId());
        if (null != file) {
            maw.addObject("imageType", file.getContentType());
            maw.addObject("image", new String(file.getContent(), StandardCharsets.UTF_8));
        }
    }
}
