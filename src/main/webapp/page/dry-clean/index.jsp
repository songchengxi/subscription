<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/includes/pageinclude.jsp" %>
<%
    String openId = request.getParameter("openId");
%>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
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
    <title>首页</title>
    <script type="text/javascript">
        /*<![CDATA[*/
        var app = angular.module('app', []);

        app.controller('MainController',
                function ($rootScope, $scope, $http) {

                    $scope.data = {};
                    $scope.rows = [];

                    if ("<%=openId%>" != "null"){
                        $http({
                            url: "${basePath}/user/storage.do?openId=<%=openId%>",
                            method: "POST"
                        }).then(function () {

                        });
                    }

                    var openid = "<%=openId%>" == "null" ? $("#openId").val() : "<%=openId%>";
                    //初始化载入数据
                    $http({
                        url: '${basePath}/wxUser/findById.do',
                        method: 'POST',
                        data: {
                            openid: openid
                        }
                    }).then(function (res) {
                        $scope.data = res.data.user;
                    });

                    $http({
                        url: '${basePath}/goods/findAllValid.do',
                        method: 'POST'
                    }).then(function (res) {
                        $scope.rows = res.data.goods;
                    });

                    $scope.detail = function (id){
                        window.location.href = "${basePath}/page/dry-clean/detail.jsp?id=" + id
                    };

                    $scope.we = function () {
                        window.location.href = "${basePath}/page/dry-clean/we.jsp";
                    };

                    $scope.member = function () {
                        window.location.href = "${basePath}/page/dry-clean/member.jsp";
                    };

                    $scope.more = function (){
                        window.location.href = "${basePath}/page/dry-clean/more.jsp";
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

<div style="height: 60px;background-color: white;">
    <div class="member-pic" style="margin: 8px 0 0 8px;">
        <img style="border-radius: 50%;height: 47px;width: 47px;"
             src="{{data.headimgurl}}"/>
    </div>
    <div class="member-infor" style="margin-top: 20px;color: black;">我的积分：<em style="color: #f60;">{{data.score}}</em></div>
</div>

<div class="pet_mian" id="top">
    <div data-am-widget="slider" class="am-slider am-slider-a1" data-am-slider='{"directionNav":false}'>
        <ul class="am-slides">
            <li>
                <img src="img/111.jpg" style="height: 300px;">
                <div class="pet_slider_font">
                    <!--<span class="pet_slider_emoji">泰洁干洗</span>-->
                    <%--<span>泰洁干洗店铺风格</span>--%>
                </div>
                <%--<div class="pet_slider_shadow"></div>--%>
            </li>
            <li>
                <img src="img/222.jpg" style="height: 300px;">
                <div class="pet_slider_font">
                    <!--<span class="pet_slider_emoji">泰洁干洗</span>-->
                    <%--<span>泰洁干洗生活馆</span>--%>
                </div>
                <%--<div class="pet_slider_shadow"></div>--%>
            </li>
            <li>
                <img src="img/333.jpg" style="height: 300px;">
                <div class="pet_slider_font">
                    <!--<span class="pet_slider_emoji">泰洁干洗</span>-->
                    <%--<span>泰洁干洗设备</span>--%>
                </div>
                <%--<div class="pet_slider_shadow"></div>--%>
            </li>
        </ul>
    </div>

    <div data-am-widget="titlebar" class="am-titlebar am-titlebar-default title">
        <h2 class="am-titlebar-title "> 积分商品 </h2>
        <%--<nav class="am-titlebar-nav">--%>
            <%--<a href="javascript:void(0);" ng-click="more()">更多 &raquo;</a>--%>
        <%--</nav>--%>
    </div>
    <ul data-am-widget="gallery" class="am-gallery am-avg-sm-2 am-avg-md-3 am-avg-lg-4 am-gallery-default product">
        <li ng-repeat="row in rows">
            <div class="am-gallery-item">
                <a href="javascript:void(0);" ng-click="detail(row.id)">
                    <img src="img/p.png" style="height: 115px;"/>
                    <h3 class="am-gallery-title">{{row.name}}</h3>
                    <div class="am-gallery-desc">
                        <em>{{row.price}}积分</em><i class="am-icon-cart-plus"></i>
                    </div>
                </a>
            </div>
        </li>
    </ul>
    <div class="pet_article_dowload pet_dowload_more_top_off">
        <div class="pet_article_footer_info">Copyright(c)2017 TaiJie All Rights Reserved</div>
    </div>
    <div class="h50"></div>
    <div data-am-widget="navbar" class="am-navbar am-cf am-navbar-default footer">
        <ul class="am-navbar-nav am-cf am-avg-sm-4">
            <li>
                <a href="javascript:void(0);">
                    <span><img src="img/nav1-sel.png" style="margin-top: 5px;"/></span> <!--#FF6767--><!--#999999-->
                    <span class="am-navbar-label" style="color: #FF6767;">积分兑换</span>
                </a>
            </li>
            <li>
                <a href="javascript:void(0);" ng-click="we()">
                    <span><img src="img/nav2.png" style="margin-top: 5px;"/></span>
                    <span class="am-navbar-label">我们</span>
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
</div>
</body>
</html>
