package com.diplom.alex.constants;

public class ApplicationConstants {

    private ApplicationConstants() {
        //UTIL CLASS
    }

    //===SQL ========================================
    public static final String USER_TABLE = "users";
    public static final String ROLE_TABLE = "roles";
    public static final String POST_TABLE = "posts";
    public static final String COURSE_TABLE = "courses";
    public static final String COURSE_HAVE_POSTS_TABLE = "courses_have_posts";
    public static final String FILES_TABLE = "files";

    //===PAGES =======================================
    public static final String TEACHER_PAGE = "cabinets/teacher_cabinet";
    public static final String MAIN_PAGE = "main";
    public static final String MATH_PAGE = "math";
    public static final String PHYSIC_PAGE = "physic";
    public static final String CHEMISTRY_PAGE = "chemistry";
    public static final String CALENDAR_PAGE = "calendar";

    //===URL ========================================
    public static final String CABINET = "/cabinet";

    //===PARAMS =====================================
    public static final String MATH = "math";
    public static final String PHYSIC = "physic";
    public static final String CHEMISTRY = "chemistry";

    //===ERROR MESSAGES==============================
    public static final String INCORRECT_LOGIN_FORMAT = "Логин указан в неверном формате";
    public static final String USER_NOT_FOUND = "Неправильный логин или пароль";

    //===PATTERNS====================================
    public static final String DATE_PATTERN = "yyyy-MM-dd";
}
