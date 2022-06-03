//Отправка данных
function sendAuthData() {
    preloadStart('auth_popup_wrapper');
    let login_inp = document.querySelector('.login_inp');
    let pass_inp = document.querySelector('.pass_inp');
    let error_message = document.querySelector('.error_message');
    if (login_inp.value != '' && pass_inp.value != '') {
        var addPostUrl = window.location.href;
        addPostUrl = addPostUrl.replace("#", "") + "/login";

        var settings = {
            "url": addPostUrl,
            "method": "POST",
            "timeout": 0,
            "headers": {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            "data": {
                "login": `${login_inp.value}`,
                "password": `${pass_inp.value}`
            },
            success: function(response) {
                preloadEnd('auth_popup_wrapper');
                location.replace(window.location.origin + response)
            },
            error: function(response) {
                preloadEnd('auth_popup_wrapper');
                errorAnimation(`${response.responseText}`);
            }
        };

        $.ajax(settings).done(function(response) {
            console.log(response);
            console.log(window.location.origin + response);
        });
    } else {
        preloadEnd('auth_popup_wrapper');
        errorAnimation('Пожалуйста, заполните оба поля');
    }
    // Анимация ошибки
    function errorAnimation(text) {
        error_message.innerHTML = text;
        error_message.style.display = 'block';
        setTimeout(function() {
            error_message.style.opacity = 1;
            error_message.style.transition = '1s';
        }, 100);
        setTimeout(function() {
            error_message.style.opacity = 0;
            error_message.style.transition = '1s';
        }, 3000);
        setTimeout(function() {
            error_message.style.display = 'none';
        }, 4100);
    }
}


//Прослушиватель энтер
document.addEventListener('keydown', function(e) {
    if (e.keyCode === 13) {
        sendAuthData();
    }
});







let add_new_employee = document.querySelector('.add_new_employee');
let add_new_employee_popup = document.querySelector('.add_new_employee_popup');
add_new_employee.addEventListener('click', () => {
    add_new_employee_popup.style.display = 'block';
    setTimeout(() => {
        add_new_employee_popup.style.opacity = 1;
        add_new_employee_popup.style.transition = '.5s';
    }, 100)
    add_new_empl();
});

function add_new_empl() {

    let add_new_employee_but = document.querySelector('.add_new_employee_but');
    add_new_employee_but.addEventListener('click', () => {
        preloadStart('add_new_employee_popup');
        let employee_name = document.querySelector('.employee_name');
        let employee_lastname = document.querySelector('.employee_lastname');
        let employee_login = document.querySelector('.login');
        let employee_password = document.querySelector('.password');
        if (employee_name.value == '' || employee_lastname.value == '' || employee_login.value == '' || employee_password.value == '') {
            preloadEnd('add_new_employee_popup');
            alert('Заполните все поля!');
        } else {
            var addPostUrl = window.location.href;
            addPostUrl = addPostUrl.replace("#", "") + "/registration";

            var settings = {
                "url": addPostUrl,
                "method": "POST",
                "timeout": 0,
                "headers": {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                "data": {
                    "login": `${employee_login.value}`,
                    "password": `${employee_password.value}`,
                    "firstName": `${employee_name.value}`,
                    "lastName": `${employee_lastname.value}`
                },
                success: function(response) {
                    preloadEnd('add_new_employee_popup');
                    alert('All right');
                },
                "error": function(response) {
                    preloadEnd('add_new_employee_popup');
                    console.log(response);
                }
            };

            $.ajax(settings).done(function(response) {
                console.log(response);
            });
        }
    })


    let close_employee_add_popup = document.querySelector('.close_employee_add_popup');
    close_employee_add_popup.addEventListener('click', () => {
        add_new_employee_popup.style.opacity = 0;
        add_new_employee_popup.style.transition = '.5s';
        setTimeout(() => {
            add_new_employee_popup.style.display = 'none';
        }, 100);

    });
}

function preloadStart(className) {
    let ClassNameElem = document.querySelector(`.${className}`);
    console.log(ClassNameElem);
    ClassNameElem.classList.add('loading');
}

function preloadEnd(className) {
    let ClassNameElem = document.querySelector(`.${className}`);
    console.log(ClassNameElem);
    ClassNameElem.classList.remove('loading');
}