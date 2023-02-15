<%@page contentType = "text/html; charset=UTF-8" pageEncoding = "UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="header.jsp"%>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="language"/>

<html lang="${sessionScope.language}">
<head>
    <title>View halls</title>
    <link rel="stylesheet" href="utility/css/halls.css">
    <link rel="stylesheet" href="utility/css/bootstrap5/bootstrap.min.css">
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>--%>
<%--    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>--%>
<%--    <script src="utility/js/bootstrap5/bootstrap.bundle.min.js"></script>--%>
</head>
<body style="background-color: black;">
<section style="background: black">
    <div class="container py-5">
        <div class="row">
            <c:if test="${sessionScope.user.role == 'ADMINISTRATOR'}">
                <div class="text-center">
                    <a href="" class="btn btn-outline-light btn-lg btn-block" data-bs-toggle="modal" data-bs-target="#modalHall"><fmt:message key="addHall.button.add"/></a>
                </div>
            </c:if>
            <div class="modal fade" id="modalHall" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header text-center">
                            <h4 class="modal-title w-100 font-weight-bold"><fmt:message key="addHall.button.add"/></h4>
                            <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form action="../home" method="post">
                            <div class="modal-body mx-3">
                                <input name="action" type="hidden" value="addHall"/>
                                <div class="md-form form-outline mb-5">
                                    <input type="text" name="name" id="defaultForm-email" class="form-control" placeholder="<fmt:message key="addHall.button.placeholder.name"/>"/>
                                </div>
                                <div class="md-form form-outline mb-4">
                                    <textarea class="form-control" name="description" rows="3" placeholder="<fmt:message key="addHall.button.placeholder.description"/>"></textarea>
                                </div>
                            </div>
                            <div class="modal-footer d-flex justify-content-center">
                                <button class="btn btn-outline-dark" type="submit"><fmt:message key="addHall.form.button"/></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <c:if test="${halls != null}">
                <c:set var="language" scope="page" value="${sessionScope['javax.servlet.jsp.jstl.fmt.locale.session']}"/>
                <c:forEach var="hall" items="${halls}">
                    <div class="col-md-12 col-lg-4 mb-4 mb-lg-0 mt-4">
                        <div class="card border-white bg-transparent text-white">
                            <img src="utility/img/hallPlaceholder.jpg" class="card-img-top" alt="noImgPlaceholder"/>
                            <div class="card-body">
                                <div class="text-center mt-1">
                                    <h4 class="card-title">${hall.name}</h4>
                                    <jsp:useBean id="now" class="java.util.Date"/>
                                    <h6 class="text-secondary mb-1 pb-3"><fmt:message key="addHall.opened"/> <fmt:formatDate value="${now}" pattern="dd-MM-yyyy"/></h6>
                                </div>
                                <div class="text-center">
                                    <div class="p-3 mx-n3 mb-4" style="background: grey">
                                        <h5 class="mb-0">${hall.description}</h5>
                                    </div>
                                </div>
<%--                                <c:choose>--%>
<%--                                    <c:when test="${sessionScope.user.role == 'ADMINISTRATOR'}">--%>
<%--&lt;%&ndash;                                        <div class="d-flex flex-row">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            <a class="btn btn-primary flex-fill me-1" data-mdb-ripple-color="dark" href=""><fmt:message key="addHall.button.edit"/></a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        </div>&ndash;%&gt;--%>
<%--                                    </c:when>--%>
<%--                                    <c:otherwise>--%>
<%--&lt;%&ndash;                                        <div class="d-flex flex-row">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            <a class="btn btn-primary flex-fill me-1" data-mdb-ripple-color="dark" href="home?action=viewExhibitions&sortBy=halls&page=1"><fmt:message key="addHall.button.currentExhibitions"/></a>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        </div>&ndash;%&gt;--%>
<%--                                    </c:otherwise>--%>
<%--                                </c:choose>--%>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
</section>
</body>
</html>