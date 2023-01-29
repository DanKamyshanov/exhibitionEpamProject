<%@page contentType = "text/html; charset=UTF-8" pageEncoding = "UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="header.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <title>Exhibitions</title>
    <link rel="stylesheet" href="utility/css/pagination.css">
    <link rel="stylesheet" href="utility/css/bootstrap5/bootstrap.min.css">
</head>
<body style="background-color: black">
<section style="background-color: black">
    <div class="container py-5">
        <div class="row justify-content-center mb-3">
            <c:if test="${exhibitions != null}">
                <c:set var="language" scope="page" value="${sessionScope['javax.servlet.jsp.jstl.fmt.locale.session']}"/>
                <c:set var="pagiPage" value="1"/>
                <c:forEach var="exhibition" items="${exhibitions}">
                    <div class="col-md-12 col-xl-10">
                        <div class="card border-white bg-transparent text-white mt-4">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-12 col-lg-3 col-xl-3 mb-4 mb-lg-0">
                                        <div class="bg-image hover-zoom ripple rounded ripple-surface">
                                            <img src="utility/img/ExhibPlaceholder.jpg" class="w-100" alt="exhibitionPlaceholder">
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-6 col-xl-6">
                                        <h5>${exhibition.name}</h5>
                                        <div class="d-flex flex-row">
                                            <div class="text-secondary mb-1 pb-3">${exhibition.date_from}&nbsp<fmt:message key="exhibition.to"/>&nbsp${exhibition.date_to}</div>
                                            <span>&nbsp&nbsp&nbsp${exhibition.working_from}&nbsp-&nbsp${exhibition.working_to}</span>
                                        </div>
                                        <div class="mt-1 mb-0 text-white">
                                            <span class="text-primary">${exhibition.theme}</span>
                                        </div>
                                        <p class="text-truncate mb-4 mb-md-0">${exhibition.description}</p>
                                    </div>
                                    <div class="col-md-6 col-lg-3 col-xl-3 border-sm-start-none border-start">
                                        <div class="d-flex flex-row align-items-center mb-1">
                                            <h4>₴${exhibition.ticket_price}</h4>
                                        </div>
                                        <c:forEach var="hall" items="${exhibition.getHalls()}">•&nbsp${hall.name}<br></c:forEach>
                                        <c:if test="${user.role == 'USER'}">
                                            <form action="home" method="post">
                                                <div class="d-flex flex-column mt-4">
                                                    <input name="action" type="hidden" value="purchaseTicket">
                                                    <input name="exhibition_id" type="hidden" value="${exhibition.getExhibitionId()}">
                                                    <input name="exhibition_name" type="hidden" value="${exhibition.name}">
                                                    <input name="exhibition_time_from" type="hidden" value="${exhibition.working_from}">
                                                    <input name="exhibition_time_to" type="hidden" value="${exhibition.working_to}">
                                                    <input name="end_date" type="hidden" value="${exhibition.date_to}">
                                                    <button class="btn btn-primary btn-sm" type="submit"><fmt:message key="exhibition.purchase"/></button>
                                                </div>
                                            </form>
                                            <div class="d-flex flex-column mt-4">
                                                <button class="btn btn-outline-light btn-sm mt-4 mt-2" data-bs-toggle="modal" data-bs-target="#modalDetails"><fmt:message key="exhibition.details"/></button>
                                                <div class="modal fade" id="modalDetails" tabindex="-1" role="dialog" aria-labelledby="exhibitionName" aria-hidden="true">
                                                    <div class="modal-dialog" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title text-dark" id="exhibitionName">${exhibition.name}</h5>
                                                                <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body text-dark">${exhibition.description}</div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><fmt:message key="exhibition.close"/></button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test="${user.role == 'ADMINISTRATOR'}">
                                            <form action="home" method="get">
                                                <div class="d-flex flex-column mt-4">
                                                    <input name="action" type="hidden" value="deleteExhibition">
                                                    <input name="exhibitionToDeleteId" type="hidden" value="${exhibition.getExhibitionId()}">
                                                    <button class="btn btn-primary btn-sm" type="submit"><fmt:message key="exhibition.cancel"/></button>
                                                    <a href="home?action=viewStatistics&exhibition_id=${exhibition.getExhibitionId()}" class="btn btn-outline-light btn-sm mt-2"><fmt:message key="header.statistic"/></a>
                                                </div>
                                            </form>
                                        </c:if>
                                        <c:if test="${user.role == null}">
                                            <div class="d-flex flex-column mt-4">
                                                <p class="mb-0"><a href="login.jsp" class="link-info"><fmt:message key="exhibition.signIn"/></a>&nbsp<fmt:message key="exhibiion.unloggedPurchase"/></p>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
<%--                    <c:set var="pagiPage" value="${pagiPage+1}"/>--%>
                </c:forEach>
            </c:if>
            <div class="pagination mt-4">
                <div class="pagination-inner">
                    <c:forEach var="count" begin="1" end="${numberOfPages}">
                        <c:choose>
                            <c:when test="${currentPage == count}">
                                <a class="active" href="home?action=viewExhibitions&page=${count}&sortBy=${param.sortBy}">${count}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="home?action=viewExhibitions&page=${count}&sortBy=${param.sortBy}">${count}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>

