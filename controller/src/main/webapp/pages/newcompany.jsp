<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title><fmt:message code="new.company.title"/></title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/css/bootstrapValidator.min.css"/>
    <link rel="stylesheet" href="/resources/css/bootstrap-datetimepicker.min.css"/>
    <link rel="stylesheet" href="/resources/css/company.css"/>
    <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/js/moment-with-locales.min.js"></script>
    <script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/resources/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="/resources/js/bootstrapValidator.min.js"></script>
    <script type="text/javascript" src="/resources/js/company.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <form class="form-horizontal" role="form" method="post" action="/company" id="companyForm"
              enctype="multipart/form-data">
            <div class="col-md-3"><jsp:include page="menu.jsp"/></div>
            <div class="col-md-4">
                <div class="company" align="center">
                    <br>
                    <h3><fmt:message code="new.company.title"/></h3>
                    <br>
                    <div class="form-group">
                        <label class="control-label col-md-5" for="companyName"><h4><fmt:message
                                code="new.company.name"/>:</h4></label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="companyName" name="companyName"
                                   placeholder="<fmt:message code="new.company.name"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-5" for="companyTag"><h4><fmt:message
                                code="new.company.tags"/>:</h4></label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="companyTag" name="companyTag"
                                   placeholder="<fmt:message code="new.company.tags"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-5" for="companyResponsibleUser"><h4><fmt:message
                                code="new.company.responsible"/>:</h4>
                        </label>
                        <div class="col-md-7">
                            <select class="form-control" id="companyResponsibleUser" name="companyResponsibleUser">
                                <c:forEach items="${responsibleUsers}" var="user">
                                    <option><c:out value="${user.getName()}"></c:out></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-5" for="companyPhone"><h4><fmt:message
                                code="new.company.phone"/>:</h4></label>
                        <div class="col-md-7">
                            <input type="number" class="form-control" id="companyPhone" name="companyPhone"
                                   placeholder="<fmt:message code="new.company.phone"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-5" for="companyEmail"><h4><fmt:message
                                code="new.company.email"/>:</h4></label>
                        <div class="col-md-7">
                            <input type="email" class="form-control" id="companyEmail" name="companyEmail"
                                   placeholder="Email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-5" for="companyWeb"><h4><fmt:message
                                code="new.company.website"/>:</h4></label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="companyWeb" name="companyWeb"
                                   placeholder="<fmt:message code="new.company.website"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-5" for="companyAddress"><h4><fmt:message
                                code="new.company.address"/>:</h4></label>
                        <div class="col-md-7">
                            <textarea type="text" class="form-control" id="companyAddress"
                                      name="companyAddress" rows="3"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-5" for="companyNote"><h4><fmt:message
                                code="new.company.note"/>:</h4></label>
                        <div class="col-md-7">
                            <textarea type="text" class="form-control" id="companyNote" name="companyNote"
                                      rows="4"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-12" align="right">
                            <label class="btn btn-primary" for="companyFile">
                                <input id="companyFile" name="companyFile" type="file" style="display:none;">
                                <fmt:message code="new.company.files"/>
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-5" for="companyContact"><h4><fmt:message
                                code="new.company.contact.select"/>:</h4></label>
                        <div class="col-md-7">
                            <select class="form-control" id="companyContact" name="companyContact">
                                <c:forEach items="${contactList}" var="companyContact">
                                    <option><c:out value="${companyContact.getName()}"></c:out></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-12" align="right">
                            <button type="button" class="btn btn-primary" id="add_contact"><fmt:message
                                    code="company.contact.title"/></button>
                        </div>
                    </div>
                </div>
                <div class="contact" align="center" style="display:none;">
                    <h3><fmt:message code="company.contact.title"/></h3>
                    <br>
                    <div class="form-group">
                        <label class="control-label col-md-5" for="contactName"><h4><fmt:message
                                code="company.contact.contact.name"/>:</h4></label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="contactName" name="contactName"
                                   placeholder="<fmt:message code="company.contact.contact.name"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-5" for="contactPosition"><h4><fmt:message
                                code="company.contact.contact.position"/>:</h4></label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="contactPosition" name="contactPosition"
                                   placeholder="<fmt:message code="company.contact.contact.position"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-5">
                            <select class="form-control col-md-4" name="typePhone">
                                <c:forEach items="${typeOfPhone}" var="companyPhone">
                                    <option><c:out value="${companyPhone}"></c:out></option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-7">
                            <input type="number" class="form-control" name="contactPhone"
                                   placeholder="<fmt:message code="company.contact.contact.phone"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-5" for="contactEmail"><h4><fmt:message
                                code="company.contact.contact.email"/>:</h4></label>
                        <div class="col-md-7">
                            <input type="email" class="form-control" id="contactEmail" name="contactEmail"
                                   placeholder="Email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-5" for="contactSkype"><h4><fmt:message
                                code="company.contact.contact.skype"/>:</h4></label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="contactSkype" name="contactSkype"
                                   placeholder="Skype">
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="deal" align="center">
                    <br>
                    <h3><fmt:message code="company.deals.title"/></h3>
                    <br>
                    <div class="form-group">
                        <div class="col-md-12" align="center">
                            <button type="button" class="btn btn-primary" id="add_deal"><fmt:message
                                    code="company.deal.title"/></button>
                        </div>
                    </div>
                </div>
                <div class="quick_deal" align="center" style="display: none;">
                    <h3><fmt:message code="company.deal.title"/></h3>
                    <br>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="dealName"><h4><fmt:message
                                code="company.deal.name"/>:</h4></label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" id="dealName" name="dealName"
                                   placeholder="<fmt:message code="company.deal.name"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="dealStage"><h4><fmt:message
                                code="company.deal.stage"/>:</h4></label>
                        <div class="col-md-7">
                            <select class="form-control" id="dealStage" name="dealStage">
                                <c:forEach items="${stageDeals}" var="dealStage">
                                    <option><c:out value="${dealStage.value}"></c:out></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="dealBudget"><h4><fmt:message
                                code="company.deal.budget"/>:</h4></label>
                        <div class="col-md-7">
                            <input type="number" class="form-control" id="dealBudget" name="dealBudget"
                                   placeholder="<fmt:message code="company.deal.budget"/>" step="any">
                        </div>
                    </div>
                </div>
                <div class="task" align="center">
                    <h3><fmt:message code="company.task.title"/></h3>
                    <br>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="period"><h4><fmt:message
                                code="company.task.period"/>:</h4></label>
                        <div class="col-md-7">
                            <select class="form-control" id="period" name="period">
                                <c:forEach items="${typeOfPeriod}" var="period">
                                    <option><c:out value="${period}"></c:out></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4"><h4><fmt:message code="company.task.date"/>:</h4></label>
                        <div class="col-md-7">
                            <input type="datetime" class="form-control" id="dateTask" name="dateTask"
                                   placeholder="dd/mm/yyyy"
                                   path="dateTask"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="taskTime"><h4><fmt:message
                                code="company.task.time"/>:</h4></label>
                        <div class="col-md-7">
                            <select class="form-control" id="taskTime" name="taskTime">
                                <c:forEach items="${timeListForTask}" var="time">
                                    <option><c:out value="${time}"></c:out></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="taskResponsibleUser"><h4><fmt:message
                                code="company.task.responsible"/>:</h4></label>
                        <div class="col-md-7">
                            <select class="form-control" id="taskResponsibleUser" name="taskResponsibleUser">
                                <c:forEach items="${responsibleUsers}" var="user">
                                    <option><c:out value="${user.getName()}"></c:out></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4" for="taskType"><h4><fmt:message
                                code="company.task.type"/>:</h4></label>
                        <div class="col-md-7">
                            <select class="form-control" id="taskType" name="taskType">
                                <c:forEach items="${taskType}" var="type">
                                    <option><c:out value="${type.value}"></c:out></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12" align="center">
                        <button type="submit" class="btn btn-success" form="companyForm"><fmt:message
                                code="company.button.saveAll"/></button>
                        <button type="reset" class="btn btn-primary"><fmt:message
                                code="company.button.resetAll"/></button>
                    </div>
                </div>
            </div>
            <div class="col-md-1"></div>
        </form>
    </div>
</div>
</body>
</html>