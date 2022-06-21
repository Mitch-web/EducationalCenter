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
    public static final String HOMEWORKS_TABLE = "homeworks";
    public static final String USERS_HAVE_POSTS_TABLE = "users_have_posts";
    public static final String GROUPS_TABLE = "`groups`";
    public static final String COURSES_HAVE_USERS_TABLE = "courses_have_users";

    //===PAGES =======================================
    public static final String MAIN_PAGE = "main";
    public static final String CALENDAR_PAGE = "calendar";
    public static final String COURSES_EDIT_PAGE = "courses_edit";
    public static final String ADMIN_CABINET_PAGE = "admin_cabinet";
    public static final String STUDENTS_PAGE = "students";

    //===URL ========================================
    public static final String CABINET = "/cabinet";

    //===PARAMS =====================================
    public static final String MATH = "math";
    public static final String PHYSIC = "physic";
    public static final String CHEMISTRY = "chemistry";

    //===ERROR MESSAGES==============================
    public static final String INCORRECT_LOGIN_FORMAT = "Логин має невірний формат";
    public static final String USER_NOT_FOUND = "Невірний логін або пароль";
    public static final String REGISTRATION_ERROR = "Помилка під час реєстрації";

    //===PATTERNS====================================
    public static final String DATE_PATTERN = "yyyy-MM-dd";
}
