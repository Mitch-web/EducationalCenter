<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html lang="en">

<%@ include file="jspf/head.jspf" %>

<body>
    <%@ include file="jspf/header.jspf" %>
        <main>
            <%@ include file="jspf/course_list.jspf" %>
            <div class="content_side">
                <h2 class="course_title">Загальний список учнів</h2>
                <div style="margin-top:50px;">
                    <c:forEach var="student" items="${students}">
                        <p style="margin-bottom: 20px;">Ім`я: ${student.firstName}, прізвище: ${student.lastName}
                        <a class="remove_student" href="#" style="all: unset; margin-right:30%; float:right; border-bottom: 1px solid">
                            <input type="hidden" value="${student.id}" name="remove_input"/>Видалити</a></p>
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