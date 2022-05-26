<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html lang="en">

<%@ include file="jspf/head.jspf" %>

<body>
    <%@ include file="jspf/header.jspf" %>
        <main>
            <%@ include file="jspf/course_list.jspf" %>
            <div class="content_side">
                <h2 class="course_title">Математика</h2>
                <c:forEach var="post" items="${posts}">
                    <a href="#" class="course_list_item">
                        <h3 class="course_item_title">${post.title}</h3>
                        <p class="course_item_subtitle">${post.subtitle}</p>
                    </a>
                </c:forEach>

                <a href="#" class="course_list_item">
                    <h3 class="course_item_title">Title</h3>
                    <p class="course_item_subtitle">Sub title uihihiuhihiuhiohiogouyguyfiutfuygogoihiuhiguyuygogiouhiuhiguyfuit</p>
                </a>

                <a href="#" class="course_list_item">
                    <h3 class="course_item_title">Title</h3>
                    <p class="course_item_subtitle">Sub title uihihiuhihiuhiohiogouyguyfiutfuygogoihiuhiguyuygogiouhiuhiguyfuit</p>
                </a>
                <a href="#" class="course_list_item">
                    <h3 class="course_item_title">Title</h3>
                    <p class="course_item_subtitle">Sub title uihihiuhihiuhiohiogouyguyfiutfuygogoihiuhiguyuygogiouhiuhiguyfuit</p>
                </a>
                <a href="#" class="course_list_item">
                    <h3 class="course_item_title">Title</h3>
                    <p class="course_item_subtitle">Sub title uihihiuhihiuhiohiogouyguyfiutfuygogoihiuhiguyuygogiouhiuhiguyfuit</p>
                </a>
                <a href="#" class="course_list_item">
                    <h3 class="course_item_title">Title</h3>
                    <p class="course_item_subtitle">Sub title uihihiuhihiuhiohiogouyguyfiutfuygogoihiuhiguyuygogiouhiuhiguyfuit</p>
                </a>
                <a href="#" class="course_list_item">
                    <h3 class="course_item_title">Title</h3>
                    <p class="course_item_subtitle">Sub title uihihiuhihiuhiohiogouyguyfiutfuygogoihiuhiguyuygogiouhiuhiguyfuit</p>
                </a>
            </div>
            <a href="#" class="add_event_container">
                <p class="add_event_text">Додати завдання</p>
                <div class="add_event_round_bg">
                    <div class="add_event_border">
                        <div class="plus_elem_one"></div>
                        <div class="plus_elem_two"></div>
                    </div>
                </div>
            </a>
        </main>
    <script src="/assets/script.js"></script>
</body>

</html>