<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><fmt:message code="deal.funnel.title"/></title>

    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/bootstrap-datetimepicker.min.css" />
    <script type="text/javascript" src="/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/resources/js/moment-with-locales.min.js"></script>
    <script type="text/javascript" src="/resources/js/bootstrap-datetimepicker.min.js"></script>

</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2"><jsp:include page="menu.jsp"/></div>
        <div class="col-md-3" style="padding-left: 30px">
            <div class="row">
                <div class="col-md-12">
                    <h3><fmt:message code="deal.funnel.filters"/></h3>
                    <div class="form-group">
                        <div class="col-md-12">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" checked value=""><fmt:message
                                        code="deal.funnel.filters.upcomming.deals"/>
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value=""><fmt:message code="deal.funnel.filters.my.deals"/>
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value=""><fmt:message
                                        code="deal.funnel.filters.success.finished.deals"/>
                                </label>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" value=""><fmt:message
                                            code="deal.funnel.filters.unrealised.deals"/>
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" value=""><fmt:message
                                            code="deal.funnel.without.task.deals"/>
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" value=""><fmt:message
                                            code="deal.funnel.with.overdue.tasks.deals"/>
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" value=""><fmt:message code="deal.funnel.deleted"/>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-7">
                    <h3><fmt:message code="deal.funnel.period.title"/></h3>
                    <form class="form-inline" role="form">
                        <div class="form-group">
                            <div class="radio">
                                <label><input type="radio" name="" for="period_id">
                                    <fmt:message code="deal.funnel.period"/>:
                                </label>
                            </div>
                            <select class="form-control" id="period_id">
                                <option>00:00</option>
                                <option>00:15</option>
                                <option>00:30</option>
                                <option>00:45</option>
                            </select>
                        </div>
                    </form>
                    <div class="radio">
                        <label><input type="radio" name=""></label>
                        <form role="form">
                            <div class="form-group">
                                <div class="form-group">
                                    <label class="pull-left"><fmt:message code="deal.funnel.period.from"/>:</label>
                                    <div class="input-group date" id="datetimepicker1">
                                        <input type="text" class="form-control"/>
                                        <span class="input-group-addon">
                                          <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="pull-left"><fmt:message code="deal.funnel.period.to"/>:</label>
                                    <div class="input-group date" id="datetimepicker2">
                                        <input type="text" class="form-control"/>
                                        <span class="input-group-addon">
                                          <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-md-5"></div>
            </div>
            <script type="text/javascript">
                $(function () {
                    $('#datetimepicker1').datetimepicker(
                            {pickTime: false}
                    );
                });
            </script>
            <script type="text/javascript">
                $(function () {
                    $('#datetimepicker2').datetimepicker(
                            {pickTime: false}
                    );
                });
            </script>
            <div class="row">
                <div class="col-md-12">
                    <br>
                    <form class="form-inline" role="form">
                        <div class="row">
                            <div class="col-md-6">
                                <label for="stage"><fmt:message code="deal.funnel.stages"/>:</label>
                            </div>
                            <div class="col-md-6">
                                <select class="form-control" id="stage">
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                </select>
                            </div>
                        </div>
                    </form>
                    <form class="form-inline" role="form">
                        <div class="row">
                            <div class="col-md-6">
                                <label for="manager"><fmt:message code="deal.funnel.managers"/>:</label>
                            </div>
                            <div class="col-md-6">
                                <select class="form-control" id="manager">
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                </select>
                            </div>
                        </div>
                    </form>
                    <div class="row">
                        <form class="form-inline" role="form">
                            <div class="col-md-6">
                                <label for="task"><fmt:message code="deal.funnel.tasks"/>:</label>
                            </div>
                            <div class="col-md-6">
                                <select class="form-control" id="task">
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="row">
                        <form class="form-inline" role="form">
                            <div class="col-md-6">
                                <label for="tag"><fmt:message code="deal.funnel.tags"/>:</label>
                            </div>
                            <div class="col-md-6">
                                <select class="form-control" id="tag">
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                </select>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-7">
            <div class="row">
                <a href="/dealFunnel" class="btn btn-primary"><fmt:message code="deal.funnel.funnel"/></a>
                <a href="/dealList" class="btn btn-primary"><fmt:message code="deal.funnel.list"/></a>
                <a href="/addDeals" class="btn btn-primary"><fmt:message code="deal.funnel.add"/></a>
            </div>
            <br><br>
            <div class="row">
                <div class="col-md-9" align="center">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th><fmt:message code="deal.funnel.primary.contact"/></th>
                            <th><fmt:message code="deal.funnel.conversation"/></th>
                            <th><fmt:message code="deal.funnel.decide"/></th>
                            <th><fmt:message code="deal.funnel.agreement"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <c:forEach var="stage" items="${stageList}">
                                <td>
                                    <c:set var="sum" value="${0}"/>
                                    <c:set var="count" value="${0}"/>
                                    <c:forEach var="deal" items="${dealService.getAllDealsByStage(stage.name)}">
                                        <c:set var="sum" value="${sum + deal.amount}"/>
                                        <c:set var="count" value="${count + 1}"/>
                                    </c:forEach>
                                    <c:out value="${sum}"/> $<br><br>
                                    <c:out value="${count}"/> Сделок<br><br>
                                </td>
                            </c:forEach>
                        </tr>
                        <tr>
                            <c:forEach var="stage" items="${stageList}">
                                <td>
                                    <c:forEach var="deal" items="${dealService.getAllDealsByStage(stage.name)}">
                                        <c:out value="${deal.getName()}"/><br><br>
                                        <c:out value="${deal.getAmount()}"/> $<br><br>
                                        <c:out value="${deal.getCompany().getName()}"/><br><br>
                                        <c:forEach var="contact"
                                                   items="${dealService.getContactsByDealName(deal.name)}">
                                            <c:out value="${contact.getName()}"/><br><br>
                                        </c:forEach>
                                    </c:forEach>
                                </td>
                            </c:forEach>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>