<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message code="registration.title"/></title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js" type="text/javascript"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        function checkPassword() {
            var passEqual = document.getElementById("inputPassword1").value === document.getElementById("inputPassword2").value;
            document.getElementById("btnSubmit").disabled = !passEqual;
            document.getElementById("messagePassword").hidden = passEqual;
        }
    </script>
</head>
<body style="padding: 25px;">
<div class="container">
    <form class="form-signin" action="register" method="post"
          style="max-width: 320px; margin: 0 auto; font-size: larger;">
        <h3 class="form-signin-heading" align="center">CRM "CRIUS"</h3>
        <h4 class="form-signin-heading" align="center"><fmt:message code="registration.header"/></h4>
        <div class="form-group">
            <label for="inputEmail" class="sr-only">Email</label>
            <input type="email" id="inputEmail" class="form-control" placeholder="<fmt:message code="registration.email.address"/>" name="email"
                   required autofocus style="z-index: 2;">
        </div>
        <div class="form-group">
            <label for="inputPassword1" class="sr-only"><fmt:message code="registration.password"/></label>
            <input type="password" id="inputPassword1" class="form-control" placeholder="<fmt:message code="registration.password"/>" name="password"
                   required onchange="checkPassword()">
            <label for="inputPassword2" class="sr-only"><fmt:message code="registration.password.verification"/></label>
            <input type="password" id="inputPassword2" class="form-control" placeholder="<fmt:message code="registration.password.verification"/>"
                   name="password2" required onchange="checkPassword()">
            <h5 id="messagePassword" style="color: red" align="center" hidden><fmt:message code="registration.password.match"/></h5>
        </div>
        <div class="form-group">
            <label for="inputName" class="sr-only"><fmt:message code="registration.name"/></label>
            <input type="text" id="inputName" class="form-control" placeholder="<fmt:message code="registration.name"/>" name="name"
                   required maxlength="100">
        </div>
        <div class="form-group">
            <label for="inputLanguage" class="sr-only"><fmt:message code="registration.language"/></label>
            <select class="form-control" id="inputLanguage" name="language" required>
                <c:forEach var="language" items="${requestScope.languageList}">
                    <option value="<c:out value="${language.id}"/>"><c:out value="${language.name}"/></option>
                </c:forEach>
            </select>
            <c:forEach var="entry" items="${requestScope.serviceMessageMap.entrySet()}">
                <h5 style="color: red" align="center"><fmt:message code="${entry.getKey()}"/>: ${entry.getValue()}</h5>
            </c:forEach>
        </div>
        <button class="btn btn-primary btn-block" id="btnSubmit" type="submit" disabled><fmt:message code="registration.account"/></button>
        <br><a class="hyperlink" href="login"><fmt:message code="registration.login.page"/></a>
    </form>
</div>
</body>
</html>
