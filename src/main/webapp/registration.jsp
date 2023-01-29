<%@page contentType = "text/html; charset=UTF-8" pageEncoding = "UTF-8" %>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
  <title>Registration</title>
  <link rel="stylesheet" href="utility/css/bootstrap5/bootstrap.css">
  <link rel="stylesheet" href="utility/css/registration.css">
</head>
<body>
<section class="vh-100" style="background: black">
  <form action="../home" method="post">
  <div class="container h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-9 col-lg-7 col-xl-6">
        <div class="card border-white bg-transparent text-white" style="border-radius: 25px">
          <div class="card-body p-5">
            <h2 class="text-uppercase text-center mb-5"><fmt:message key="registration.form.register"/></h2>
            <input name="action" type="hidden" value="register">
            <div class="form-outline mb-4">
              <input type="text" name="first_name" class="form-control form-control-lg" placeholder="<fmt:message key="registration.form.firstName"/>" required>
            </div>
            <div class="form-outline mb-4">
              <input type="text" name="last_name" class="form-control form-control-lg" placeholder="<fmt:message key="registration.form.lastName"/>" required>
            </div>
            <div class="form-outline mb-4">
              <input type="text" name="login" class="form-control form-control-lg" placeholder="<fmt:message key="registration.form.login"/>" required>
            </div>
            <div class="form-outline mb-4">
              <input type="email" name="email" class="form-control form-control-lg" placeholder="<fmt:message key="registration.form.email"/>" required>
            </div>
            <div class="form-outline mb-4">
              <input type="text" name="phone_number" class="form-control form-control-lg" placeholder="<fmt:message key="registration.form.phone"/>" required>
            </div>
            <div class="form-outline mb-4">
              <input type="password" name="password" class="form-control form-control-lg" placeholder="<fmt:message key="registration.form.password"/>" required>
            </div>
            <div class="form-outline mb-4">
              <input type="password" name="reEnterPassword" class="form-control form-control-lg" placeholder="<fmt:message key="registration.form.passwordRepeat"/>" required>
            </div>
            <div class="d-flex justify-content-center">
              <button class="btn btn-outline-light btn-lg px-5" type="submit"><fmt:message key="registration.form.button"/></button>
            </div>
            <p class="text-center mt-5 mb-0"><fmt:message key="registration.form.loginCaption"/> <a href="login.jsp" class="link-info"><u><fmt:message key="registration.form.loginButton"/></u></a></p>
          </div>
        </div>
      </div>
    </div>
  </div>
  </form>
</section>
<c:if test="${sessionScope.error = 'passwordMismatch'}">
  <script>
    alert("passwords do not match")
  </script>
  ${sessionScope.error == null}
</c:if>
</body>
</html>



