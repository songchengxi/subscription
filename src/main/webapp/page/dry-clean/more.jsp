<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/includes/pageinclude.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <%@ include file="/includes/headinclude.jsp" %>
    <link rel="stylesheet" href="css/amazeui.min.css">
    <link rel="stylesheet" href="css/style.css"/>
    <link rel="stylesheet" href="css/wap.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/amazeui.min.js"></script>
    <title>更多商品</title>
    <script type="text/javascript">
        /*<![CDATA[*/
        var app = angular.module('app', []);

        app.controller('MainController',
                function ($rootScope, $scope, $http) {

                    $scope.data = {};
                    $scope.rows = [];

                    //初始化载入数据
                    $http({
                        url: '${basePath}/goods/findAllValid.do',
                        method: 'POST'
                    }).then(function (res) {
                        $scope.rows = res.data.goods;
                    });

                    $scope.detail = function (id){
                        window.location.href = "${basePath}/page/dry-clean/detail.jsp?id=" + id
                    };

                    $scope.index = function () {
                        window.location.href = "${basePath}/page/dry-clean/index.jsp";
                    }
                });
        /*]]>*/
    </script>
</head>
<body ng-app="app" ng-controller="MainController">
<input type="hidden" id="openId" value="${openId}">
<div data-am-widget="gotop" class="am-gotop am-gotop-fixed">
    <a href="#top">
        <img class="am-gotop-icon-custom" src="img/goTop.png"/>
    </a>
</div>
<header data-am-widget="header" class="am-header am-header-default header">
    <div class="am-header-left am-header-nav">
        <a href="javascript:void(0);" ng-click="index()">
            <i class="am-header-icon am-icon-angle-left"></i>
        </a>
    </div>
    <h1 class="am-header-title"><a href="#title-link" class="" style="color: #333;">全部商品</a></h1>
    <div class="am-header-right am-header-nav">
        <a href="#right-link" class=""> </a>
    </div>
</header>
<div class="pet_mian" id="top">
    <ul data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-avg-md-3 am-avg-lg-4 am-gallery-default product">
        <li ng-repeat="row in rows">
            <div class="am-gallery-item">
                <a href="javascript:void(0);" ng-click="detail(row.id)">
                    <img src="img/p.png"/>
                    <h3 class="am-gallery-title">{{row.name}}</h3>
                    <div class="am-gallery-desc">
                        <em>{{row.price}}积分</em><i class="am-icon-cart-plus"></i>
                    </div>
                </a>
            </div>
        </li>
    </ul>
</div>
</body>
</html>
