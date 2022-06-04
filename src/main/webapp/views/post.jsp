<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<c:set var="timeToDeadline" value="До сдачі завдання залишилось: ${timeLeft} днів"/>
<c:if test="${timeLeft < 0}">
    <c:set var="timeToDeadline" value="Термін здачі завдання вже закінчився ${timeLeft * -1} день(дні) тому"/>
</c:if>
<c:if test="${timeLeft == -9999}">
    <c:set var="timeToDeadline" value="Завдання не має терміну"/>
</c:if>
<html lang="en">

<%@ include file="jspf/head.jspf" %>

<body>
    <%@ include file="jspf/header.jspf" %>
        <main>
            <%@ include file="jspf/course_list.jspf" %>
            <div class="content_side">
                <h2 class="course_title">Завдання з курсу 'Математика'</h2>
                <c:choose>
                    <c:when test="${!incorrectPost}">
                        <div class="course_list_item" style="margin-top: 60px;">
                            <h3 class="course_item_title">Назва завдання: ${post.title}</h3>
                            <p class="course_item_subtitle">Опис завдання: ${post.subtitle}</p>
                            <c:if test="${post.deadline != null}">
                                <p>Кінцевий термін: ${post.deadline}</p>
                            </c:if>
                            <p>${timeToDeadline}</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p>Такого завдання не існує!</p>
                    </c:otherwise>
                </c:choose>
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
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="/assets/settings.js"></script>
        <script src="/assets/script.js"></script>
</body>

</html>