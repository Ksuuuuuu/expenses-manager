
<%--
  Created by IntelliJ IDEA.
  User: Кисюк
  Date: 07.04.2023
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Все расходы</title>
</head>
<body>
<div align="center">
    <h2>Менеджер расходов</h2>
        <table>
        <c:forEach var="receipt" items="${receipts}">
            <tr>
                <td>${receipt.id}</td>
                <td>${receipt.dateTime}</td>
                <td>${receipt.amount}</td>
                <td>${receipt.filePath}</td>
                <td>${receipt.idUser}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/receipts/delete/id=${receipt.id}">Удалить</a>
                </td>
            </tr>
        </c:forEach>
        </table>
    <form method="get" action="${pageContext.request.contextPath}/receipts/new">
    <button style="margin-top: 20px" type="submit">Добавить</button>
    </form>
</div>

</body>
</html>
