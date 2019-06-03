<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>叩丁狼客户关系管理系统-登录</title>
  <link rel="stylesheet" href="/js/bootstrap/css/bootstrap.css">
  <link rel="stylesheet" href="/js/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="/js/Ionicons/css/ionicons.min.css">
  <link rel="stylesheet" href="/js/adminlte/css/AdminLTE.min.css">
  <link rel="stylesheet" href="/js/adminlte/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="/js/adminlte/css/fonts.googleapis.com.css">

  <script src="/js/jquery/jquery.min.js"></script>
  <script src="/js/bootstrap/js/bootstrap.js"></script>
  <script src="/js/adminlte/js/adminlte.min.js"></script>
  <script src="/js/plugins/twbsPagination/jquery.twbsPagination.min.js"></script>
  <script src="/js/system/commonAll.js"></script>


</head>
<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <a href="http://www.wolfcode.cn"><b>叩丁狼</b>CRM</a>
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">请输入账号密码</p>
    <span>${errorMsg}</span>
    <form  method="post" id="loginForm" action="/login.do">
      <div class="form-group has-feedback">
        <input type="text" class="form-control" placeholder="请输入账号" name="username" value="admin">
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password" class="form-control" placeholder="请输入密码" name="password" value="1">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="row">

        <div class="col-xs-12">
          <button type="submit" class="btn btn-primary btn-block btn-flat submitBtn">登录</button>
        </div>
      </div>
    </form>


  </div>
</div>
</body>
</html>
