<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/includes/pageinclude.jsp" %>
<%
    String id = request.getParameter("id");
%>
<html>
<head>
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
                    $scope.rows = [];

                    $scope.data.id = <%=id%>;

                    //初始化载入数据
                    $http({
                        url: '${basePath}/goods/findById.do',
                        method: 'POST',
                        data: {
                            id: $scope.data.id
                        }
                    }).then(function (res) {
                        $scope.data = res.data.goods;
                    });

                    $scope.index = function () {
                        window.location.href = "${basePath}/page/dry-clean/index.jsp";
                    };
                });
        /*]]>*/
    </script>
</head>
<body ng-app="app" ng-controller="MainController">
<input type="hidden" id="openId" value="${openId}">
<header data-am-widget="header" class="am-header am-header-default header">
    <div class="am-header-left am-header-nav" style="top: 11px;">
        <a href="javascript:void(0);" ng-click="index()">
            <i class="am-header-icon am-icon-angle-left"></i>
        </a>
    </div>
    <h1 class="am-header-title"><a href="#title-link" class="" style="color: #333;">详细信息</a></h1>
    <div class="am-header-right am-header-nav">
        <a href="#right-link" class=""> </a>
    </div>
</header>

<div data-am-widget="slider" class="am-slider am-slider-default" data-am-slider='{}'>
    <ul class="am-slides">
        <li><img src="img/detail.png"></li>
        <li><img src="img/detail.png"></li>
    </ul>
</div>
<div class="detail">
    <h2>{{data.name}}</h2>
    <div class="price">
        <b>{{data.price}}积分</b><span></span>
    </div>
    <div class="kucun">
        <p>库存：{{data.stock}}</p>
        <p>运费：免运费</p>
    </div>
</div>
<div class="comment">
    <h2>宝贝评价（0）</h2>
    <ul>
        <li><a hhref="">有图（0）</a></li>
        <li><a hhref="">好评（0）</a></li>
        <li><a hhref="">中评（0）</a></li>
        <li><a hhref="">差评（0）</a></li>
    </ul>
</div>
<div class="detail-con">
    <p>{{data.describe}}</p>
    <img src="img/banner.jpg"/>
</div>
<div class="h50"></div>
<ul class="fixed-btn">
    <li><a href="" class="current">立即购买</a></li>
    <li><a href="">加入购物车</a></li>
</ul>
</body>
</html>
