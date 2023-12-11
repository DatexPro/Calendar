<%@ page import="java.util.Date" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%--
  Created by IntelliJ IDEA.
  User: rusla
  Date: 20.11.2023
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calendar</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }
        .note-container {
            display: block;
        }
    </style>
</head>
<body>
<h2>Calendar</h2>

<table id="calendarTable"></table>
<div class="note-container"></div>

<script>
    function generateCalendar() {
        const now = new Date();
        const currentMonth = now.getMonth();
        const currentYear = now.getFullYear();

        const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate();
        const firstDayOfMonth = new Date(currentYear, currentMonth, 1).getDay();
        const weeksInMonth = Math.ceil((firstDayOfMonth + daysInMonth) / 7);
        const table = document.getElementById('calendarTable');
        const headerRow = document.createElement('tr');

        const dayNames = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];

        dayNames.forEach(day => {
            const th = document.createElement('th');
            th.textContent = day;
            headerRow.appendChild(th);
        });

        table.appendChild(headerRow);

        let dayCounter = 1;

        for (let i = 0; i < weeksInMonth; i++) {
            const row = document.createElement('tr');

            for (let j = 0; j < 7; j++) {
                const td = document.createElement('td');
                const date = dayCounter;

                if (i === 0 && j < firstDayOfMonth) {
                    // Пустые ячейки до первого числа месяца
                    td.textContent = '';
                } else if (dayCounter <= daysInMonth) {
                    // Заполнение дней месяца
                    td.textContent = dayCounter;
                    td.onclick = function () {
                        showNotes(date);
                    };
                    dayCounter++;
                }

                row.appendChild(td);
            }

            table.appendChild(row);
        }
    }

    function showNotes(date) {
        console.log('Showing notes for date:', date);
        const noteContainer = document.querySelector('.note-container');
        noteContainer.innerHTML = '';

        const header = document.createElement('h3');
        header.textContent = 'Notes for ' + date + '.' + (new Date().getMonth() + 1) + '.' + (new Date().getYear() + 1900);
        noteContainer.appendChild(header);

        const noteInput = document.createElement('input');
        noteInput.classList.add('note-input');
        noteContainer.appendChild(noteInput);

        const addButton = document.createElement('button');
        addButton.textContent = 'Add Note';
        addButton.addEventListener('click', () => addNoteToCalendar(date, noteInput.value));
        noteContainer.appendChild(addButton);
    }

    function addNoteToCalendar(date, context) {
        axios.post('${contextPath}/calendar', { day: date, context: context })
            .then(response => {
                console.log('Note added successfully:', response.data);
                // Дополнительные действия после успешного добавления
            })
            .catch(error => {
                console.error('Error adding note:', error);
                // Обработка ошибок
            });
    }

    // Вызов функции для генерации календаря
    generateCalendar();
</script>


<%--<div class="container">--%>
<%--    <form action="${contextPath}/calendar" method="post">--%>
<%--        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
<%--        <label htmlFor="context"> Context</label>--%>
<%--        <input className="form-control" type="text" id="context" name="context"/>--%>
<%--        <label htmlFor="day"> Day</label>--%>
<%--        <input className="form-control" type="date" id="day" name="day"/>--%>
<%--        <button className="btn btn-lg btn-primary btn-block" type="submit">Save note</button>--%>
<%--    </form>--%>
<%--</div>--%>

</body>
</html>
