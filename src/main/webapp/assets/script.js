(function() {
    const header = document.querySelector('header');
    const account_icon = header.querySelector('.account_icon');
    const account_container = header.querySelector('.account_settings_container');
    account_icon.addEventListener('click', () => {
        account_container.classList.toggle('active');
    });

    //teacher checker

    //const add_task;
    let address = window.location.href;
    let url = new URL(address);
    let add_task_cont;
    if (document.querySelector('.add_event_container')) {
        add_task_cont = document.querySelector('.add_event_container');
    }

    if (url.href.includes('student')) {
        add_task_cont.style.display = "none";
    }

    const exitButton = document.querySelector(".account_settings_container button");
    exitButton.addEventListener("click", () => {
        var logoutUrl = window.location.origin;
        logoutUrl = logoutUrl.replace("#", "") + "/logout";
        var settings = {
            "url": logoutUrl,
            "method": "GET",
            "timeout": 0,
            "headers": {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            "success": function(response) {
                location.replace('/');
            },
            error: function(response) {
                alert("Fail");
            }
        };
        $.ajax(settings).done(function(response) {
            console.log(response);
        });
    });

    const add_event_container = document.querySelector('.add_event_container');
    const popup_window = document.querySelector('.popup_window');
    const close = document.querySelector('.close');
    const send_project_form = popup_window.querySelector('form');

    add_event_container.addEventListener('click', () => {
        popup_window.classList.add('active');
    });
    close.addEventListener('click', () => {
        popup_window.classList.remove('active');
    });

    let title_inp = send_project_form.querySelector('input[name="title-inp"]');
    let description_inp = send_project_form.querySelector('textarea[name="description-inp"]');
    let deadline_inp = send_project_form.querySelector('input[name="deadline-inp"]');

    send_project_form.addEventListener('submit', function(e) {
        e.preventDefault();
        if (title_inp.value != 0 && description_inp.value != 0) {
            var myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/x-www-form-urlencoded");

            var urlencoded = new URLSearchParams();
            urlencoded.append("title", `${title_inp.value}`);
            urlencoded.append("subtitle", `${description_inp.value}`);
            urlencoded.append("deadline", `${deadline_inp.value}`);

            var requestOptions = {
                method: 'POST',
                headers: myHeaders,
                body: urlencoded,
                redirect: 'follow'
            };

            var addPostUrl = window.location.href;
            addPostUrl = addPostUrl.replace("#", "") + "/add-post";

            fetch(addPostUrl, requestOptions)
                .then(() => {
                    preloadStart('popup_window');
                })
                .then(response => {
                    if (response.status == 400) {
                        alert("Введіть коректну дату!");
                    }
                })
                .then((result) => {
                    preloadEnd('popup_window');
                    popup_window.classList.remove('active');
                })
                .catch(error => alert('error', error));
        } else {
            alert('Введіть дані для створення завдання!');
        }
    });
})
();

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