<%--
  Created by IntelliJ IDEA.
  User: Kray
  Date: 2018/1/25
  Time: 22:54
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
    <title>Bilet 场馆登录</title>
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
    <h1>Bilet 场馆登录</h1>
    <hr/>

    <%@include file="../displayError.jsp" %>

    <form:form action="/venue/loginPost" method="post" role="form">
        <div class="form-group">
            <label for="vid">7 位识别码</label>
            <input type="text" minlength="7" maxlength="7" class="form-control" id="vid" name="vid" placeholder="请输入识别码"
                   required/>
            <script type="text/javascript">
                var text = document.getElementById("vid");
                text.onkeyup = function () {
                    this.value = this.value.replace(/\D/g, '');
                }
            </script>
        </div>
        <div class="form-group">
            <label for="password">密码</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码" required/>
        </div>
        <hr/>
        <button type="submit" class="btn btn-sm btn-success">登录</button>
    </form:form>
</div>
<%@include file="../jsFile.jsp" %>
</body>
</html>