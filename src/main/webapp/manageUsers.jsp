<%@page contentType = "text/html; charset=UTF-8" pageEncoding = "UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="header.jsp"%>
<html>
<head>
    <title>Manage Users</title>
    <link rel="stylesheet" href="utility/css/manageUsers.css">
    <link rel="stylesheet" href="utility/css/pagination.css">
    <link rel="stylesheet" href="utility/css/bootstrap5/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script>
        $(document).ready(function(){
            $('[data-toggle="tooltip"]').tooltip();
        });
    </script>
</head>
<body style="background-color: black">
<div class="container">
    <div class="table-responsive">
        <div class="table-wrapper" style="background-color: black">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8 text-white"><h2><fmt:message key="users.title"/></h2></div>
                    <div class="col-sm-4">
<%--                        <div class="search-box">--%>
<%--                            <i class="material-icons">&#xE8B6;</i>--%>
<%--                            <input type="text" class="form-control" placeholder="Search&hellip;">--%>
<%--                        </div>--%>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-dark table-hover table-bordered">
                <thead>
                <tr>
                    <th>ID</th>
                    <th><fmt:message key="users.role"/></th>
                    <th><fmt:message key="users.login"/></th>
                    <th><fmt:message key="users.name"/></th>
                    <th><fmt:message key="users.email"/></th>
                    <th><fmt:message key="users.phone"/></th>
                    <th><fmt:message key="users.action"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.getRole()}</td>
                    <td>${user.login}</td>
                    <td>${user.first_name} ${user.last_name}</td>
                    <td>${user.email}</td>
                    <td>${user.phone_number}</td>
                    <td>
<%--                        <a href="userStatistics.jsp" class="view" title="View" data-toggle="tooltip"><i class="material-icons">&#xE417;</i></a>--%>
<%--                        <a href="#" class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>--%>
                        <form action="home" method="get" style="margin-block-end: 0; display: inline-block; margin: 0 5px">
                            <input name="action" type="hidden" value="deleteUser">
                            <input name="userToDeleteID" type="hidden" value="${user.id}">
                            <button style="color: #E34724; border: none; background-color: transparent;" type="submit"><i class="material-icons">&#xE872;</i></button>
                        </form>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="pagination mt-4">
                <div class="pagination-inner">
                    <c:forEach var="count" begin="1" end="${usersNumberOfPages}">
                        <c:choose>
                            <c:when test="${usersCurrentPage == count}">
                                <a class="active" href="home?action=viewUsers&page=${count}">${count}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="home?action=viewUsers&page=${count}">${count}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
