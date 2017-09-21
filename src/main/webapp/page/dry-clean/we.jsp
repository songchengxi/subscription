<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/includes/pageinclude.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
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
                function ($rootScope, $scope, $http) {

                    $scope.data = {};
                    $scope.data.openId = $("#openId").val();

                    $scope.index = function () {
                        window.location.href = "${basePath}/page/dry-clean/index.jsp";
                    };

                    $scope.member = function () {
                        window.location.href = "${basePath}/page/dry-clean/member.jsp";
                    }
                });
        /*]]>*/
    </script>
</head>
<body ng-app="app" ng-controller="MainController">
<input type="hidden" id="openId" value="${openId}">
<div data-am-widget="slider" class="am-slider am-slider-default" data-am-slider='{}'>
    <ul class="am-slides">
        <li><img src="img/banner3.png"></li>
        <li><img src="img/banner4.png"></li>
    </ul>
</div>
<div class="am-tabs qiehuan" data-am-tabs>
    <ul class="am-tabs-nav am-nav am-nav-tabs">
        <li class="am-active"><a href="#tab1">店铺介绍</a></li>
        <li><a href="#tab2">建议留言</a></li>
    </ul>
    <div class="am-tabs-bd">
        <div class="am-tab-panel am-fade am-in am-active" style="height: 500px;" id="tab1">
            泰洁干洗是一家24小时经营以港式粤菜为基础的中西融合菜，特聘香港融合菜大师主理，打造新派主题时尚餐厅。它专业的厨师团队，开发和研究新派融合菜，定期推出特色菜品，聘请专业艺术团队研发造型，让茉莉每一款菜品成为一件艺术品，让客人感受别样的饮食文化。
            <iframe src="map.html " width="100%" height="100%"></iframe>
        </div>
        <div class="am-tab-panel am-fade" id="tab2">
            <input type="text" placeholder="姓名" class="tab-input"/>
            <input type="text" placeholder="电话" class="tab-input"/>
            <textarea placeholder="建议" class="tab-input"></textarea>
            <button type="button" class="tab-btn">提交</button>
        </div>

    </div>
</div>
<div class="h50"></div>
<div data-am-widget="navbar" class="am-navbar am-cf am-navbar-default footer " id="">
    <ul class="am-navbar-nav am-cf am-avg-sm-4">
        <li>
            <a href="javascript:void(0);" ng-click="index()">
                <span><img src="img/nav1.png" style="margin-top: 5px;"/></span>
                <span class="am-navbar-label">积分兑换</span>
            </a>
        </li>
        <li>
            <a href="javascript:void(0);">
                <span><img src="img/nav2-sel.png" style="margin-top: 5px;"/></span>
                <span class="am-navbar-label" style="color: #FF6767;">我们</span>
            </a>
        </li>
        <li>
            <a href="javascript:void(0);" ng-click="member()">
                <span><img src="img/nav3.png" style="margin-top: 5px;"/></span>
                <span class="am-navbar-label">我的</span>
            </a>
        </li>
    </ul>
</div>
</body>
</html>
