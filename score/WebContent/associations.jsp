<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <title>关联规则挖掘</title>
        
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/navbar.css" rel="stylesheet">
        <link href="score.css" rel="stylesheet"/>

        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap-filestyle.min.js"></script>
        <script src="score.js"></script>
    </head>

    <body>
        <%
        String username = (String)session.getAttribute("username");
        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
        %>
        <div class="container">
            <!-- Static navbar -->
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header"><a class="navbar-brand" href="single.jsp">学生成绩统计分析系统</a></div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li><a href="upload.jsp">上传成绩</a></li>
                            <li><a href="individual.jsp">个人成绩分析</a></li>
                            <li><a href="single.jsp">单科分析</a></li>
                            <li><a href="double.jsp">相关性分析</a></li>
                            <li class="active"><a href="associations.jsp">关联规则挖掘</a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="LogoutServlet">退出</a></li>
                        </ul>
                    </div><!--/.nav-collapse -->
                </div><!--/.container-fluid -->
            </nav>
            
            <div class="jumbotron">
                <div class="row">
                    <div class="col-xs-2">选择数据</div>
                    <div class="col-xs-10"><input type="radio" name="table">成绩&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="table" checked>年级排名</div>
                </div><br>
                <div class="row">
                    <div class="col-xs-2"></div>
                    <div class="col-xs-10"></div>
                </div><br>
                <div class="row">
                    <div class="col-xs-2">规则数目</div>
                    <div class="col-xs-10"><input type="text" id="numRules" value="10" size="15"></div>
                </div><br>
                <div class="row">
                    <div class="col-xs-2">最小置信度</div>
                    <div class="col-xs-10"><input type="text" id="minConfidence" value="0.3" size="15"></div>
                </div><br>
                <div class="row">
                    <div class="col-xs-2">支持度上界</div>
                    <div class="col-xs-10"><input type="text" id="upperBoundMinSupport" value="1" size="15"></div>
                </div><br>
                <div class="row">
                    <div class="col-xs-2">支持度下界</div>
                    <div class="col-xs-10"><input type="text" id="lowerBoundMinSupport" value="0.05" size="15"></div>
                </div><br>
                <div class="row">
                    <div class="col-xs-2">支持度的改变量</div>
                    <div class="col-xs-10"><input type="text" id="delta" value="0.05" size="15"></div>
                </div><br>
                <button type="button" class="btn btn-primary" onclick="associationMining()">确定</button><br><br><br>

                <div id="bestRules"></div>
            </div>
        </div> <!-- /container -->

    </body>
    
</html>
