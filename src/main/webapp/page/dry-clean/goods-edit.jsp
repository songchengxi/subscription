<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/includes/pageinclude.jsp" %>
<%
    String id = request.getParameter("id");
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>泰洁干洗</title>
    <meta name="description" content="泰洁干洗"/>
    <meta name="keywords" content="泰洁干洗"/>
    <%--移动浏览器的显示视窗优化和缩放禁止--%>
    <meta name="viewport" content="width=device-width, user-scalable=no">
    <%--屏蔽浏览器自动识别电话号码添加链接--%>
    <meta name="format-detection" content="telephone=no">
    <%@ include file="/includes/headinclude.jsp" %>
    <link rel="stylesheet" href="css/amazeui.min.css"/>
    <link rel="stylesheet" href="css/style.css"/>
    <script type="text/javascript" src="js/amazeui.min.js"></script>
    <!-- 引入 Bootstrap -->
    <link href="../../libs/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- 包括所有已编译的插件 -->
    <script src="../../libs/bootstrap-3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        /*<![CDATA[*/
        var app = angular.module('app', []);

        app.controller('MainController',
                function ($rootScope, $scope, $http) {

                    $scope.data = {};

                    $scope.data.id = "<%=id%>";

                    //初始化载入数据
                    if ("<%=id%>" != "null") {
                        $http({
                            url: '${basePath}/goods/findById.do',
                            method: 'POST',
                            data: {
                                id: $scope.data.id
                            }
                        }).then(function (res) {
                            $scope.data = res.data.goods;
                        });
                    }

                    $scope.save = function () {
                        $http({
                            url: '${basePath}/goods/save.do',
                            method: 'POST',
                            data: $scope.data
                        }).then(function (res) {
                            $scope.data = res.data.goods;
                            window.location.href = "${basePath}/page/dry-clean/manage.jsp";
                        });
                    };

                    $scope.manage = function (){
                        window.location.href = "${basePath}/page/dry-clean/manage.jsp";
                    };
                });
        /*]]>*/
    </script>
</head>
<body ng-app="app" ng-controller="MainController">
<header data-am-widget="header" class="am-header am-header-default header">
    <div class="am-header-left am-header-nav">
        <a href="javascript:void(0);" ng-click="manage()">
            <i class="am-header-icon am-icon-angle-left"></i>
        </a>
    </div>
    <h1 class="am-header-title"><a href="#title-link" class="" style="color: #333;">全部商品</a></h1>
    <div class="am-header-right am-header-nav">
        <a href="#right-link" class=""> </a>
    </div>
</header>
<form class="form-horizontal" role="form">
    <input type="hidden" name="id" value="{{data.id}}">
    <div class="form-group">
        <label for="name" class="col-xs-4 col-md-6 control-label">名称</label>
        <div class="col-xs-8 col-md-6">
            <input type="text" class="form-control" name="name" id="name" ng-model="data.name" placeholder="请输入名称">
        </div>
    </div>
    <div class="form-group">
        <label for="stock" class="col-xs-4 col-md-6 control-label">库存</label>
        <div class="col-xs-8 col-md-6">
            <input type="text" class="form-control" name="stock" id="stock" ng-model="data.stock" placeholder="请输入库存">
        </div>
    </div>
    <div class="form-group">
        <label for="price" class="col-xs-4 col-md-6 control-label">购买所需积分</label>
        <div class="col-xs-8 col-md-6">
            <input type="text" class="form-control" name="price" id="price" ng-model="data.price" placeholder="请输入积分">
        </div>
    </div>
    <div class="form-group">
        <label for="isValid" class="col-xs-4 col-md-6 control-label">是否生效</label>
        <div class="col-xs-8 col-md-6">
            <label class="radio-inline">
                <input type="radio" name="isValid" id="isValid" value="1" ng-model="data.isValid" checked>有效
            </label>
            <label class="radio-inline">
                <input type="radio" name="isValid" value="0" ng-model="data.isValid">无效
            </label>
        </div>
    </div>
    <div class="form-group">
        <label for="describe" class="col-xs-4 col-md-6 control-label">描述</label>
        <div class="col-xs-8 col-md-6">
            <textarea class="form-control" id="describe" ng-model="data.describe" name="describe" rows="3"></textarea>
        </div>
    </div>
    <div class="form-group">
        <div class="col-xs-12 col-md-12">
            <button type="submit" class="tab-btn" ng-click="save()">保存</button>
        </div>
    </div>
</form>
</body>
</html>
