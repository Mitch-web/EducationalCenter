<%@ page pageEncoding="UTF-8" %>
<html lang="en">

<%@ include file="jspf/head.jspf" %>

<body>
    <%@ include file="jspf/header.jspf" %>
        <main>
            <%@ include file="jspf/course_list.jspf" %>
            <div class="content_side">
                <h2 class="course_title">Оберіть потрібний предмет</h2>
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
        <script src="../assets/script.js"></script>
</body>

</html>