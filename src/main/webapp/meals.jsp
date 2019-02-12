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

        <th>Дата, Время</th>
        <th>Описание</th>
        <th>Каллории</th>

        <c:forEach var="meal" items="${meals}">
            <c:if test="${meal.excess=='true'}">
                <tr style="color: red">
                    <td>${meal.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</td>
                    <td align="center">${meal.description}</td>
                    <td align="center">${meal.calories}</td>
                </tr>
            </c:if>
            <c:if test="${meal.excess=='false'}">
                <tr style="color: green">
                    <td>${meal.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</td>
                    <td align="center">${meal.description}</td>
                    <td align="center">${meal.calories}</td>
                </tr>
            </c:if>
        </c:forEach>
    </table>

    <table>
        <tr>
            <a href="add">Добавить</a>
        </tr>
    </table>
</form>
</body>
</html>
