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
    let send_project_form;

    if (add_event_container != null) {
        send_project_form = popup_window.querySelector('form');
        add_event_container.addEventListener('click', () => {
            popup_window.classList.add('active');
        });
        close.addEventListener('click', () => {
            popup_window.classList.remove('active');
        });

        let title_inp = send_project_form.querySelector('input[name="title-inp"]');
        let description_inp = send_project_form.querySelector('textarea[name="description-inp"]');
        let deadline_inp = send_project_form.querySelector('input[name="deadline-inp"]');
        let file_inp = send_project_form.querySelector('input[name="file_add_foto"]');

        send_project_form.addEventListener('submit', function(e) {
            e.preventDefault();
            if (title_inp.value != 0 && description_inp.value != 0) {
                preloadStart('popup_window');
                var myHeaders = new Headers();

                var formdata = new FormData();
                formdata.append("title", `${title_inp.value}`);
                formdata.append("subtitle", `${description_inp.value}`);
                formdata.append("deadline", `${deadline_inp.value}`);
                formdata.append("fileType", `${file_inp.files[0].type}`);
                let blb = new Blob([file_inp.files[0]]);
                let url = URL.createObjectURL(blb);
                let reader = new FileReader();
                reader.readAsDataURL(blb);
                reader.onloadend = function() {
                    var base64data = reader.result;
                    let str1 = base64data;

                    let from1 = str1.search('base64') + 7;
                    let to1 = str1.length;
                    let newstr1 = str1.substr(from1, to1);
                    formdata.append('file', newstr1);

                var requestOptions = {
                    method: 'POST',
                    headers: myHeaders,
                    body: formdata,
                    redirect: 'follow'
                };

                var addPostUrl = window.location.href;
                addPostUrl = addPostUrl.replace("#", "") + "/add-post";
                console.log(addPostUrl);

                fetch(addPostUrl, requestOptions)
                    .then(response => {
                        if (response.status == 400) {
                            alert("Введіть коректну дату!");
                            preloadEnd('popup_window');
                        }
                    })
                    .then(result => {
                        preloadEnd('popup_window');
                        popup_window.classList.remove('active');
                        window.location.reload();
                    })
                .catch(error => alert(error));
                }
            } else {
                alert('Введіть дані для створення завдання!');
            }
        });
    }
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