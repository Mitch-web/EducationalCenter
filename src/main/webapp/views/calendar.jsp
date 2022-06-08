<%@ page pageEncoding="UTF-8" %>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Secular+One&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/assets/style.css">
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
    <%@ include file="jspf/header.jspf" %>
    <main>
        <%@ include file="jspf/course_list.jspf" %>
        <div class="content_side">
            <div class="calendar_container">
                <h3 class="current_year"></h3>
                <div class="month_container">
                    <span class="arr_prev">></span>
                    <h3 class="month"></h3>
                    <span class="arr_next">></span>
                </div>
                <div id="calendar"></div>
            </div>
            <div class="calendar_content_container">
                <h3 class="post_title"></h3>
                <p class="post_description"></p>
                <p class="post_deadline"></p>
            </div>
        </div>
    </main>
    <style>
        .month_container {
            width: 177px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        .month_container span {
            cursor: pointer;
        }

        .month_container span:last-child {
            margin-top: 2px;
            transform: rotate(180deg);
        }

        .current_year {
            width: 177px;
            text-align: center;
        }

        .content_side {
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        .calendar_content_container {
            width: 50%;
            text-align: center;
        }
        .post_title:not(:first-child) {
            margin-top: 50px;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="/assets/settings.js"></script>
    <script src="/assets/script.js"></script>
    <script src="/assets/calendar.js"></script>
</body>

</html>