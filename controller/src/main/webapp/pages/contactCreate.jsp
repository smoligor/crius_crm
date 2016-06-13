<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message code="new.contact.title"/></title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="/resources/css/contactCreate.css" type="text/css">
    <script src="/resources/js/jquery.min.js" type="text/javascript"></script>
    <script src="/resources/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/resources/js/contactCreate.js" type="text/javascript"></script>
</head>
<body onload="onBodyLoad()">
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2">
            <jsp:include page="menu.jsp"/>
        </div>
        <div class="col-md-10" style="padding-left: 45px">
            <form class="form-horizontal" name="form_contact" role="form" method="post" enctype="multipart/form-data">
                <fieldset id="fieldset_contact">
                    <legend><fmt:message code="new.contact.title.new"/></legend>
                    <div class="form-group">
                        <label class="control-label col-xs-3" for="contact_name"><fmt:message
                                code="new.contact.name"/>:</label>
                        <div class="col-xs-9">
                            <input class="form-control" type="text" id="contact_name" name="contact_name" required
                                   placeholder="<fmt:message code="new.contact.name"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-3" for="contact_tag"><fmt:message
                                code="new.contact.tags"/>:</label>
                        <div class="col-xs-9">
                            <input class="form-control" type="text" id="contact_tag" name="contact_tag"
                                   placeholder="<fmt:message code="new.contact.tags.rule"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-3" for="contact_user_id"><fmt:message
                                code="new.contact.responsible"/>:</label>
                        <div class="col-xs-9">
                            <select class="form-control" id="contact_user_id" name="contact_user_id"
                                    title="<fmt:message code="new.contact.responsible"/>"
                                    required>
                                <option disabled selected value style="display: none"></option>
                                <c:forEach var="user" items="${requestScope.userList}">
                                    <option value="<c:out value="${user.id}"/>"><c:out value="${user.name}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-3" for="contact_position"><fmt:message
                                code="new.contact.position"/>:</label>
                        <div class="col-xs-9">
                            <input class="form-control" type="text" id="contact_position" name="contact_position"
                                   placeholder="<fmt:message code="new.contact.position"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <select class="form-control" name="contact_type_of_phone"
                                    title="<fmt:message code="new.contact.phone.type"/>" required
                                    style="font-weight: bold; text-align-last: right;">
                                <c:forEach var="typeOfPhone" items="${requestScope.typeOfPhoneArray}">
                                    <option value="<c:out value="${typeOfPhone.id}"/>"><c:out
                                            value="${typeOfPhone}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-xs-9">
                            <input class="form-control" type="tel" name="contact_phone"
                                   title="<fmt:message code="new.contact.phone"/>"
                                   placeholder="<fmt:message code="new.contact.phone"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-3" for="contact_email"><fmt:message
                                code="new.contact.email"/>:</label>
                        <div class="col-xs-9">
                            <input class="form-control" type="email" id="contact_email" name="contact_email"
                                   placeholder="<fmt:message code="new.contact.email"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-3" for="contact_skype"><fmt:message
                                code="new.contact.skype"/>:</label>
                        <div class="col-xs-9">
                            <input class="form-control" type="text" id="contact_skype" name="contact_skype"
                                   placeholder="<fmt:message code="new.contact.skype"/>">
                        </div>
                    </div>
                    <div class="form-group" align="right">
                        <input class="btn btn-default fieldReset" type="button"
                               value="<fmt:message code="new.contact.button.reset"/>"
                               title="<fmt:message code="new.contact.button.reset.title"/>">
                    </div>
                </fieldset>
                <fieldset id="fieldset_note">
                    <legend><fmt:message code="new.contact.note.title"/></legend>
                    <div class="form-group">
                        <div class="col-xs-3"></div>
                        <div class="col-xs-9">
                    <textarea class="form-control" id="note_text" name="note_text"
                              placeholder="<fmt:message code="new.contact.note.title"/>"
                              rows="2"></textarea>
                        </div>
                    </div>
                    <div class="form-group" align="right">
                        <input class="btn btn-default fieldReset" type="button"
                               value="<fmt:message code="new.contact.button.reset"/>"
                               title="<fmt:message code="new.contact.button.reset.title"/>">
                    </div>
                </fieldset>
                <fieldset id="fieldset_file">
                    <legend><fmt:message code="new.contact.file.title"/></legend>
                    <div class="form-group">
                        <div class="col-xs-3"></div>
                        <div class="col-xs-9">
                            <input class="form-control" type="file" id="file_file" name="file_file">
                        </div>
                    </div>
                    <div class="form-group" align="right">
                        <input class="btn btn-default fieldReset" type="button"
                               value="<fmt:message code="new.contact.button.reset"/>"
                               title="<fmt:message code="new.contact.button.reset.title"/>">
                    </div>
                </fieldset>
                <fieldset id="fieldset_company">
                    <legend><fmt:message code="new.contact.company.title"/></legend>
                    <div class="form-group" align="center">
                        <div class="col-xs-1"></div>
                        <div class="col-xs-5">
                            <label class="radio-inline">
                                <input type="radio" name="company_new" value="0"
                                       onchange="companyRadioChange(false)"><fmt:message
                                    code="new.contact.company.choose.existing"/></label>
                        </div>
                        <div class="col-xs-5">
                            <label class="radio-inline">
                                <input type="radio" name="company_new" value="1"
                                       onchange="companyRadioChange(true)" checked><fmt:message
                                    code="new.contact.company.create.new"/></label>
                        </div>
                    </div>
                    <div class="form-group companySelect" hidden>
                        <label class="control-label col-xs-3" for="company_id"><fmt:message
                                code="new.contact.company.choose.existing.company"/>:</label>
                        <div class="col-xs-9">
                            <select class="form-control" id="company_id" name="company_id"
                                    title="<fmt:message code="new.contact.company.choose.company"/>">
                                <option disabled selected value style="display: none"></option>
                                <c:forEach var="company" items="${requestScope.companyList}">
                                    <option value="<c:out value="${company.id}"/>"><c:out
                                            value="${company.name}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group companyNew">
                        <label class="control-label col-xs-3" for="company_name"><fmt:message
                                code="new.contact.company.create.name"/>:</label>
                        <div class="col-xs-9">
                            <input class="form-control" type="text" id="company_name" name="company_name"
                                   title="<fmt:message code="new.contact.company.create.name"/>"
                                   placeholder="<fmt:message code="new.contact.company.create.name"/>">
                        </div>
                    </div>
                    <div class="form-group companyNew">
                        <label class="control-label col-xs-3" for="company_phone"><fmt:message
                                code="new.contact.company.create.phone"/>:</label>
                        <div class="col-xs-9">
                            <input class="form-control" type="tel" id="company_phone" name="company_phone"
                                   title="<fmt:message code="new.contact.company.create.phone"/>"
                                   placeholder="<fmt:message code="new.contact.company.create.phone"/>">
                        </div>
                    </div>
                    <div class="form-group companyNew">
                        <label class="control-label col-xs-3" for="company_email"><fmt:message
                                code="new.contact.company.create.email"/>:</label>
                        <div class="col-xs-9">
                            <input class="form-control" type="email" id="company_email" name="company_email"
                                   placeholder="<fmt:message code="new.contact.company.create.email"/>">
                        </div>
                    </div>
                    <div class="form-group companyNew">
                        <label class="control-label col-xs-3" for="company_web"><fmt:message
                                code="new.contact.company.create.website"/>:</label>
                        <div class="col-xs-9">
                            <input class="form-control" type="url" id="company_web" name="company_web"
                                   placeholder="<fmt:message code="new.contact.company.create.website"/>">
                        </div>
                    </div>
                    <div class="form-group companyNew">
                        <label class="control-label col-xs-3" for="company_address"><fmt:message
                                code="new.contact.company.create.address"/>:</label>
                        <div class="col-xs-9">
                    <textarea class="form-control" id="company_address" name="company_address" rows="2"
                              placeholder="<fmt:message code="new.contact.company.create.address"/>"></textarea>
                        </div>
                    </div>
                    <div class="form-group" align="right">
                        <input class="btn btn-default fieldReset" type="button"
                               value="<fmt:message code="new.contact.button.reset"/>"
                               title="<fmt:message code="new.contact.button.reset.title"/>">
                    </div>
                </fieldset>
                <fieldset id="fieldset_deal">
                    <legend><fmt:message code="new.contact.deal.title"/></legend>
                    <div class="form-group">
                        <label class="control-label col-xs-3" for="deal_name"><fmt:message
                                code="new.contact.deal.name"/>:</label>
                        <div class="col-xs-9">
                            <input class="form-control" type="text" id="deal_name" name="deal_name"
                                   title="<fmt:message code="new.contact.deal.name"/>"
                                   placeholder="<fmt:message code="new.contact.deal.name"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-3" for="deal_stage_name"><fmt:message
                                code="new.contact.deal.stage"/>:</label>
                        <div class="col-xs-9">
                            <input class="form-control" id="deal_stage_name" name="deal_stage_name"
                                   title="<fmt:message code="new.contact.deal.stage"/>" list="deal_stage_options"
                                   placeholder="<fmt:message code="new.contact.deal.stage"/>">
                            <datalist id="deal_stage_options">
                                <c:forEach var="stage" items="${requestScope.stageList}">
                                    <option value="<c:out value="${stage.name}"/>"></option>
                                </c:forEach>
                            </datalist>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-3" for="deal_budget"><fmt:message
                                code="new.contact.deal.budget"/>:</label>
                        <div class="col-xs-9">
                            <input class="form-control" type="number" min="0" step="0.01" id="deal_budget"
                                   name="deal_budget"
                                   title="<fmt:message code="new.contact.deal.budget"/>"
                                   placeholder="<fmt:message code="new.contact.deal.budget"/>">
                        </div>
                    </div>
                    <div class="form-group" align="right">
                        <input class="btn btn-default fieldReset" type="button"
                               value="<fmt:message code="new.contact.button.reset"/>"
                               title="<fmt:message code="new.contact.button.reset.title"/>">
                    </div>
                </fieldset>
                <fieldset id="fieldset_task">
                    <legend><fmt:message code="new.contact.task.title"/></legend>
                    <div class="form-group">
                        <label class="control-label col-xs-3" for="task_period"><fmt:message
                                code="new.contact.task.date.time"/>:</label>
                        <div class="col-xs-3">
                            <select class="form-control" id="task_period"
                                    title="<fmt:message code="new.contact.task.date.time.title"/>"
                                    onchange="taskPeriodChange()">
                                <option disabled selected value style="display: none"></option>
                                <c:forEach var="period" items="${requestScope.typeOfPeriodArray}">
                                    <option value="<c:out value="${period.id}"/>"><c:out value="${period}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-xs-3" style="padding-left: 0; padding-right: 0;">
                            <input class="form-control" type="date" id="task_date" name="task_date"
                                   title="<fmt:message code="new.contact.task.date.title"/>"
                                   style="font-weight: bold; text-align: center; padding-left: 6px; padding-right: 6px;"/>
                        </div>
                        <div class="col-xs-3">
                            <select class="form-control" id="task_time" name="task_time"
                                    title="<fmt:message code="new.contact.task.time.title"/>"
                                    style="font-weight: bold; text-align-last: center; padding-left: 6px; padding-right: 6px;">
                                <c:forEach var="taskTime" varStatus="status" items="${requestScope.taskTimeList}">
                                    <option value="<c:out value="${status.index}"/>"><c:out
                                            value="${taskTime}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-3" for="task_responsible_user_id"><fmt:message
                                code="new.contact.task.responsible"/>:</label>
                        <div class="col-xs-9">
                            <select class="form-control" id="task_responsible_user_id" name="task_responsible_user_id"
                                    title="<fmt:message code="new.contact.task.responsible"/>">
                                <option disabled selected value style="display: none"></option>
                                <c:forEach var="user" items="${requestScope.userList}">
                                    <option value="<c:out value="${user.id}"/>"><c:out value="${user.name}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-3" for="task_type"><fmt:message
                                code="new.contact.task.type"/>:</label>
                        <div class="col-xs-9">
                            <select class="form-control" id="task_type" name="task_type"
                                    title="<fmt:message code="new.contact.task.type"/>">
                                <option disabled selected value style="display: none"></option>
                                <c:forEach var="taskType" items="${requestScope.taskTypeList}">
                                    <option><c:out value="${taskType}"/></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-3" for="task_text"><fmt:message
                                code="new.contact.task.text"/>:</label>
                        <div class="col-xs-9">
                    <textarea class="form-control" id="task_text" name="task_text"
                              placeholder="<fmt:message code="new.contact.task.text"/>"
                              rows="2"></textarea>
                        </div>
                    </div>
                    <div class="form-group" align="right">
                        <input class="btn btn-default fieldReset" type="button"
                               value="<fmt:message code="new.contact.button.reset"/>"
                               title="<fmt:message code="new.contact.button.reset.title"/>">
                    </div>
                </fieldset>
                <div class="form-group" align="right">
                    <div class="btn-group">
                        <input class="btn btn-default" type="submit"
                               value="<fmt:message code="new.contact.button.save"/>"
                               title="<fmt:message code="new.contact.button.save.title"/>">
                        <input class="btn btn-default" type="reset"
                               value="<fmt:message code="new.contact.button.reset.all"/>"
                               title="<fmt:message code="new.contact.button.reset.all.title"/>"
                               onclick="onResetAllClick()">
                        <input class="btn btn-default" type="reset"
                               value="<fmt:message code="new.contact.button.cancel"/>"
                               title="<fmt:message code="new.contact.button.cancel.title"/>" onclick="history.go(-1);">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
