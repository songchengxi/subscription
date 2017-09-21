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
<body ng-app="app" ng-controller="MainController">
<input type="hidden" id="openId" value="${openId}">
<header data-am-widget="header" class="am-header am-header-default header">
    <div class="am-header-left am-header-nav">
        <a href="javascript:void(0);" ng-click="member()">
            <i class="am-header-icon am-icon-angle-left"></i>
        </a>
    </div>
    <h1 class="am-header-title"><a href="#title-link" class="" style="color: #333;">收货地址</a></h1>
    <div class="am-header-right am-header-nav">
        <a href="#right-link" class=""> </a>
    </div>
</header>
<ul class="address-list">
    <li class="curr">
        <p>收货人：安女士&nbsp;&nbsp;153********</p>
        <p class="order-add1">收货地址：河北省廊坊市 青云谱区解放西路258号河北省廊坊市 青云谱区解放西路258号</p>
        <hr>
        <div class="address-cz">
            <label class="am-radio am-warning">
                <input type="radio" name="radio3" value="" data-am-ucheck="" checked="" class="am-ucheck-radio"><span
                    class="am-ucheck-icons"><i class="am-icon-unchecked"></i><i class="am-icon-checked"></i></span> 设为默认
            </label>
            <a href=""><img src="img/bj.png" style="width: 18px;">&nbsp;编辑</a>
            <a href="">删除</a>
        </div>
    </li>
    <li>
        <p>收货人：安女士&nbsp;&nbsp;182********</p>
        <p class="order-add1">收货地址：河北省廊坊市 青云谱区解放西路258号河北省廊坊市 青云谱区解放西路258号</p>
        <hr>
        <div class="address-cz">
            <label class="am-radio am-warning">
                <input type="radio" name="radio3" value="" data-am-ucheck="" class="am-ucheck-radio"><span
                    class="am-ucheck-icons"><i class="am-icon-unchecked"></i><i class="am-icon-checked"></i></span> 设为默认
            </label>
            <a href=""><img src="img/bj.png" style="width: 18px;">&nbsp;编辑</a>
            <a href="">删除</a>
        </div>
    </li>
    <li>
        <p>收货人：安女士&nbsp;&nbsp;182********</p>
        <p class="order-add1">收货地址：河北省廊坊市 青云谱区解放西路258号河北省廊坊市 青云谱区解放西路258号</p>
        <hr>
        <div class="address-cz">
            <label class="am-radio am-warning">
                <input type="radio" name="radio3" value="" data-am-ucheck="" class="am-ucheck-radio"><span
                    class="am-ucheck-icons"><i class="am-icon-unchecked"></i><i class="am-icon-checked"></i></span> 设为默认
            </label>
            <a href=""><img src="img/bj.png" style="width: 18px;">&nbsp;编辑</a>
            <a href="">删除</a>
        </div>
    </li>
    <li>
        <p>收货人：安女士&nbsp;&nbsp;182********</p>
        <p class="order-add1">收货地址：河北省廊坊市 青云谱区解放西路258号河北省廊坊市 青云谱区解放西路258号</p>
        <hr>
        <div class="address-cz">
            <label class="am-radio am-warning">
                <input type="radio" name="radio3" value="" data-am-ucheck="" class="am-ucheck-radio"><span
                    class="am-ucheck-icons"><i class="am-icon-unchecked"></i><i class="am-icon-checked"></i></span> 设为默认
            </label>
            <a href=""><img src="img/bj.png" style="width: 18px;">&nbsp;编辑</a>
            <a href="">删除</a>
        </div>
    </li>
</ul>
</body>
</html>
