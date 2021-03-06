<%--
  Created by IntelliJ IDEA.
  User: Kray
  Date: 2018/1/25
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bilet 修改场馆信息</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="/assets/bootstrap-3.3.7-dist/css/bootstrap.min.css">
</head>
<body>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/venue/show/${venue.vid}">返回</a>
        </div>
    </div>
</nav>

<div class="container">
    <h1>Bilet 修改场馆信息</h1>
    <hr/>
    <form:form action="/venue/updatePost" method="post" role="form">
        <div class="form-group">
            <label for="name">名称</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="请输入名称" value="${venue.name}"/>
        </div>
        <div class="form-group">
            <label for="password">密码</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码" value="${venue.password}"/>
        </div>
        <div class="form-group">
            <label for="address">地址</label>
            <input type="text" class="form-control" id="address" name="address" placeholder="请输入地址" value="${venue.address}"/>
        </div>
        <input type="hidden" id="vid" name="vid" value="${venue.vid}"/>
        <hr/>
        <b>注意：一旦更新场馆信息，需要重新等待管理员审核通过后才能进行发布活动等其他操作！</b>
        <hr/>
        <div class="form-group">
            <label for="vid"></label>
            <button type="submit" class="btn btn-sm btn-success">提交</button>
        </div>
    </form:form>
</div>
<%@include file="../jsFile.jsp" %>
</body>
</html>