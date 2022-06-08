const add_homework_container = document.querySelector('.add_homework_container');
const popup_window = document.querySelector('.popup_window');
const close = document.querySelector('.close');

add_homework_container.addEventListener('click', () => {
    popup_window.classList.add('active');
});
close.addEventListener('click', () => {
    popup_window.classList.remove('active');
});
const send_homework_form = document.querySelector('form[id="add_homework_form"]');
let homework_inp = send_homework_form.querySelector('input[name="file_add_homework"]');

send_homework_form.addEventListener('submit', function(e) {
    e.preventDefault();
    if (homework_inp.value != 0) {
        var myHeaders = new Headers();

        var formdata = new FormData();
        formdata.append("fileType", `${homework_inp.files[0].type}`);
        let blb = new Blob([homework_inp.files[0]]);
        let url = URL.createObjectURL(blb);
        let reader = new FileReader();
        reader.readAsDataURL(blb)
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
        addPostUrl = addPostUrl.replace("#", "") + "/add-homework";

        fetch(addPostUrl, requestOptions)
            .then(response => console.log(response))
            .then(result => {
                popup_window.classList.remove('active');
                //window.location.reload();
            })
        .catch(error => alert(error));
        }
    } else {
        alert('Файл не був завантажений!');
    }
});