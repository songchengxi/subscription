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
    <script type="text/javascript" src="js/amazeui.min.js"></script>
    <script type="text/javascript">
        /*<![CDATA[*/
        var app = angular.module('app', []);

        app.controller('MainController',
                function ($rootScope, $scope, $location, $http) {

                    $scope.data = {};
                    $scope.data.openId = $("#openId").val();

                    //初始化载入数据
                    $http({
                        url: '${basePath}/wxUser/findById.do',
                        method: 'POST',
                        data: {
                            openid: $scope.data.openId
                        }
                    }).then(function (res) {
                        $scope.data = res.data.user;
                    });

                    $scope.index = function () {
                        window.location.href = "${basePath}/page/dry-clean/index.jsp";
                    };

                    $scope.we = function () {
                        window.location.href = "${basePath}/page/dry-clean/we.jsp";
                    };

                    $scope.address = function () {
                        window.location.href = "${basePath}/page/dry-clean/address.jsp";
                    };

                    $scope.order = function () {
                        window.location.href = "${basePath}/page/dry-clean/order.jsp";
                    };

                    $scope.yhq = function () {
                        window.location.href = "${basePath}/page/dry-clean/yhq.jsp";
                    }
                });
        /*]]>*/
    </script>
</head>
<body ng-app="app" ng-controller="MainController">
<input type="hidden" id="openId" value="${openId}">
<div class="member">
    <div class="member-pic">
        <img style="border-radius: 50%;"
             src="{{data.headimgurl}}"/>
    </div>
    <div class="member-infor">{{data.nickname}}</div>
</div>
<ul class="member-nav">
    <%--<li><a href="javascript:void(0);" ng-click="address()"><i class="am-icon-map-marker"></i><span>收货地址</span></a></li>--%>
    <li><a href="javascript:void(0);" ng-click="order()"><i class="am-icon-newspaper-o"></i><span>我的订单</span></a></li>
    <%--<li><a href=""><i class="am-icon-cart-arrow-down"></i><span>购物车</span></a></li>--%>
    <li><a href=""><i class="am-icon-bell-o"></i><span>系统通知</span></a></li>
    <%--<li><a href=""><i class="am-icon-credit-card"></i><span>会员卡</span></a></li>--%>
    <%--<li><a href="javascript:void(0);" ng-click="yhq()"><i class="am-icon-cc-mastercard"></i><span>优惠券</span></a></li>--%>
    <li><a href=""><i class="am-icon-dollar"></i><span>我的积分</span><em style="color: #f60;padding-left: 20px;">{{data.score}}</em></a></li>
</ul>
<ul class="member-nav mt">
    <li><a href=""><i class="am-icon-phone"></i>联系我们</a></li>
</ul>
<div class="h50"></div>
<div data-am-widget="navbar" class="am-navbar am-cf am-navbar-default footer" id="">
    <ul class="am-navbar-nav am-cf am-avg-sm-4">
        <li>
            <a href="javascript:void(0);" ng-click="index()">
                <span><img src="img/nav1.png" style="margin-top: 5px;"/></span>
                <span class="am-navbar-label">积分兑换</span>
            </a>
        </li>
        <li>
            <a href="javascript:void(0);" ng-click="we()">
                <span><img src="img/nav2.png" style="margin-top: 5px;"/></span>
                <span class="am-navbar-label">我们</span>
            </a>
        </li>
        <li>
            <a href="javascript:void(0);">
                <span><img src="img/nav3-sel.png" style="margin-top: 5px;"/></span>
                <span class="am-navbar-label" style="color: #FF6767;">我的</span>
            </a>
        </li>
    </ul>
</div>
</body>
</html>
