<%--
  Created by IntelliJ IDEA.
  User: Кисюк
  Date: 07.04.2023
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Расходы ${date}</title>
</head>
<body>
<div align="center">
    <h2>Расходы ${date}</h2>
    <table>
        <c:forEach var="receipt" items="${receiptsByDate}">
            <tr>
                <td>${receipt.id}</td>
                <td>${receipt.dateTime}</td>
                <td>${receipt.amount}</td>
                <td>${receipt.filePath}</td>
                <td>${receipt.idUser}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
