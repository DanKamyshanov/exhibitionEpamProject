<%@page contentType = "text/html; charset=UTF-8" pageEncoding = "UTF-8" %>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@taglib prefix="my2" tagdir="WEB-INF/tag/exhibitionSorting.tag" %>--%>
<%@include file="headerIndex.jsp"%>

<c:choose>
    <c:when test="${empty language}">
        <c:set var="language" scope="session" value="${pageContext.request.locale.language}"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${language}" scope="session"/>
    </c:otherwise>
</c:choose>

<fmt:setBundle basename="language"/>

<!DOCTYPE html>
<html>
<head>
    <title>Exhibitions</title>
    <link rel="stylesheet" href="utility/css/bootstrap5/bootstrap.css">
    <link rel="stylesheet" href="utility/css/index.css">
</head>
<body style="background-color: black">
<div class="p-5 text-center">
    <div class="d-flex justify-content-center h-100">
        <div class="text-white">
            <h1 class="mb-3"><fmt:message key="home.sort.header"/></h1>
            <form action="home" method="get">
                <input name="action" type="hidden" value="viewExhibitions">
                <input name="page" type="hidden" value="1">
                <input name="sortBy" type="hidden" value="theme">
                <button type="submit" class="btn btn-outline-light btn-lg"><fmt:message key="home.exhibition.sort.theme"/></button>
            </form>
            <form action="home" method="get">
                <input name="action" type="hidden" value="viewExhibitions">
                <input name="page" type="hidden" value="1">
                <input name="sortBy" type="hidden" value="ticket_price">
                <button type="submit" class="btn btn-outline-light btn-lg"><fmt:message key="home.exhibition.sort.price"/></button>
            </form>
            <h1 class="mb-3"><fmt:message key="home.sort.header.lower"/></h1>
            <form action="home" method="get">
                <input name="action" type="hidden" value="viewExhibitions">
                <input name="page" type="hidden" value="1">
                <input name="sortBy" type="hidden" value="date">
                <input type="date" name="selectedDate" <c:if test="${param.selectedDate != null}"> value="${param.selectedDate}" </c:if>>
                <br/>
                <br/>
                <button type="submit" class="btn btn-outline-light btn-lg"><fmt:message key="home.exhibition.sort.date"/></button>
            </form>
        </div>
    </div>
</div>
</body>
</html>