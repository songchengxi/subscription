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
    <style>
        .contact {
            position: fixed;
            background-color: #ff5500;
            background-repeat: no-repeat;
            background-position: center center;
            border-radius: 50%;
            box-shadow: 0 10px 10px 0 rgba(255, 188, 38, 0.2);
            height: 48px;
            width: 48px;
            text-align: center;
            font-size: 0.7rem;
            bottom: 10px;
            right: 10px;
            background-image: url(../../images/add.png);
            z-index: 100;
        }
    </style>
    <script type="text/javascript" src="js/amazeui.min.js"></script>
    <script type="text/javascript">
        /*<![CDATA[*/
        var app = angular.module('app', []);

        app.controller('MainController',
                function ($rootScope, $scope, $http) {

                    $scope.data = {};
                    $scope.goods = [];
                    $scope.users = [];

                    //初始化载入数据
                    $http({
                        url: '${basePath}/goods/findAll.do',
                        method: 'POST'
                    }).then(function (res) {
                        $scope.goods = res.data.goods;
                    });
                    $http({
                        url: '${basePath}/wxUser/findAll.do',
                        method: 'POST'
                    }).then(function (res) {
                        $scope.users = res.data.users;
                        console.log($scope.users);
                    });

                    $scope.edit = function (id) {
                        window.location.href = "${basePath}/page/dry-clean/goods-edit.jsp?id=" + id;
                    };

                    $scope.addGoods = function () {
                        window.location.href = "${basePath}/page/dry-clean/goods-edit.jsp";
                    };

                    $scope.del = function (id) {
                        var msg = "您真的确定要删除吗？\n\n请确认！";
                        if (confirm(msg) == true) {
                            $http({
                                url: '${basePath}/goods/del.do',
                                method: 'POST',
                                data: {
                                    id: id
                                }
                            }).then(function (res) {
                                //删除成功后移除数据
                                $scope.remove(res.data.id);
                            });
                        }
                    };

                    //移除
                    $scope.remove = function (id) {
                        for (var i in $scope.goods) {
                            var row = $scope.goods[i];
                            if (id == row.id) {
                                $scope.goods.splice(i, 1);
                                return;
                            }
                        }
                    };

                    $scope.addShow = function () {
                        $("#add").show();
                    };
                    $scope.addHide = function () {
                        $("#add").hide();
                    }
                });
        /*]]>*/
    </script>
</head>
<body ng-app="app" ng-controller="MainController">
<div class="am-tabs qiehuan" data-am-tabs>
    <ul class="am-tabs-nav am-nav am-nav-tabs">
        <li class="am-active"><a href="#tab1" ng-click="addShow()">商品信息</a></li>
        <li><a href="#tab2" ng-click="addHide()">会员信息</a></li>
    </ul>
    <div class="am-tabs-bd">
        <div class="am-tab-panel am-fade am-in am-active" id="tab1">
            <ul>
                <li ng-repeat="row in goods" style="background:#f5f5f5;padding: 10px 0;margin-bottom: 20px;">
                    <a class="o-con" href="" style="margin-top: 10px;">
                        <div class="o-con-img" style="height: auto;"><img src="img/detail.png"></div>
                        <div class="o-con-txt">
                            <p>{{row.name}}</p>
                            <p class="price">{{row.price}}积分</p>
                            <p>库存：<span>{{row.stock}}</span></p>
                        </div>
                        <div class="o-con-much" ng-if="row.isValid == '0'"><h4>无效</h4></div>
                        <div class="o-con-much" ng-if="row.isValid == '1'"><h4>有效</h4></div>
                    </a>
                    <hr style="margin: 10px 0;">
                    <div class="address-cz" style="padding: 0;">
                        <a style="width: 40%;" href="javascript:void(0);" ng-click="edit(row.id)"><img src="img/bj.png"
                                                                                                       style="width: 18px;">&nbsp;编辑</a>
                        <a style="width: 40%;" href="javascript:void(0);" ng-click="del(row.id)"><p style="color: red;">
                            删除</p></a>
                    </div>
                </li>
            </ul>
        </div>
        <div class="am-tab-panel am-fade" id="tab2">
            <ul>
                <li ng-repeat="user in users" style="background:#f5f5f5;padding: 10px 0;margin-bottom: 20px;">
                    <a class="o-con" href="" style="margin-top: 10px;">
                        <div class="o-con-img" style="height: auto;"><img src="{{user.headimgurl}}"></div>
                        <div class="o-con-txt">
                            <p>{{user.nickname}}</p>
                            <p class="price">{{user.score}}积分</p>
                            <%--<p>库存：<span>{{user.stock}}</span></p>--%>
                        </div>
                        <div class="o-con-much" ng-if="row.subscribe == '0'"><h4>取消关注</h4></div>
                        <div class="o-con-much" ng-if="row.subscribe == '1'"><h4>关注</h4></div>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <%--添加--%>
    <div id="add" class="contact" ng-click="addGoods()"></div>
</div>
</body>
</html>
