<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление записи</title>
</head>
<body>
<form method="post">
    <label>Дата, Время:
        <input type="datetime-local" name="dateTime"><br/>
    </label>

    <label>Описание:
        <input type="text" name="description"><br/>
    </label>

    <label>Каллории:
        <input type="number" name="calories"><br/>
    </label>
    <button type="submit">Сохранить</button>

    <div>
        <a href="meals">К графику питания</a>
    </div>
</form>
</body>
</html>
