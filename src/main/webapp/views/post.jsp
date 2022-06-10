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
                                                <p>Прикріплений файл:</p>
                                                <img src="data:${imageType};base64,${image}" width="350" height="200" />
                                            </c:if>
                                            <c:if test="${role == 'student'}">
                                                <a href="#" class="add_homework_container">
                                                    <p class="add_homework_text" style="font-size: 20; color: #115571;">Здати роботу</p>
                                                </a>
                                            </c:if>
                                            <div class="popup_window">
                                                <p class="close">X</p>
                                                <form action="#" id="add_homework_form">
                                                    <label>Додати файл</label>
                                                    <input type="file" name="file_add_homework">
                                                    <input type="submit" value="Відправити">
                                                </form>
                                            </div>
                                            <!--<img src="data:${homeworkType};base64,${homeworkImage}" width="250" height="100"/>-->
                                            <c:if test="${role == 'teacher'}">
                                                <div>
                                                    <c:forEach var="userMarking" items="${userMarkings}">
                                                        <c:if test="${userMarking.mark == 0}">
                                                            <c:set var="mark" value="Не оцінено" />
                                                        </c:if>
                                                        <c:if test="${userMarking.mark != 0}">
                                                            <c:set var="mark" value="Завдання не має терміну" />
                                                        </c:if>
                                                        <div class="marks_content_container">
                                                            <p class="show_students_list">Показати список задач</p>
                                                            <div class="marks_list_container">
                                                                <ul>
                                                                    <li class="marks_list_titles">
                                                                        <p>Группа</p>
                                                                        <p>ФИО</p>
                                                                        <p>Завдання</p>
                                                                        <p>Оцинка</p>
                                                                    </li>
                                                                    <li class="marks_list_elems">
                                                                        <p>КН-15</p>
                                                                        <p>Стоволос Алексей Батькович</p>
                                                                        <p>homework.docx</p>
                                                                        <p>5</p>
                                                                        <div class="check_mark_btn">
                                                                            <button>Оциныты</button>
                                                                        </div>
                                                                    </li>
                                                                    <li class="marks_list_elems">
                                                                        <p>КН-15</p>
                                                                        <p>Стоволос Алексей Батькович</p>
                                                                        <p>homework.docx</p>
                                                                        <p>5</p>
                                                                        <div class="check_mark_btn">
                                                                            <button>Оциныты</button>
                                                                        </div>
                                                                    </li>
                                                                    <li class="marks_list_elems">
                                                                        <p>КН-15</p>
                                                                        <p>Стоволос Алексей Батькович</p>
                                                                        <p>homework.docx</p>
                                                                        <p>5</p>
                                                                        <div class="check_mark_btn">

                                                                        </div>
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <!-- <p>${userMarking.name}, ${userMarking.firstName}, ${userMarking.lastName}, ${userMarking.contentType}, ${mark}
                                                            <input</p>-->
                                                    </c:forEach>
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
                        })
                    </script>
            </body>

        </html>