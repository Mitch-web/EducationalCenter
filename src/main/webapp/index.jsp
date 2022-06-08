<%@ page pageEncoding="UTF-8" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

        <html lang="ru">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
            <link rel="icon" type="image/x-icon" href="./assets/images/favicon.ico">
            <title>Авторизация</title>
            <link rel="stylesheet" href="/assets/old.css">
        </head>

        <body>
            <div class="auth_popup_wrapper">
                <div class="auth_popup_container">
                    <div class="auth_company_logo">
                        <!--<img src="#" >-->
                    </div>
                    <p class="auth_text">Вхід</p>
                    <form:form class="auth_form" name="loginForm">
                        <label class="login_text">Логін</label><br>
                        <input type="text" class="login_inp" placeholder="Ваш логін" name="login"><br>
                        <label class="pass_text">Пароль</label>
                        <div class="passwordInpButton_cont">
                            <input type="password" class="pass_inp" placeholder="Ваш пароль" name="password">
                            <div onclick="sendAuthData()" class="post_button">Вхід</div>
                        </div>
                        <input name="auth_remember" type="checkbox" class="auth_remember_inp" />
                        <label for="auth_remember" class="auth_remember">Запам`ятати мене на цьому компьютері</label><br>
                    </form:form>
                    <input class="add_new_employee" type="submit" value="Зареєструватись" />
                </div>
            </div>

            <div class="add_new_employee_popup">
                <form:form id="reg" class="form" name="newUser">
                    <p class="close_employee_add_popup">х</p>
                    <label for="employee_name">Введіть ім`я:</label>
                    <input type="text" name="employee_name" class="employee_name" />
                    <label for="employee_name">Введіть прізвище:</label>
                    <input type="text" name="employee_lastname" class="employee_lastname" />
                    <label for="login">Придумайте логін:</label>
                    <input type="text" name="login" class="login" />
                    <label for="password">Придумайте пароль:</label>
                    <input type="password" name="password" class="password" />
                    <input class="add_new_employee_but" type="submit" value="Зареєструватись" />
                </form:form>
            </div>

            <p class="error_message"></p>
            <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
            <script src="/assets/settings.js"></script>
            <script src="/assets/code.js"></script>
            <script src="/assets/owner_cabinet.js"></script>
        </body>

        </html>