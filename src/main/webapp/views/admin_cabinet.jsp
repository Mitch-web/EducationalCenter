<%@ page pageEncoding="UTF-8" %>
<html lang="en">

<%@ include file="jspf/head.jspf" %>

<body>
    <%@ include file="jspf/header.jspf" %>
        <main>
            <%@ include file="jspf/course_list.jspf" %>
            <div class="content_side">
                <h2 class="course_title">Оберіть потрібний функціонал</h2>
                <a href="/${role}/students">Список студентів</a>
                <a href="/${role}/courses">Список курсів</a>
            </div>
        </main>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="/assets/settings.js"></script>
        <script src="/assets/script.js"></script>
</body>

</html>