package com.diplom.alex.controllers;

import com.diplom.alex.model.*;
import com.diplom.alex.services.CourseService;
import com.diplom.alex.services.FileService;
import com.diplom.alex.services.PostService;
import com.diplom.alex.services.UserService;
import com.diplom.alex.services.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.SQLWarningException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    public ModelAndView getCoursePosts(HttpServletRequest request, @PathVariable int courseId) {
        ModelAndView maw = new ModelAndView("course_page");
        CourseModel course = courseService.getById(courseId);
        maw.addObject("course", course);
        maw.addObject("posts", postService.getPostsByCourse(course.getName()));
        maw.addObject("requestUri", request.getRequestURI());
        return maw;
    }

    @GetMapping("/{courseId}/posts/{id}")
    public ModelAndView getPostPage(@PathVariable(value = "id") int id, HttpSession session,
                                    @PathVariable(value = "courseId") int courseId) throws ParseException {
        ModelAndView maw = new ModelAndView("post");
        PostModel postById = postService.getPostById(id);
        if (postById.getId() == id) {
            int userId = ((UserModel) session.getAttribute("user")).getId();
            String role = (String) session.getAttribute("role");
            maw.addObject("isPostTaskVisible", isPostTaskVisible(role, userId, id));
            maw.addObject("course", courseService.getById(courseId));
            List<UserMarkingModel> userMarkings = userService.getByPostId(id);
            maw.addObject("userMarkings", userMarkings);
            if ("student".equals(role)) {
                 Optional<UserMarkingModel> userMarking = userMarkings.stream()
                         .filter(marking -> userId == marking.getId())
                         .findFirst();
                if (userMarking.isPresent()) {
                    if (userMarking.get().getMark() != -1) {
                        maw.addObject("mark", "???????? ????????????: " + userMarking.get().getMark());
                        if (null != userMarking.get().getComment() && !"".equals(userMarking.get().getComment())) {
                            maw.addObject("comment", "????????????: " + userMarking.get().getComment());
                        } else {
                            maw.addObject("comment", "?????? ????????????????????");
                        }
                    } else {
                        maw.addObject("mark", "???????????????? ???? ????????????");
                    }
                }
            }
            getAndShowPost(maw, postById);
        } else {
            maw.addObject("incorrectPost", true);
        }
        return maw;
    }

    private boolean isPostTaskVisible(String role, int userId, int postId) {
        return role.equals("student") && null == homeworkService.getHomeworkByPostAndUser(postId, userId);
    }

    @PostMapping("/{courseId}/posts/{id}")
    public ResponseEntity<Object> postMark(@PathVariable("id") int id, int userId, int mark, String comment) {
        return homeworkService.updateWithMark(id, userId, mark, comment)
                ? ResponseEntity.ok().body(mark)
                : ResponseEntity.badRequest().body("?????????????? ?????????????? ?? ?????????????? ?????????????????? ????????????");
    }

    @GetMapping("/{courseId}/posts/{id}/{fileName}")
    public ModelAndView getHomeworkPage(@PathVariable("id") int id, int userId) {
        ModelAndView maw = new ModelAndView("file");
        HomeworkModel homework = homeworkService.getHomeworkByPostAndUser(id, userId);
        maw.addObject("imageType", homework.getContentType());
        maw.addObject("image", new String(homework.getContent(), StandardCharsets.UTF_8));
        return maw;
    }

    @PostMapping("/{courseId}/posts/{id}/remove")
    public ResponseEntity removePost(@PathVariable("courseId") int courseId, @PathVariable("id") int id) {
        try {
            postService.removePost(id, courseId);
            return ResponseEntity.ok().build();
        } catch (SQLWarningException e) {
            return ResponseEntity.badRequest().body("?????????????? ?????????????? ?? ?????????????? ?????????????????? ????????????????");
        }
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
