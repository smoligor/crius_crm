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
    <title><fmt:message code="view.deal.title"/></title>

    <link href="/resources/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="/resources/css/bootstrap-datetimepicker.min.css"/>
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
                    <h3><fmt:message code="view.deal.filters"/></h3>
                    <div class="form-group">
                        <div class="col-md-12">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" checked value=""><fmt:message
                                        code="view.deal.filters.upcomming.deals"/>
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value=""><fmt:message code="view.deal.filters.my.deals"/>
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value=""><fmt:message
                                        code="view.deal.filters.success.finished.deals"/>
                                </label>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" value=""><fmt:message
                                            code="view.deal.filters.unrealised.deals"/>
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" value=""><fmt:message
                                            code="view.deal.without.task.deals"/>
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" value=""><fmt:message
                                            code="view.deal.with.overdue.tasks.deals"/>
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" value=""><fmt:message code="view.deal.deleted"/>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-9">
                    <h3><fmt:message code="view.deal.period.title"/></h3>
                    <form class="form-inline" role="form">
                        <div class="form-group">
                            <div class="radio">
                                <label><input type="radio" for="period_id">
                                    <fmt:message code="view.deal.period"/>:
                                    <select class="form-control" id="period_id">
                                        <option>00:00</option>
                                        <option>00:15</option>
                                        <option>00:30</option>
                                        <option>00:45</option>
                                    </select>
                                </label>
                            </div>
                        </div>
                    </form>
                    <div class="radio">
                        <label><input type="radio" name=""></label>
                        <form role="form">
                            <div class="form-group">
                                <div class="form-group">
                                    <label class="pull-left"><fmt:message code="view.deal.period.from"/>:</label>
                                    <div class="input-group date" id="datetimepicker1">
                                        <input type="text" class="form-control"/>
                                        <span class="input-group-addon">
                                          <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="pull-left"><fmt:message code="view.deal.period.to"/>:</label>
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
                <div class="col-md-3"></div>
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
                                <label for="stage"><fmt:message code="view.deal.stages"/>:</label>
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
                                <label for="manager"><fmt:message code="view.deal.managers"/>:</label>
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
                                <label for="task"><fmt:message code="view.deal.tasks"/>:</label>
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
                                <label for="tag"><fmt:message code="view.deal.tags"/>:</label>
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
                <a href="/dealFunnel" class="btn btn-primary"><fmt:message code="view.deal.funnel"/></a>
                <a href="/dealList" class="btn btn-primary"><fmt:message code="view.deal.list"/></a>
                <a href="/addDeals" class="btn btn-primary pull-center"><fmt:message code="view.deal.add"/></a>
            </div>
            <br><br>
            <div class="row">
                <div class="col-md-9" align="center">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th><fmt:message code="view.deal.name"/></th>
                            <th><fmt:message code="view.deal.main.contact"/></th>
                            <th><fmt:message code="view.deal.company.contacts"/></th>
                            <th><fmt:message code="view.deal.stage.deal"/></th>
                            <th><fmt:message code="view.deal.budget"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="deal" items="${dealList}">
                            <tr>
                                <td><c:out value="${deal.name}"/></td>
                                <td><c:out value="${deal.getPrimaryContact().getName()}"/></td>
                                <td><c:out value="${deal.getPrimaryContact().getCompany().getName()}"/></td>
                                <td><c:out value="${deal.getStage().getName()}"/></td>
                                <td><c:out value="${deal.amount}"/></td>
                            </tr>
                        </c:forEach>
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