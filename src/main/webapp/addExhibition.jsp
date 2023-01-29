<%@page contentType = "text/html; charset=UTF-8" pageEncoding = "UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="header.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <title>Add exhibition</title>
    <link rel="stylesheet" href="utility/css/bootstrap5/bootstrap.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="utility/css/addExhibition.css">
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>
    <script src="utility/js/bootstrap5/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.js"></script>
    <script>
        $(document).ready(function(){
            var multipleCancelButton = new Choices('#hallSelect', {
                removeItemButton: true,
                maxItemCount:5,
                searchResultLimit:5,
                renderChoiceLimit:5
            });
        });
    </script>
</head>

<body>
<section class="vh-100 addExhibition_bg">
    <div class="container py-5 h-100">
        <div class="row justify-content-center align-items-center h-100">
            <div class="col-12 col-lg-9 col-xl-7">
                <div class="card border-white bg-transparent text-white" style="border-radius: 25px">
                    <div class="card-body p-4 p-md-5">
                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5"><fmt:message key="addExhibition.title"/></h3>
                        <form action="../home" method="post">
                            <input name="action" type="hidden" value="addExhibition">
                            <div class="row">
                                <div class="col-md-6 mb-4">
                                    <div class="form-outline">
                                        <input type="text" id="Name" name="name" class="form-control form-control-lg" placeholder="<fmt:message key="addExhibition.form.name"/>"/>
                                    </div>
                                </div>
                                <div class="col-md-6 mb-4">
                                    <div class="form-outline">
                                        <input type="text" id="Theme" name="theme" class="form-control form-control-lg" placeholder="<fmt:message key="addExhibition.form.theme"/>"/>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-4">
                                    <div class="form-outline">
                                        <input class="form-control form-control-lg" type="date" id="dateFrom" name="date_from" <c:if test="${param.dateFrom != null}"> value="${param.dateFrom}"</c:if>/>
                                        <label class="form-label" for="dateFrom"><fmt:message key="addExhibition.form.dateFrom"/></label>
                                    </div>
                                </div>
                                <div class="col-md-6 mb-4">
                                    <div class="form-outline">
                                        <input class="form-control form-control-lg" type="date" id="dateTo" name="date_to" <c:if test="${param.dateTo != null}"> value="${param.dateTo}"</c:if>/>
                                        <label class="form-label" for="dateTo"><fmt:message key="addExhibition.form.dateTo"/></label>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-4">
                                    <div class="form-outline">
                                        <input class="form-control form-control-lg" type="time" id="openingTime" name="working_from" min="00:00" max="23:59" required>
                                        <label class="form-label" for="openingTime"><fmt:message key="addExhibition.form.workingFrom"/></label>
                                    </div>
                                </div>
                                <div class="col-md-6 mb-4">
                                    <div class="form-outline">
                                        <input class="form-control form-control-lg" type="time" id="closingTime" name="working_to" min="00:00" max="23:59" required>
                                        <label class="form-label" for="closingTime"><fmt:message key="addExhibition.form.workingTo"/></label>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-4 text-dark">
                                    <select id="hallSelect" name="occupiedHalls" placeholder="<fmt:message key="addExhibition.form.halls"/>" multiple>
                                        <c:forEach items="${halls}" var="hall">
                                            <option value="${hall.id}">${hall.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-6 mb-4">
                                    <div class="input-group input-group-lg">
                                        <span class="input-group-text" id="currency">₴</span>
                                        <input type="number" name="ticket_price" step="0.01" min="0.01" class="form-control" placeholder="<fmt:message key="addExhibition.form.ticketPrice"/>" aria-label="Ticket Price" aria-describedby="currency">
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-12">
                                    <div class="form-outline">
                                        <textarea class="form-control" name="description" rows="3" placeholder="<fmt:message key="addExhibition.form.inputDescription"/>"></textarea>
                                    </div>
                                </div>
                            </div>

                            <div class="mt-4 pt-2">
                                <button class="btn btn-primary btn-lg" type="submit"><fmt:message key="addExhibition.form.create"/></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>
<c:if test="${sessionScope.error == 'ExhibitionAlreadyExists'}">
    <script>
        alert("Exhibition already exists")
    </script>
</c:if>
<c:if test="${sessionScope.error == 'invalidPrice'}">
    <script>
        alert("Price cannot be negative")
    </script>
</c:if>
<c:if test="${sessionScope.error ==  'invalidDatePeriod'}">
    <script>
        alert("Invalid date period")
    </script>
</c:if>
<c:if test="${sessionScope.error == 'invalidTimePeriod'}">
    <script>
        alert("Invalid time period")
    </script>
</c:if>
<c:if test="${sessionScope.error == 'hallError'}">
    <script>
        alert("Invalid hall input")
    </script>
</c:if>
</body>
</html>
