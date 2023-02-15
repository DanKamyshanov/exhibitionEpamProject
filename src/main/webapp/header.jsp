<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:choose>
    <c:when test="${empty language}">
        <c:set var="language" scope="session" value="${pageContext.request.locale.language}"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${language}" scope="session"/>
    </c:otherwise>
</c:choose>

<fmt:setBundle basename="language"/>

<html>
<head>
    <title>Exhibitions</title>
    <link rel="stylesheet" href="utility/css/header.css">
    <link rel="stylesheet" href="utility/css/bootstrap5/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>
    <script src="utility/js/bootstrap5/bootstrap.bundle.min.js"></script>
    <script src="utility/js/languageChange.js"></script>
</head>
<body>
<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <svg xmlns="http://www.w3.org/2000/svg" width="40" height="32" fill="currentColor" class="bi bi-easel me-2" viewBox="0 0 16 16">
                    <path d="M8 0a.5.5 0 0 1 .473.337L9.046 2H14a1 1 0 0 1 1 1v7a1 1 0 0 1-1 1h-1.85l1.323 3.837a.5.5 0 1 1-.946.326L11.092 11H8.5v3a.5.5 0 0 1-1 0v-3H4.908l-1.435 4.163a.5.5 0 1 1-.946-.326L3.85 11H2a1 1 0 0 1-1-1V3a1 1 0 0 1 1-1h4.954L7.527.337A.5.5 0 0 1 8 0zM2 3v7h12V3H2z"></path>
                </svg>
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <c:choose>
                    <c:when test="${sessionScope.user.role == 'ADMINISTRATOR'}">
                        <li><a href="index.jsp" class="nav-link px-2 text-white"><fmt:message key="header.home"/></a></li>
                        <li><a href="halls.jsp" class="nav-link px-2 text-white"><fmt:message key="header.addHall"/></a></li>
                        <li><a href="addExhibition.jsp" class="nav-link px-2 text-white"><fmt:message key="header.addExhibition"/></a></li>
                        <li><a href="home?action=viewUsers&page=1" class="nav-link px-2 text-white"><fmt:message key="users.title"/></a></li>
<%--                        <li><a href="#" class="nav-link px-2 text-white"><fmt:message key="header.statistic"/></a></li>--%>
                    </c:when>
                    <c:otherwise>
                        <li><a href="index.jsp" class="nav-link px-2 text-white"><fmt:message key="header.home"/></a></li>
                        <li><a href="halls.jsp" class="nav-link px-2 text-white"><fmt:message key="header.halls"/></a></li>
                        <li><a href="home?action=viewExhibitions&sortBy=default&page=1" class="nav-link px-2 text-white"><fmt:message key="header.exhibitions"/></a></li>
                    </c:otherwise>
                </c:choose>
            </ul>

            <div class="dropdown">
                <c:choose>
                    <c:when test="${sessionScope['javax.servlet.jsp.jstl.fmt.locale.session'] eq 'ua'}">
                        <button class="btn btn-outline-light dropdown-toggle me-2" type="button" id="dropdownLang" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false" onchange="changeLanguage()">UA</button>
                        <div class="dropdown-menu" aria-labelledby="dropdownLang">
                            <a class="dropdown-item" href="home?action=changeLanguage&language=en">EN</a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-outline-light dropdown-toggle me-2" type="button" id="dropdownLang" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false"onchange="changeLanguage()">EN</button>
                        <div class="dropdown-menu" aria-labelledby="dropdownLang">
                            <a class="dropdown-item" href="home?action=changeLanguage&language=ua">UA</a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

            <c:choose>
                <c:when test="${sessionScope.user == null}">
                    <div class="text-end">
                        <a class="btn btn-outline-light me-2" href="login.jsp" role="button"><fmt:message key="header.button.logIn"/></a>
                        <a class="btn btn-warning" href="registration.jsp" role="button"><fmt:message key="header.button.signUp"/></a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="text-end">
                        <div class="btn-group">
                            <button type="button" class="btn btn-warning"><fmt:message key="header.loggedIn"/> ${sessionScope.user.login}!</button>
                            <button type="button" class="btn btn-warning dropdown-toggle dropdown-toggle-split" id="dropdownMenuReference" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false" data-reference="parent">
                                <span class="sr-only">Toggle Dropdown</span>
                            </button>
                            <div class="dropdown-menu" aria-labelledby="dropdownMenuReference">
                                <a class="dropdown-item" href="profile.jsp"><fmt:message key="header.profile.view"/></a>
                                <form action="../home" method="get">
                                    <input name="action" type="hidden" value="logout">
                                    <button class="dropdown-item" type="submit"><fmt:message key="header.button.logOut"/></button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</header>
</body>
</html>