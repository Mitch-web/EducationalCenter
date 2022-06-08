<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html lang="en">

<%@ include file="jspf/head.jspf" %>

<body>
    <%@ include file="jspf/header.jspf" %>
        <main>
            <%@ include file="jspf/course_list.jspf" %>
            <div class="content_side">
                <h2 class="course_title">Фізика</h2>
                <c:forEach var="post" items="${posts}">
                    <a href="${requestUri}/posts/${post.id}" class="course_list_item">
                        <h3 class="course_item_title">${post.title}</h3>
                        <p class="course_item_subtitle">${post.subtitle}</p>
                    </a>
                </c:forEach>
            </div>
            <c:if test="${role == 'teacher'}">
                <a href="#" class="add_event_container">
                    <p class="add_event_text">Додати завдання</p>
                    <div class="add_event_round_bg">
                        <div class="add_event_border">
                            <div class="plus_elem_one"></div>
                            <div class="plus_elem_two"></div>
                        </div>
                    </div>
                </a>
            </c:if>
        </main>
        <%@ include file="jspf/add_post.jspf" %>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="/assets/settings.js"></script>
        <script src="/assets/script.js"></script>
</body>

</html>