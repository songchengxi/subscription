<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/includes/pageinclude.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>泰洁干洗</title>
    <meta name="description" content="泰洁干洗"/>
    <meta name="keywords" content="泰洁干洗"/>
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <%@ include file="/includes/headinclude.jsp" %>
    <link rel="stylesheet" href="css/amazeui.min.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/amazeui.min.js"></script>
    <script type="text/javascript">
        /*<![CDATA[*/
        var app = angular.module('app', []);

        app.controller('MainController',
                function ($rootScope, $scope, $http) {

                    $scope.data = {};
                    $scope.rows = [];

                    $scope.member = function () {
                        window.location.href = "${basePath}/page/dry-clean/member.jsp";
                    }
                });
        /*]]>*/
    </script>
</head>
<body style="background: rgb(255, 103, 103);" ng-app="app" ng-controller="MainController">
<input type="hidden" id="openId" value="${openId}">
<header data-am-widget="header" class="am-header am-header-default header">
    <div class="am-header-left am-header-nav">
        <a href="javascript:void(0);" ng-click="member()">
            <i class="am-header-icon am-icon-angle-left"></i>
        </a>
    </div>
    <h1 class="am-header-title"> <a href="#title-link" class="" style="color: #333;">泰洁干洗</a></h1>
    <div class="am-header-right am-header-nav">
        <a href="#right-link" class=""> </a>
    </div>
</header>
<div class="page zShow" id="couponDetail" refresh="0" style="background: rgb(255, 103, 103);">
    <div class="coupon-wrap">
        <img src="http://wx.qlogo.cn/mmopen/leI3C8OV8Bxia8XB41lkdMBM2n15PhflIQJgyBGvwwic3vkbXDn9cseHLMQ5uT5dUsfmricGBibpXgreWCWdatjXaPiaFDqmibsRaf/0" alt="logo" class="logo">
        <p class="name">泰洁干洗</p>
        <h1 class="title">10元抵用券</h1>
        <h2 class="sub-title">满50减10</h2>
        <p class="condition">使用条件：满<span>50.00</span>元减<span>10.00</span>元</p>
        <p class="date">可用时间：<span>2017-04-02</span>-<span>2017-09-20</span></p>
        <p class="time"><span>00:00</span>-<span>24:00</span> <i></i> <i></i></p>
        <div class="contact-wrap">
            <p class="address">地址 : <span>汾阳市</span></p>
            <p class="phone">电话 : <span>13133387544</span></p>
        </div>
    </div>
    <div class="receive-btn" onclick="receiveCoupon()" style="background: rgb(255, 214, 60);">领取</div>
</div>
</body>
</html>
