<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>CRM</title>
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/resources/css/menu.css">
</head>
<body>
<div id="wrapper">
    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand dropdown">
                <img src="/resources/image/crm_icon.png" width="40" />
                <a href="#" class="navbar-brand dropdown-toggle" data-toggle="dropdown">
                    CRM CRIUS
                </a>
                <ul class="dropdown-menu dropdown-menu-right submenu">
                    <li class="submenu"><img src="/resources/image/user.png" width="20"/>
                        <c:out value="${user.getName()}"/>
                    </li>
                    <li class="submenu"><a href="/profile"><fmt:message code="menu.profile"/></a></li>
                    <li class="divider"></li>
                    <li class="submenu"><a href="/logout"><fmt:message code="menu.logout"/></a></li>
                </ul>
            </li>
            <li>
                <a href="/board"><fmt:message code="menu.board"/></a>
            </li>
            <li>
                <a href="/dealList"><fmt:message code="menu.deals"/></a>
            </li>
            <li>
                <a href="/viewcompanies"><fmt:message code="menu.contacts"/></a>
            </li>
            <li>
                <a href="/viewtask"><fmt:message code="menu.tasks"/></a>
            </li>
            <li>
                <a href="/analytics"><fmt:message code="menu.analytics"/></a>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <fmt:message code="menu.settings"/>
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu submenu">
                    <li class="submenu"><a href="#"><fmt:message code="menu.settings.general"/></a></li>
                    <li class="submenu"><a href="#"><fmt:message code="menu.settings.sales.stages"/></a></li>
                    <li class="submenu"><a href="#"><fmt:message code="menu.settings.users.rights"/></a></li>
                    <li class="submenu"><a href="#"><fmt:message code="menu.settings.editors.fields"/></a></li>
                    <li class="submenu"><a href="#"><fmt:message code="menu.settings.business.processes"/></a></li>
                    <li class="submenu"><a href="#"><fmt:message code="menu.settings.visit.history"/></a></li>
                    <li class="submenu"><a href="#"><fmt:message code="menu.settings.security"/></a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
</body>
</html>