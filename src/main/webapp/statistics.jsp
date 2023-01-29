<%@page contentType = "text/html; charset=UTF-8" pageEncoding = "UTF-8" %>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="header.jsp" %>
<html>
<head>
    <title>Statistics</title>
</head>
<body style="background-color: black">
<table class="table table-bordered table-dark">
    <thead>
    <tr>
        <th>Users</th>
        <th>Purchases</th>
    </tr>
    </thead>
    <c:forEach var="stat" items="${statistics}">
        <thead>
        <tr>
            <td>${stat.key}</td>
            <td>${stat.value}</td>
        </tr>
        </thead>
    </c:forEach>
</table>
</body>
</html>
