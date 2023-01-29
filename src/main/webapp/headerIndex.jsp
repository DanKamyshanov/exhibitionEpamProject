<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="my" uri="WEB-INF/tag/myTag.tld" %>
<%@include file="header.jsp"%>
<html>
<head>
    <title>Lower Header</title>
    <link rel="stylesheet" href="utility/css/bootstrap5/bootstrap.min.css">
</head>
<body>
<div class="p-5 text-center bg-image" style="background-image: url(utility/img/Art.jpg); height: 400px">
  <div class="mask" style="background-color: rgba(0, 0, 0, 0.7)">
    <div class="d-flex justify-content-center align-items-center h-100">
      <div class="text-white">
        <h1 class="mb-3"><my:Landing input="An Amazing World of Art"/></h1>
        <h4 class="mb-3"><my:Landing input="One Click Away"/></h4>
        <form action="home" method="get">
          <input name="action" type="hidden" value="viewExhibitions">
          <input name="page" type="hidden" value="1">
          <input name="sortBy" type="hidden" value="default">
          <button type="submit" class="btn btn-outline-light btn-lg"><fmt:message key="home.greeting.button"/></button>
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>
