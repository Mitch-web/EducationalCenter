<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html lang="en">

<%@ include file="jspf/head.jspf" %>

<body>
    <%@ include file="jspf/header.jspf" %>
        <main>
            <%@ include file="jspf/course_list.jspf" %>
            <div class="content_side">
                <h2 class="course_title">Редагувати список предметів</h2>
                <div class="courses_list" style="margin-top: 50px;">
                    <c:set var="k" value="${0}"/>
                    <c:forEach var="course" items="${coursesList}">
                        <c:set var="k" value="${k + 1}"/>
                        <div class="course_elem" style="max-height: 150px;">
                        <p style="margin-bottom: 15px;">${k} ${course.name}
                            <a class="remove_course" href="#" style="all: unset; margin-left: 20%; margin-right:10%; border-bottom: 1px solid">
                                <input type="hidden" value="${course.id}" name="remove_input"/>
                                Видалити
                            </a>
                            <form class="update_course_form" style="">
                                <input type="text" name="update_course_input" placeholder="Нова назва"/>
                                <input type="hidden" value="${course.id}" name="update_input_id"/>
                                <input type="submit" name="update_course_btn" value="Змінити назву"/>
                            </form>
                        </p>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </main>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="/assets/settings.js"></script>
        <script src="/assets/script.js"></script>
        <script src="/assets/admin.js"></script>
</body>

</html>