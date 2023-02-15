<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="header.jsp"%>


<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="utility/css/bootstrap5/bootstrap.css">
    <link rel="stylesheet" href="utility/css/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src='https://www.google.com/recaptcha/api.js?hl=${sessionScope.language eq 'ua' ? 'ru' : 'en'}'></script>
<%--    <script src="https://www.google.com/recaptcha/enterprise.js?render=6LccqYAkAAAAABuunFb10vdZygcBrKymbOM9zXOZ"></script>--%>
    <script>
        window.onload = function (){
            var $recaptcha = document.querySelector('#g-recaptcha-response');
            if($recaptcha){
                $recaptcha.setAttribute("required", "required");
            }
        }
    </script>
</head>
<body>
<section class="vh-100" style="background: black">
    <form action="../home" method="get">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card border-white bg-transparent text-white" style="border-radius: 25px;">
                    <div class="card-body p-5 text-center">
                        <div class="mb-md-5 mt-md-4 pb-5">
                            <h2 class="fw-bold mb-2 text-uppercase"><fmt:message key="login.title"/></h2>
                            <p class="text-white-50 mb-5"><fmt:message key="login.caption"/></p>
                            <input name="action" type="hidden" value="login">
                            <div class="form-outline form-white mb-4">
                                <input type="text" name="login" class="form-control form-control-lg" placeholder="<fmt:message key="login.form.placeholder.login"/>" required/>
                            </div>
                            <div class="form-outline form-white mb-4">
                                <input type="password" name="password" class="form-control form-control-lg" placeholder="<fmt:message key="login.form.placeholder.password"/>" required/>
                            </div>
                            <button class="btn btn-outline-light btn-lg px-5" type="submit"><fmt:message key="login.form.button"/></button>
                            <div class="d-flex justify-content-center text-center mt-4 pt-1">
                                <div class="g-recaptcha" data-sitekey="6LfKrIAkAAAAAJdpYcMuK0Lc16WoJK_1di15543q"></div>
<%--                                <a href="" class="text-white"><i class="fa fa-facebook-f fa-lg"></i></a>--%>
<%--                                <a href="" class="text-white"><i class="fa fa-twitter fa-lg mx-4 px-2"></i></a>--%>
<%--                                <a href="" class="text-white"><i class="fa fa-google fa-lg"></i></a>--%>
                            </div>
                        </div>
                        <div>
                            <p class="mb-0"><fmt:message key="login.forward.registration.caption"/><a href="registration.jsp" class="link-info"> <fmt:message key="login.forward.registration.hl"/></a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </form>
</section>
<c:if test="${sessionScope.error == 'loginLogin'}">
    <script>
        alert("Invalid login")
    </script>
</c:if>
<c:if test="${sessionScope.error == 'passwordLogin'}">
    <script>
        alert("Invalid password")
    </script>
</c:if>
<c:if test="${sessionScope.error == 'loginError'}">
    <script>
        alert("Invalid Credentials")
    </script>
</c:if>
</body>
</html>