<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@attribute name="action" required="true" type="java.lang.String" %>

<form action="home" method="get">
    <input name="action" type="hidden" value="${action}">
    <input name="page" type="hidden" value="1">
    <input name="sortBy" type="hidden" value="theme">
    <button type="submit" class="btn btn-outline-light btn-lg"><fmt:message key="home.exhibition.sort.theme"/></button>
</form>
<form action="home" method="get">
    <input name="action" type="hidden" value="${action}">
    <input name="page" type="hidden" value="1">
    <input name="sortBy" type="hidden" value="ticket_price">
    <button type="submit" class="btn btn-outline-light btn-lg"><fmt:message key="home.exhibition.sort.price"/></button>
</form>
<h1 class="mb-3"><fmt:message key="home.sort.header.lower"/></h1>
<form action="home" method="get">
    <input name="action" type="hidden" value="${action}">
    <input name="page" type="hidden" value="1">
    <input name="sortBy" type="hidden" value="date">
    <input type="date" name="selectedDate" <c:if test="${param.selectedDate != null}"> value="${param.selectedDate}" </c:if>>
    <br/>
    <br/>
    <button type="submit" class="btn btn-outline-light btn-lg"><fmt:message key="home.exhibition.sort.date"/></button>
</form>

