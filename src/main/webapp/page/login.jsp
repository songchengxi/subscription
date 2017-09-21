<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/includes/pageinclude.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>登陆</title>
    <link rel="stylesheet" href="${basePath}/css/login.css"/>
    <%@ include file="/includes/headinclude.jsp" %>
    <script type="text/javascript">
        /*<![CDATA[*/
        var app = angular.module('app', []);
        app.controller(
                'MainController',
                function ($rootScope, $scope, $http) {

                    $scope.data = {};

                    //登陆
                    $scope.login = function () {
                        if ($scope.data.loginNo == undefined || $scope.data.loginNo == "") {
                            $scope.data.msg = "请填写用户名";
                            return;
                        } else if ($scope.data.pwd == undefined || $scope.data.pwd == "") {
                            $scope.data.msg = "请填写密码";
                            return;
                        }

                        $http({
                            url: '${basePath}/user/login.do',
                            method: 'POST',
                            data: $scope.data
                        }).then(function (r) {
                            if (r.data.state == "OK") {
                                window.location.href = "${basePath}/page/dry-clean/index.jsp?openId=ound1xLAlhqnsO1l1qtuAu9x4bH4";
                            } else {
                                $scope.data.msg = "账号或密码错误";
                            }
                        });
                    };
                });
        /*]]>*/
    </script>
</head>

<body ng-app="app" ng-controller="MainController">
<div class="login">
    <div class="welcome"><img src="${basePath}/images/welcome.png"></div>
    <div class="login-form">
        <div class="login-inp"><label>登录</label><input ng-model="data.loginNo" type="text"></div>
        <div class="login-inp"><label>密码</label><input ng-model="data.pwd" type="password"></div>
        <div class="login-inp"><a href="javascript:void(0);" ng-click="login()">立即登录</a></div>
    </div>
    <div class="login-txt"><a href="#">立即注册</a>|<a href="#">忘记密码？</a></div>
    <div style="text-align:center;color:red;margin-top: 10px;">{{data.msg}}</div>
</div>
<div style="text-align:center;">
</div>
</body>
</html>
