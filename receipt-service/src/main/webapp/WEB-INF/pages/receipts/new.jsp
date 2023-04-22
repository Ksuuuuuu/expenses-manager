<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Кисюк
  Date: 09.04.2023
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div align="center">
  <form:form modelAttribute="receipt" method="post" action="${pageContext.request.contextPath}/receipts/new">
    Дата и время:<input style="margin-top: 20px" type="text" name="dateTime"><br>
    Сумма:<input style="margin-top: 20px" type="text" name="amount"><br>
    Путь файла:<input style="margin-top: 20px" type="text" name="filePath"><br>
    Юзер:<input style="margin-top: 20px" type="text" name="idUser"><br>
    <button style="margin-top: 20px" type="submit">Добавить</button>
  </form:form>
</div>
</body>
</html>
