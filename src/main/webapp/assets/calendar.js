/*=====# RESPONSE EXAMPLE =============
let response = [{
        date: '2022-05-31',
        title: 'Первый тайтл',
        subtitle: 'Илюша ну шо? Никита может?'
    },
    {
        date: '2022-06-04',
        title: 'Второй тайтл',
        subtitle: 'В епаме такому не учат'
    },
    {
        date: '2022-06-10',
        title: 'Третий тайтл',
        subtitle: 'Вот это я понимаю код'
    },
    {
        date: '2022-06-21',
        title: 'Четвёртый тайтл',
        subtitle: 'Сдавайся русский Иван, помой пизду русской Наташке'
    },
    {
        date: '2022-07-01',
        title: 'Пятый тайтл',
        subtitle: 'А шо я? Я JavaScript FrontEnd Super Ultra Senior TeamLead MEGA Developer'
    }
]*/

let response;

async function get_posts() {
    var myHeaders = new Headers();
    myHeaders.append("Cookie", "JSESSIONID=84599CA0A241814DA778ED360267FB31");

    var urlencoded = new URLSearchParams();

    var requestOptions = {
      headers: myHeaders,
      redirect: 'follow'
    };

    var posts;

    return fetch("https://educationcenter1.herokuapp.com/teacher/posts", requestOptions)
      .then(response => response.text())
      .then((result) => {
          console.log(JSON.parse(result));
          //return JSON.parse('[' + result + ']');
          response = JSON.parse(result);
      })
      .catch(error => console.log('error', error));
};
window.onload = async function (){
await get_posts();
await getBindedDate(2022, get_curr_month + 1);
}

//response = get_posts();

let get_curr_month;

let getCurrMonthNow = new Date();
get_curr_month = getCurrMonthNow.getMonth();

let month_view = ['Січень', 'Лютий', 'Березень', 'Квітень', 'Травень', 'Червень', 'Липень', 'Серпень', 'Вересень', 'Жовтень', 'Листопад', 'Грудень'];

let text_month = document.querySelector('.month');
text_month.innerHTML = month_view[get_curr_month];



function createCalendar(elem, year, month) {

    let mon = month - 1; // месяцы в JS идут от 0 до 11, а не от 1 до 12
    let d = new Date(year, mon);

    let table = '<table><tr><th>пн</th><th>вт</th><th>ср</th><th>чт</th><th>пт</th><th>сб</th><th>нд</th></tr><tr>';

    // пробелы для первого ряда
    // с понедельника до первого дня месяца
    // * * * 1  2  3  4
    for (let i = 0; i < getDay(d); i++) {
        table += '<td></td>';
    }

    // <td> ячейки календаря с датами
    while (d.getMonth() == mon) {

        if (getDay(d) == 5 || getDay(d) == 6) {
            table += '<td style="background-color:red;" onclick="post_container.innerHTML=``">' + d.getDate() + '</td>';
        } else if (getDay(d) != 5 || getDay(d) != 6) {
            table += '<td onclick="post_container.innerHTML=``">' + d.getDate() + '</td>';
        }

        if (getDay(d) % 7 == 6) { // вс, последний день - перевод строки
            table += '</tr><tr>';
        }

        d.setDate(d.getDate() + 1);
    }

    // добить таблицу пустыми ячейками, если нужно
    // 29 30 31 * * * *
    if (getDay(d) != 0) {
        for (let i = getDay(d); i < 7; i++) {
            table += '<td></td>';
        }
    }

    // закрыть таблицу
    table += '</tr></table>';

    elem.innerHTML = table;
}

function getDay(date) { // получить номер дня недели, от 0 (пн) до 6 (вс)
    let day = date.getDay();
    if (day == 0) day = 7; // сделать воскресенье (0) последним днем
    return day - 1;
}

//month arrows

let arr_prev = document.querySelector('.arr_prev');
let arr_next = document.querySelector('.arr_next');
let n = 0;
let n1 = 1;
let yearNow = 2022;
let current_year = document.querySelector('.current_year');
current_year.innerHTML = yearNow;
arr_prev.addEventListener('click', function() {
    --n;
    --n1;
    if (get_curr_month + (n) == -1) {
        --yearNow;
        n = 11 - get_curr_month;
        n1 = 12 - get_curr_month;
        createCalendar(calendar, yearNow, get_curr_month + (n1));
        getBindedDate(yearNow, get_curr_month + (n1));
        text_month.innerHTML = month_view[get_curr_month + (n)];
        current_year.innerHTML = yearNow;
    } else {
        createCalendar(calendar, yearNow, get_curr_month + (n1));
        getBindedDate(yearNow, get_curr_month + (n1));
        text_month.innerHTML = month_view[get_curr_month + (n)];
    }
});

arr_next.addEventListener('click', function() {
    ++n;
    ++n1;
    if (get_curr_month + (n) == 12) {
        ++yearNow;
        n = -get_curr_month;
        n1 = -get_curr_month + 1;
        createCalendar(calendar, yearNow, get_curr_month + (n1));
        getBindedDate(yearNow, get_curr_month + (n1));
        text_month.innerHTML = month_view[get_curr_month + (n)];
        current_year.innerHTML = yearNow;
    } else {
        createCalendar(calendar, yearNow, get_curr_month + (n1));
        getBindedDate(yearNow, get_curr_month + (n1));
        text_month.innerHTML = month_view[get_curr_month + (n)];
    }
});






createCalendar(calendar, 2022, get_curr_month + 1);

let post_container = document.querySelector('.calendar_content_container');
let view_desc = document.querySelectorAll('tbody tr td');
//getBindedDate(2022, get_curr_month + 1)

//Навод на текст

let post_title = document.querySelector('.post_title');
let post_description = document.querySelector('.post_description');


async function getBindedDate(curr_year, curr_month) {
    view_desc = document.querySelectorAll('tbody tr td');
    curr_month = curr_month.toString().length < 2 ? '0' + curr_month : curr_month;
    let result = response.filter((index) => {
        if (index.deadline.substr(5, 2) == curr_month) {
            return index
        }
    });
    for (let result_elem of result) {
        let responsedDates = [];
        responsedDates.push(result_elem.deadline.substr(8, 2));
        for (let i = 0; i < view_desc.length; i++) {
            for (let j = 0; j < responsedDates.length; j++) {
                let newElem = view_desc[i].innerHTML.length < 2 ? '0' + view_desc[i].innerHTML : view_desc[i].innerHTML;
                if (newElem == responsedDates[j]) {
                    view_desc[i].style.backgroundColor = '#524fe4';
                    view_desc[i].style.cursor = 'pointer';

                    view_desc[i].addEventListener('click', function(e) {
                        post_container.innerHTML = post_container.innerHTML + `<h3 class="post_title">${result_elem.title}</h3><p class="post_description">${result_elem.subtitle}</p>`;
                    })
                }
            }

        }
    }

}