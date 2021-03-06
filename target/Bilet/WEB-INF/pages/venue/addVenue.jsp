<%--
  Created by IntelliJ IDEA.
  User: Kray
  Date: 2018/1/25
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bilet 场馆申请</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="/assets/bootstrap-3.3.7-dist/css/bootstrap.min.css">
</head>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">返回</a>
        </div>
    </div>
</nav>

<body>
<div class="container">
    <h1>Bilet 场馆申请</h1>
    <hr/>
    <form:form action="/venue/addPost" method="post" role="form">
        <div class="form-group">
            <label for="name">名称</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="请输入名称"/>
        </div>
        <div class="form-group">
            <label for="password">密码</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码"/>
        </div>
        <div class="form-group">
            <label for="address">地址</label>
            <input type="text" class="form-control" id="address" name="address" placeholder="请输入地址"/>
        </div>
        <button type="submit" class="btn btn-sm btn-success">提交</button>
    </form:form>
</div>
<%@include file="../jsFile.jsp" %>
</body>
</html>