<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<c:set var="timeToDeadline" value="До сдачі завдання залишилось: ${timeLeft} днів" />
<c:if test="${timeLeft < 0}">
    <c:set var="timeToDeadline" value="Термін здачі завдання вже закінчився ${timeLeft * -1} день(дні) тому" />
</c:if>
<c:if test="${timeLeft == -9999}">
    <c:set var="timeToDeadline" value="Завдання не має терміну" />
</c:if>
<html lang="en">

<%@ include file="jspf/head.jspf" %>

    <body>
        <%@ include file="jspf/header.jspf" %>
            <main>
                <%@ include file="jspf/course_list.jspf" %>
                    <div class="content_side">
                        <h2 class="course_title">Завдання з курсу '${course.name}'</h2>
                        <c:choose>
                            <c:when test="${!incorrectPost}">
                                <div class="course_list_item" style="margin-top: 60px;">
                                    <h3 class="course_item_title">Назва завдання: ${post.title}</h3>
                                    <p class="course_item_subtitle">Опис завдання: ${post.subtitle}</p>
                                    <c:if test="${post.deadline != null}">
                                        <p>Кінцевий термін: ${post.deadline}</p>
                                    </c:if>
                                    <p>${timeToDeadline}</p>
                                    <c:if test="${image != null}">
                                        <p style="margin-bottom: 20px;">Прикріплений файл:</p>
                                        <img src="data:${imageType};base64,${image}" width="450" height="250" />
                                    </c:if>
                                    <c:choose>
                                        <c:when test="${isPostTaskVisible}">
                                            <a href="#" class="add_homework_container"><p class="add_homework_text" style="font-size: 20; color: #115571; margin-top: -32px;">Здати роботу</p></a>
                                        </c:when>
                                        <c:otherwise>
                                            <c:if test="${role == 'student'}"><p style="margin-top: 30px;">Ви вже відправили свою відповідь</p></c:if>
                                        </c:otherwise>
                                    </c:choose>
                                    <div class="popup_window">
                                        <p class="close">X</p>
                                        <form action="#" id="add_homework_form">
                                            <label>Додати файл</label>
                                            <input type="file" name="file_add_homework">
                                            <input type="submit" value="Відправити">
                                        </form>
                                    </div>
                                    <c:if test="${role == 'teacher'}">
                                        <div class="marks_content_container">
                                            <p class="show_students_list">Показати список студентів</p>
                                            <div class="marks_list_container">
                                                <ul>
                                                    <li class="marks_list_titles">
                                                        <p>Группа</p>
                                                        <p>ПІБ</p>
                                                        <p>Завдання</p>
                                                        <p>Оцінка</p>
                                                    </li>
                                                    <c:forEach var="userMarking" items="${userMarkings}">
                                                        <li class="marks_list_elems" data-id="${userMarking.id}">
                                                            <p>${userMarking.name}</p>
                                                            <p>${userMarking.lastName} ${userMarking.firstName}</p>
                                                            <p style="margin-top: -60px;"><a href="/${role}/courses/${course.id}/posts/${post.id}/${userMarking.fileName}?userId=${userMarking.id}" target="_blank">${userMarking.fileName}</a></p>
                                                            <c:choose>
                                                                <c:when test="${userMarking.mark != -1}">
                                                                    <p>${userMarking.mark}</p>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <div class="mark_value">
                                                                        <input type="number">
                                                                    </div>
                                                                    <div class="check_mark_btn">
                                                                        <button>Оцінити</button>
                                                                    </div>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                    </c:if>
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
            <script src="/assets/send_homework.js"></script>
            <script>
                let show_students_list = document.querySelector('.show_students_list');
                let ul_list = document.querySelector('.marks_list_container ul');
                show_students_list.addEventListener('click', function() {
                    ul_list.classList.toggle('active');
                });
                //add mark func

                let students_works = document.querySelectorAll('.marks_list_elems');
                for (let work_container of students_works) {
                    if (work_container.querySelector('.check_mark_btn button')) {
                        let studentId = work_container.getAttribute('data-id');
                        let estimate_but = work_container.querySelector('.check_mark_btn button');
                        let mark = work_container.querySelector('.mark_value input');
                        let current_url = window.location.href;
                        estimate_but.addEventListener('click', function() {
                            if (mark.value == '') {
                                alert('Внесіть оцінку');
                            } else if (Number(mark.value) < 0 || Number(mark.value) > 100) {
                                alert('Внесіть оцінку від 0 до 100');
                            } else {
                                var settings = {
                                    "url": current_url,
                                    "method": "POST",
                                    "timeout": 0,
                                    "headers": {
                                        "Content-Type": "application/x-www-form-urlencoded"
                                    },
                                    "data": {
                                        "userId": Number(studentId),
                                        "mark": Number(mark.value)
                                    }
                                };

                                $.ajax(settings).done(function(response) {
                                    console.log(response);
                                });
                            }
                        });
                    }
                }
            </script>
    </body>

</html>