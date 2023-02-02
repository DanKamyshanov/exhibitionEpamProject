<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="header.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <title>User profile</title>
    <link rel="stylesheet" href="utility/css/profile.css">
    <link rel="stylesheet" href="utility/css/bootstrap5/bootstrap.css">
</head>
<body style="background-color: black">
<div class="container py-3">
    <div class="row">
        <div class="col-lg-4">
            <div class="card border-white bg-transparent text-white mb-4" style="border-radius: 25px">
                <div class="card-body text-center">
                    <img src="utility/img/profilePicDefault.jpg" alt="defaultProfilePic" class="rounded-circle img-fluid" style="width: 150px">
                    <h5 class="my-3">${sessionScope.user.login}</h5>
                    <div class="d-flex justify-content-center mb-2">
                        <a href="" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalProfile"><fmt:message key="profile.button.edit"/></a>
                    </div>
                    <div class="modal fade" id="modalProfile" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header text-center">
                                    <h4 class="modal-title w-100 font-weight-bold"><fmt:message key="profile.form.title"/></h4>
                                    <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <form action="../home" method="post">
                                    <div class="modal-body mx-3">
                                        <input name="action" type="hidden" value="updateProfile"/>
                                        <div class="md-form form-outline mb-4">
                                            <input type="text" name="first_name" id="defaultForm-email" class="form-control" placeholder="<fmt:message key="profile.form.firstName"/>"/>
                                        </div>
                                        <div class="md-form form-outline mb-4">
                                            <input type="text" name="last_name" id="lastName" class="form-control" placeholder="<fmt:message key="profile.form.lastName"/>">
                                        </div>
                                        <div class="md-form form-outline mb-4">
                                            <input type="text" name="login" id="login" class="form-control" placeholder="<fmt:message key="profile.form.login"/>">
                                        </div>
                                        <div class="md-form form-outline mb-4">
                                            <input type="password" name="password" id="password" class="form-control" placeholder="<fmt:message key="profile.form.password"/>">
                                        </div>
                                        <div class="md-form form-outline mb-4">
                                            <input type="email" name="email" id="email" class="form-control" placeholder="<fmt:message key="profile.form.email"/>">
                                        </div>
                                        <div class="md-form form-outline mb-4">
                                            <input type="text" name="phone_number" id="phoneNumber" class="form-control" placeholder="<fmt:message key="profile.form.phone"/>">
                                        </div>
                                    </div>
                                    <div class="modal-footer d-flex justify-content-center">
                                        <button class="btn btn-outline-dark" type="submit"><fmt:message key="addHall.form.button"/></button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-8">
            <div class="card border-white bg-transparent text-white mb-4" style="border-radius: 25px">
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm-3">
                            <p class="mb-0"><fmt:message key="profile.fullname"/></p>
                        </div>
                        <div class="col-sm-9">
                            <p class="text-muted mb-0">${sessionScope.user.first_name}&nbsp${sessionScope.user.last_name}</p>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-sm-3">
                            <p class="mb-0"><fmt:message key="profile.email"/></p>
                        </div>
                        <div class="col-sm-9">
                            <p class="text-muted mb-0">${sessionScope.user.email}</p>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-sm-3">
                            <p class="mb-0"><fmt:message key="profile.phone"/></p>
                        </div>
                        <div class="col-sm-9">
                            <p class="text-muted mb-0">${sessionScope.user.phone_number}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
