<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Kray
  Date: 2018/1/25
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bilet 用户详情</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="/assets/bootstrap-3.3.7-dist/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <h1>Bilet 用户详情</h1>
    <hr/>
    <h2>欢迎您！${member.email}</h2>
    <hr/>
    <a href="/member/order/${member.mid}" type="button" class="btn btn-info btn-sm">我的订单</a>
    <a href="/member/coupon/${member.mid}" type="button" class="btn btn-info btn-sm">我的优惠券</a>
    <a href="/member/book/${member.mid}" type="button" class="btn btn-info btn-sm">我的账单</a>
    <a href="/member/update/${member.mid}" type="button" class="btn btn-warning btn-sm">修改信息</a>
    <hr/>
    <table class="table table-bordered table-striped">
        <tr>
            <th rowspan=$row_host1 style='vertical-align: middle;'>邮箱</th>
            <td rowspan=$row_host1 style='vertical-align: middle;'>${member.email}</td>
        </tr>
        <tr>
            <th rowspan=$row_host1 style='vertical-align: middle;'>银行账户</th>
            <td rowspan=$row_host1 style='vertical-align: middle;'>${member.bankAccount}</td>
        </tr>
        <tr>
            <th rowspan=$row_host1 style='vertical-align: middle;'>余额</th>
            <td rowspan=$row_host1 style='vertical-align: middle;'>
                ${member.balance}
                <a class="btn btn-default" href="/member/charge/${member.mid}" role="button">充值</a>
            </td>
        </tr>
        <tr>
            <th rowspan=$row_host1 style='vertical-align: middle;'>累积积分</th>
            <td rowspan=$row_host1 style='vertical-align: middle;'>${member.totalPoint}</td>
        </tr>
        <tr>
            <th rowspan=$row_host1 style='vertical-align: middle;'>可兑换积分</th>
            <td rowspan=$row_host1 style='vertical-align: middle;'>${member.currentPoint}</td>
        </tr>
        <tr>
            <th rowspan=$row_host1 style='vertical-align: middle;'>等级</th>
            <td rowspan=$row_host1 style='vertical-align: middle;'>${member.level}</td>
        </tr>
        <tr>
            <th rowspan=$row_host1 style='vertical-align: middle;'>邮箱状态</th>
            <td rowspan=$row_host1 style='vertical-align: middle;'>${member.isEmailPassed}</td>
        </tr>
        <tr>
            <th rowspan=$row_host1 style='vertical-align: middle;'>账户状态</th>
            <td rowspan=$row_host1 style='vertical-align: middle;'>${member.isTerminated}</td>
        </tr>
    </table>
    <c:if test="${!empty events}">
        <hr/>
        <h2>演出列表</h2>
        <table class="table table-bordered table-striped">
            <tr>
                <th>名称</th>
                <th>类别</th>
                <th>时间</th>
                <th>描述</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${events}" var="event">
                <tr>
                    <td rowspan=$row_host1 style='vertical-align: middle;'>${event.name}</td>
                    <td rowspan=$row_host1 style='vertical-align: middle;'>${event.eventType}</td>
                    <td rowspan=$row_host1 style='vertical-align: middle;'>${event.time}</td>
                    <td rowspan=$row_host1 style='vertical-align: middle;'>${event.description}</td>
                    <td rowspan=$row_host1 style='vertical-align: middle;'>
                        <a class="btn btn-primary" href="/member/order/${member.mid}/event/${event.eid}/chooseSeat"
                           role="button">选座购票</a>
                        <c:if test="${event.canRandom}">
                            <a class="btn btn-primary" href="/member/order/${member.mid}/event/${event.eid}/randomSeat"
                               role="button">立即购票</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <hr/>
    <a href="/member/logout" type="button" class="btn btn-danger btn-sm">登出</a>
</div>
<%@include file="../jsFile.jsp" %>
</body>
</html>