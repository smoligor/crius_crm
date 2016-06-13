<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><fmt:message code="logout.title"/></title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css" type="text/css"/>
    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body style="padding: 25px;">
<div class="container">
    <div class="row">
        <div class="col-md-2">
            <jsp:include page="menu.jsp"/>
        </div>
        <div class="col-md-10">
            <form class="form-signin" action="logout" method="post"
                  style="max-width: 320px; margin: 0 auto; font-size: larger;">
                <h3 class="form-signin-heading" align="center">CRM "CRIUS"</h3>
                <div class="form-group">
                    <h5><fmt:message code="logout.account"/>: ${sessionScope.user.email}</h5>
                </div>
                <div class="form-group">
                    <input class="btn btn-primary col-md-5" type="button" onclick="history.back()"
                           value="<fmt:message code="logout.back"/>"/>
                    <div class="col-md-2"></div>
                    <input class="btn btn-primary col-md-5" type="submit" value="<fmt:message code="logout.logout"/>"/>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
