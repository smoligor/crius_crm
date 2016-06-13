<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><fmt:message code="dashboard.page.title"/></title>
    <link rel="stylesheet" href="resources/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="resources/css/dashboard.css" type="text/css">
    <script src="resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="resources/js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-2"><jsp:include page="menu.jsp"/></div>
        <div class="col-md-10" align="left">
            <div class="row">
                <div class="col-md-9">
                    <div class="row">
                        <table class="col-md-6 widget widget-green">
                            <tbody align="center">
                            <tr class="row-body header">
                                <td class="col-md-6 border text"><fmt:message code="dashboard.widget.deal.title"/></td>
                            </tr>
                            <tr class="row-body">
                                <td class="border"><a class="text" href="dealList"><fmt:message
                                        code="dashboard.widget.deal.count"/>: ${requestScope.deal_count}</a></td>
                            </tr>
                            <tr class="row-body">
                                <td class="border"><a class="text" href="dealList"><fmt:message
                                        code="dashboard.widget.deal.budget"/>: ${requestScope.deal_sum / 100} ${requestScope.currency}</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <table class="col-md-6 widget widget-green">
                            <tbody align="center">
                            <tr class="row-body header">
                                <td class="col-md-6 border text"><fmt:message code="dashboard.widget.dealTask.title"/></td>
                            </tr>
                            <tr class="row-body">
                                <td class="border"><a class="text" href="dealList?deal_with_task=0"><fmt:message
                                        code="dashboard.widget.dealTask.noTask"/>: ${requestScope.deal_count - requestScope.deal_with_task}</a>
                                </td>
                            </tr>
                            <tr class="row-body">
                                <td class="border"><a class="text" href="dealList?deal_with_task=1"><fmt:message
                                        code="dashboard.widget.dealTask.withTask"/>: ${requestScope.deal_with_task}</a></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <table class="col-md-4 widget widget-red">
                            <tbody align="center">
                            <tr class="row-mini header">
                                <td class="border text"><fmt:message code="dashboard.widget.task.title"/></td>
                            </tr>
                            <tr class="row-mini">
                                <td class="border"><a class="text" href="taskList"><fmt:message
                                        code="dashboard.widget.task.work"/>: ${requestScope.task_in_work}</a></td>
                            </tr>
                            <tr class="row-mini">
                                <td class="border"><a class="text" href="taskList?done=1"><fmt:message
                                        code="dashboard.widget.task.done"/>: ${requestScope.task_done}</a></td>
                            </tr>
                            <tr class="row-mini">
                                <td class="border"><a class="text" href="taskList?paused=1"><fmt:message
                                        code="dashboard.widget.task.paused"/>: ${requestScope.task_paused}</a></td>
                            </tr>
                            </tbody>
                        </table>
                        <table class="col-md-4 widget widget-blue">
                            <tbody align="center">
                            <tr class="row-body header">
                                <td class="border text"><fmt:message code="dashboard.widget.contactCompany.title"/></td>
                            </tr>
                            <tr class="row-body">
                                <td class="border"><a class="text" href="viewcompanies#contacts"><fmt:message
                                        code="dashboard.widget.contactCompany.contact"/>: ${requestScope.contact_count}</a></td>
                            </tr>
                            <tr class="row-body">
                                <td class="border"><a class="text" href="viewcompanies#companies"><fmt:message
                                        code="dashboard.widget.contactCompany.company"/>: ${requestScope.company_count}</a></td>
                            </tr>
                            </tbody>
                        </table>
                        <table class="col-md-4 widget widget-green">
                            <tbody align="center">
                            <tr class="row-body header">
                                <td class="border text"><fmt:message code="dashboard.widget.result.title"/></td>
                            </tr>
                            <tr class="row-body">
                                <td class="border"><a class="text" href="dealList?status=5"><fmt:message
                                        code="dashboard.widget.result.success"/>: ${requestScope.deal_finished_ok}</a></td>
                            </tr>
                            <tr class="row-body">
                                <td class="border"><a class="text" href="dealList?status=6"><fmt:message
                                        code="dashboard.widget.result.fail"/>: ${requestScope.deal_finished_fail}</a></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <table class="col-md-3 border" id="logList">
                    <thead>
                    <tr id="textLogHeader">
                        <th class="border"><fmt:message code="dashboard.log.title"/></th>
                    </tr>
                    </thead>
                    <tbody class="body-scroll">
                    <c:forEach var="index" begin="0" end="${requestScope.logList.size() - 1}" step="1">
                        <fmt:message code="${requestScope.logList.get(index).logType}" var="logType" scope="page"/>
                        <tr>
                            <td class="row-log ${index % 2 == 0 ? '' : ' even'}">
                                    ${requestScope.logList.get(index).dateCreate}, ${requestScope.logList.get(index).userName}
                                <br>${pageScope.logType}: ${requestScope.logList.get(index).logInfo}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
