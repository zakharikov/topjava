<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<form method="get">
    <table border="1">

        <th>Id</th>
        <th>Дата, Время</th>
        <th>Описание</th>
        <th>Каллории</th>

        <c:forEach var="meal" items="${meals}">
            <tr style="color:${meal.excess ? 'red' : 'green'}">
                <td>${meal.id}</td>
                <td>${meal.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</td>
                <td align="center">${meal.description}</td>
                <td align="center">${meal.calories}</td>
            </tr>
        </c:forEach>
    </table>
</form>

<h2>CRUD operations</h2>

<form method="post">
    <label>Дата, Время:
        <input type="datetime-local" name="dateTime" required>
    </label>
    <label>Описание:
        <input type="text" name="description" required>
    </label>
    <label>Каллории:
        <input type="number" name="calories" required>
    </label>
    <button type="submit">Добавить</button>
</form>

<form method="post">
    <label>Id:
        <input type="number" name="idToDelete" required>
    </label>
    <button type="submit">Удалить</button>
</form>

<form method="post">
    <label>Id:
        <input type="number" name="idToUpdate" required>
    </label>
    <label>Дата, Время:
        <input type="datetime-local" name="dateTimeToUpdate" required>
    </label>
    <label>Описание:
        <input type="text" name="descriptionToUpdate" required>
    </label>
    <label>Каллории:
        <input type="number" name="caloriesToUpdate" required>
    </label>
    <button type="submit">Изменить</button>
</form>

<form method="post">
    <label>Id:
        <input type="number" name="idToRead" required>
    </label>
    <button type="submit">Получить</button>
</form>

<form method="get">
    <label>Получено:
        <c:out value="${mealToPrint}"/>
    </label>
</form>
</body>
</html>
