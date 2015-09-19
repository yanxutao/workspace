<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>登录</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/navbar.css" rel="stylesheet">
    <link href="css/signin.css" rel="stylesheet">
    <link href="score.css" rel="stylesheet"/>
      
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="score.js"></script>
  </head>

  <body>
    <div class="container">
      <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header"><a class="navbar-brand" href="single.jsp">学生成绩统计分析系统</a></div>
        </div><!--/.container-fluid -->
      </nav>
      <form class="form-signin" action="LoginServlet" method="post">
        <label for="inputUsername" class="sr-only">用户名</label>
        <input type="text" name="username" id="inputUsername" class="form-control" placeholder="用户名" required autofocus>
        <br>
        
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="密码" required>
        ${loginMsg}<br><br>
        
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
      </form>
   </div><!-- /container -->
  </body>
</html>