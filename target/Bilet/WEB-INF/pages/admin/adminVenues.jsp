<%--
  Created by IntelliJ IDEA.
  User: Kray
  Date: 2018/1/26
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bilet 场地管理</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="/assets/bootstrap-3.3.7-dist/css/bootstrap.min.css">
</head>
<body>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/admin/adminHome">返回</a>
        </div>
    </div>
</nav>

<div class="container">
    <h1>Bilet 场地管理</h1>
    <hr/>
    <c:if test="${empty venueList}">
        <div class="alert alert-success" role="alert">
            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>没有待审核的场地信息
        </div>
    </c:if>
    <c:if test="${!empty venueList}">
        <table class="table table-bordered table-striped">
            <tr>
                <th>场地 ID</th>
                <th>名称</th>
                <th>地址</th>
                <th>座位数</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${venueList}" var="venue">
                <tr>
                    <td rowspan=$row_host1 style='vertical-align: middle;'>${venue.vid}</td>
                    <td rowspan=$row_host1 style='vertical-align: middle;'>${venue.name}</td>
                    <td rowspan=$row_host1 style='vertical-align: middle;'>${venue.address}</td>
                    <td rowspan=$row_host1 style='vertical-align: middle;'>${venue.seatsNumber}</td>
                    <td rowspan=$row_host1 style='vertical-align: middle;'>
                        <a href="/admin/venues/events/${venue.vid}" type="button"
                           class="btn btn-sm btn-primary">活动查看</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<%@include file="../jsFile.jsp" %>
</body>
</html>
