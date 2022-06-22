const remove_course_link = document.querySelectorAll('.remove_course');
if (remove_course_link) {
for(let deleteBut of remove_course_link) {
    deleteBut.addEventListener('click', function(e) {
    e.preventDefault();
    let remove_course_input = deleteBut.querySelector('[name="remove_input"]');
        if (deleteBut.value != 0) {
            var removeUrl = window.location.href;
            removeUrl = removeUrl.replace("#", "") + '/' + `${remove_course_input.value}` + "/remove";
            var settings = {
                "url": removeUrl,
                "method": "POST",
                "timeout": 0,
                "headers": {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                "success": function(response) {
                    alert("Курс був успішно видалений!");
                    window.location.reload();
                },
                error: function(response) {
                    alert(response);
                }
            };
            $.ajax(settings).done(function(response) {
                console.log(response);
            });
        }
    });
    }
}

const update_course_form = document.querySelectorAll('.update_course_form');
if (update_course_form) {
for(let updateBut of update_course_form) {
    updateBut.addEventListener('submit', function(e) {
    let update_input = updateBut.querySelector('[name="update_course_input"]');
    if (update_input.value != 0) {
        e.preventDefault();
        let update_course_input = updateBut.querySelector('[name="update_input_id"]');
            var removeUrl = window.location.href;
            removeUrl = removeUrl.replace("#", "") + '/' + `${update_course_input.value}` + "/update";
            var settings = {
                "url": removeUrl,
                "method": "POST",
                "timeout": 0,
                "headers": {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                "data": {
                    "newName": `${update_input.value}`
                },
                "success": function(response) {
                    alert("Курс був успішно оновлений! Нова назва: " + response);
                    window.location.reload();
                },
                error: function(response) {
                    alert(response);
                }
            };
            $.ajax(settings).done(function(response) {
                console.log(response);
            });
        } else {
            alert("Введіть назву курсу!");
        }
    });
    }
}

const remove_user_link = document.querySelectorAll('.remove_student');
if (remove_user_link) {
for(let deleteBut of remove_user_link) {
    deleteBut.addEventListener('click', function(e) {
    e.preventDefault();
    let remove_user_input = deleteBut.querySelector('[name="remove_input"]');
        if (deleteBut.value != 0) {
            var removeUrl = window.location.href;
            removeUrl = removeUrl.replace("#", "") + '/' + `${remove_user_input.value}` + "/remove";
            var settings = {
                "url": removeUrl,
                "method": "POST",
                "timeout": 0,
                "headers": {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                "success": function(response) {
                    alert(response);
                    window.location.reload();
                },
                error: function(response) {
                    alert(response);
                }
            };
            $.ajax(settings).done(function(response) {
                console.log(response);
            });
        }
    });
    }
}
