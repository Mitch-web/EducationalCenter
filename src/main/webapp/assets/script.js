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

    let add_event_container = document.querySelector(".add_event_container");
    add_event_container.addEventListener("click", (e)=>{
    console.log('dick')
        e.preventDefault();
       /* var settings = {
        			"url": `${location.origin + location.pathname}/add`,
        			"method": "POST",
        			"timeout": 0,
        			"headers": {
        				"Content-Type": "application/x-www-form-urlencoded"
        			},
        			"data": {
        				"login": `Заголовок`,
        				"password": `Описание`
        			},
        			success: function(response) {
        			    window.location.reload();
        			},
        			error: function(error){
        		        console.log(error);
        			}
        		}
        		$.ajax(settings).done(function (response) {
                			console.log(response);
                			console.log(window.location.origin + response);
                		});*/


        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/x-www-form-urlencoded");

        var urlencoded = new URLSearchParams();
        urlencoded.append("title", "lala");
        urlencoded.append("subtitle", "123123");

        var requestOptions = {
          method: 'POST',
          headers: myHeaders,
          body: urlencoded,
          redirect: 'follow'
        };

        fetch("http://localhost:8080/teacher/courses/math/add-post", requestOptions)
          .then(response => response.text())
          .then(result => console.log(result))
          .catch(error => console.log('error', error));

    })

})();