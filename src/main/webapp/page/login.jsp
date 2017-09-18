<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/includes/pageinclude.jsp" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>登陆</title>
    <link rel="stylesheet" href="${basePath}/css/login.css" />
</head>

<body>
<div class="login">
    <div class="welcome"><img src="${basePath}/images/welcome.png"></div>
    <div class="login-form">
        <div class="login-inp"><label>登录</label><input type="text" placeholder=""></div>
        <div class="login-inp"><label>密码</label><input type="password" placeholder=""></div>
        <div class="login-inp"><a href="${basePath}/page/dry-clean/index.html" >立即登录</a></div>
    </div>
    <div class="login-txt"><a href="#">立即注册</a>|<a href="#">忘记密码？</a></div>
</div>
<div style="text-align:center;">
    <%--<p>更多模板：<a href="http://www.mycodes.net/" target="_blank">源码之家</a></p>--%>
</div>
</body>
</html>
