<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><fmt:message code="view.list.title"/></title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/contactCompanyView.css" type="text/css">
    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/resources/js/contactCompanyView.js" type="text/javascript"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2"><jsp:include page="menu.jsp"/></div>
        <div class="col-md-10" style="padding-left: 30px">
            <ul class="nav nav-tabs" id="listSelectionTab">
                <li class="active"><a data-toggle="tab" href="#allEntity" id="allEntityTab"><fmt:message
                        code="view.list.all"/></a></li>
                <li><a data-toggle="tab" href="#contacts" id="contactTab"><fmt:message code="view.list.contacts"/></a>
                </li>
                <li><a data-toggle="tab" href="#companies" id="companyTab"><fmt:message code="view.list.companies"/></a>
                </li>
            </ul>
            <div class="tab-content">
                <div id="allEntity" class="tab-pane fade in active">
                    <form class="form-horizontal col-md-4" id="formAllEntity">
                        <fieldset>
                            <legend>... фильтры общие</legend>
                        </fieldset>
                    </form>
                    <div class="col-md-8" align="center">
                        <div class="row">
                            <div class="col-md-8" align="left">
                                <h3><fmt:message code="view.list.all.title"/></h3>
                            </div>
                        </div>
                        <div class="row">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th><fmt:message code="view.list.all.name"/></th>
                                    <th><fmt:message code="view.list.all.company"/></th>
                                    <th><fmt:message code="view.list.all.phone"/></th>
                                    <th><fmt:message code="view.list.all.email"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope.contactList}" var="contact">
                                    <tr>
                                        <td><c:out value="${contact.name}"/></td>
                                        <td><c:out value="${contact.company.name}"/></td>
                                        <td><c:out value="${contact.phone}"/></td>
                                        <td><c:out value="${contact.email}"/></td>
                                    </tr>
                                </c:forEach>
                                <c:forEach items="${requestScope.companyList}" var="company">
                                    <tr>
                                        <td><c:out value="${company.name}"/></td>
                                        <td></td>
                                        <td><c:out value="${company.phone}"/></td>
                                        <td><c:out value="${company.email}"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div id="contacts" class="tab-pane fade">
                    <form class="form-horizontal col-md-4" id="formContact">
                        <div class="form-group">
                            <div class="radio">
                                <label class="radio"><input type="radio" name="contactStd"
                                                            value="0" checked><fmt:message
                                        code="view.list.contact.full.list.contacts"/></label>
                            </div>
                            <div class="radio">
                                <label class="radio"><input type="radio" name="contactStd"
                                                            value="1"><fmt:message
                                        code="view.list.contact.without.tasks"/></label>
                            </div>
                            <div class="radio">
                                <label class="radio"><input type="radio" name="contactStd"
                                                            value="2"><fmt:message
                                        code="view.list.contact.with.overdue.tasks"/></label>
                            </div>
                            <div class="radio">
                                <label class="radio"><input type="radio" name="contactStd"
                                                            value="3"><fmt:message
                                        code="view.list.contact.deleted"/></label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-4" for="contactWhen"><fmt:message
                                    code="view.list.contact.when"/>:</label>
                            <div class="col-md-8">
                                <select class="form-control" id="contactWhen" onchange="contactWhenChange()">
                                    <option value="0"><fmt:message code="view.list.contact.when.all.time"/></option>
                                    <option value="1"><fmt:message code="view.list.contact.when.today"/></option>
                                    <option value="2"><fmt:message code="view.list.contact.when.3.days"/></option>
                                    <option value="3"><fmt:message code="view.list.contact.when.during.week"/></option>
                                    <option value="4"><fmt:message code="view.list.contact.when.per.month"/></option>
                                    <option value="5"><fmt:message code="view.list.contact.when.for.quarter"/></option>
                                    <option value="6"><fmt:message code="view.list.contact.when.during.period"/></option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group" id="groupContactCalendar" hidden>
                            <label class="control-label col-md-1" for="contactCalendarFrom"
                                   style="padding-right: 6px">c</label>
                            <div class="col-md-5" style="padding-left: 6px">
                                <input type="date" class="form-control" id="contactCalendarFrom"
                                       name="contactCalendarFrom"
                                       title="Выберите начало периода" style="text-align: center">
                            </div>
                            <label class="control-label col-md-1" for="contactCalendarTo"
                                   style="padding-right: 6px">по</label>
                            <div class="col-md-5" style="padding-left: 6px">
                                <input type="date" class="form-control" id="contactCalendarTo" name="contactCalendarTo"
                                       title="Выберите конец периода" style="text-align: center">
                            </div>
                        </div>
                        <div class="form-group" align="center">
                            <div class="radio-inline">
                                <label class="btn btn-primary active" id="contactWhenCreateBtn">
                                    <input type="radio" class="invisible" name="contactWhenMode" value="0"
                                           onchange="contactWhenModeClick(false)" checked><fmt:message
                                        code="view.list.contact.created"/></label>
                            </div>
                            <div class="radio-inline">
                                <label class="btn btn-default" id="contactWhenChangeBtn">
                                    <input type="radio" class="invisible" name="contactWhenMode" value="1"
                                           onchange="contactWhenModeClick(true)"><fmt:message
                                        code="view.list.contact.changed"/></label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-4" for="contactStageSelect"><fmt:message
                                    code="view.list.contact.stages"/>:</label>
                            <div class="checkbox col-md-8" id="contactStageSelect">
                                <label class="checkbox"><input type="checkbox" class="checkbox" name="contactStages"
                                                               value="0"><fmt:message
                                        code="view.list.contact.stages.without.deals"/></label>
                                <label class="checkbox"><input type="checkbox" class="checkbox" name="contactStages"
                                                               value="1"><fmt:message
                                        code="view.list.contact.stages.without.open.deals"/></label>
                                <label class="checkbox"><input type="checkbox" class="checkbox" name="contactStages"
                                                               value="2"><fmt:message
                                        code="view.list.contact.stages.primary.contact"/></label>
                                <label class="checkbox"><input type="checkbox" class="checkbox" name="contactStages"
                                                               value="3"><fmt:message
                                        code="view.list.contact.stages.conversation"/></label>
                                <label class="checkbox"><input type="checkbox" class="checkbox" name="contactStages"
                                                               value="4"><fmt:message
                                        code="view.list.contact.stages.decide"/></label>
                                <label class="checkbox"><input type="checkbox" class="checkbox" name="contactStages"
                                                               value="5"><fmt:message
                                        code="view.list.contact.stages.agreement"/></label>
                                <label class="checkbox"><input type="checkbox" class="checkbox" name="contactStages"
                                                               value="6"><fmt:message
                                        code="view.list.contact.stages.success.implemented"/></label>
                                <label class="checkbox"><input type="checkbox" class="checkbox" name="contactStages"
                                                               value="7"><fmt:message
                                        code="view.list.contact.stages.closed"/></label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-4" for="contactManager"><fmt:message
                                    code="view.list.contact.manager"/>:</label>
                            <div class="col-md-8">
                                <select class="form-control" id="contactManager" name="contactManager">
                                    <c:forEach var="manager" items="${requestScope.userList}">
                                        <option value="${manager.id}">${manager.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-4" for="contactTask"><fmt:message
                                    code="view.list.contact.tasks"/>:</label>
                            <div class="col-md-8">
                                <select class="form-control" id="contactTask" name="contactTask">
                                    <option value="0"><fmt:message code="view.list.contact.tasks.ignore"/></option>
                                    <option value="1"><fmt:message code="view.list.contact.tasks.for.today"/></option>
                                    <option value="2"><fmt:message code="view.list.contact.tasks.next.day"/></option>
                                    <option value="3"><fmt:message code="view.list.contact.tasks.this.week"/></option>
                                    <option value="4"><fmt:message code="view.list.contact.tasks.this.month"/></option>
                                    <option value="5"><fmt:message code="view.list.contact.tasks.this.quarter"/></option>
                                    <option value="6"><fmt:message code="view.list.contact.tasks.no.tasks"/></option>
                                    <option value="7"><fmt:message code="view.list.contact.tasks.overdue"/></option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-4" for="contactTag"><fmt:message
                                    code="view.list.contact.tasks.tags"/>:</label>
                            <div class="col-md-8">
                                <select class="form-control" id="contactTag" name="contactTag">
                                    <c:forEach var="tag" items="${requestScope.tagList}">
                                        <option value="${tag.id}">${tag.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group" align="center">
                            <input type="submit" class="btn btn-default"
                                   value="<fmt:message code="view.list.contact.submit"/>"
                                   title="<fmt:message code="view.list.contact.submit.title"/>">
                            <input type="reset" class="btn btn-default"
                                   value="<fmt:message code="view.list.contact.reset"/>"
                                   title="<fmt:message code="view.list.contact.reset.title"/>">
                        </div>
                    </form>
                    <div class="col-md-8">
                        <div class="row">
                            <div class="col-md-8" align="left">
                                <h3><fmt:message code="view.list.contact.title"/></h3>
                            </div>
                            <div class="col-md-4" align="right">
                                <br>
                                <input type="button" class="btn btn-primary" onclick="location.href='/contactcreate'"
                                       value="<fmt:message code="view.list.contact.add"/>">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12" align="center">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th><fmt:message code="view.list.contact.name"/></th>
                                        <th><fmt:message code="view.list.contact.company"/></th>
                                        <th><fmt:message code="view.list.contact.phone"/></th>
                                        <th><fmt:message code="view.list.contact.email"/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${requestScope.contactList}" var="contact">
                                        <tr>
                                            <td><c:out value="${contact.name}"/></td>
                                            <td><c:out value="${contact.company.name}"/></td>
                                            <td><c:out value="${contact.phone}"/></td>
                                            <td><c:out value="${contact.email}"/></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="companies" class="tab-pane fade">
                    <form class="form-horizontal col-md-4" id="formCompany">
                        <fieldset>
                            <legend>... фильтры компаний</legend>
                        </fieldset>
                    </form>
                    <div class="col-md-8">
                        <div class="row">
                            <div class="col-md-8" align="left">
                                <h3><fmt:message code="view.list.company.title"/></h3>
                            </div>
                            <div class="col-md-4" align="right">
                                <br>
                                <button type="button" class="btn btn-primary" onclick="location.href='/company'"><fmt:message
                                        code="view.list.company.add"/>
                                </button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12" align="center">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th><fmt:message code="view.list.company.name"/></th>
                                        <th><fmt:message code="view.list.company.phone"/></th>
                                        <th><fmt:message code="view.list.company.email"/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${requestScope.companyList}" var="company">
                                        <tr>
                                            <td><c:out value="${company.getName()}"></c:out></td>
                                            <td><c:out value="${company.getPhone()}"></c:out></td>
                                            <td><c:out value="${company.getEmail()}"></c:out></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>