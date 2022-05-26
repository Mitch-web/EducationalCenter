<!DOCTYPE HTML>
<html>

<head>
    <style>
        table {
            border-collapse: collapse;
        }
        
        td,
        th {
            border: 1px solid black;
            padding: 3px;
            text-align: center;
        }
        
        th {
            font-weight: bold;
            background-color: #E6E6E6;
        }
    </style>
</head>

<body>

    <h3 class="month"></h3>
    <div id="calendar"></div>

    <script>
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
                    table += '<td style="background-color:red; cursor: pointer;" title="Митяй лох обоссаный">' + d.getDate() + '</td>';
                } else if (getDay(d) != 5 || getDay(d) != 6) {
                    table += '<td>' + d.getDate() + '</td>';
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




        createCalendar(calendar, 2022, get_curr_month + 1);
    </script>

</body>

</html>