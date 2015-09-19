<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <title>上传成绩</title>
        
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
                            <li  class="active"><a href="upload.jsp">上传成绩</a></li>
                            <li><a href="individual.jsp">个人成绩分析</a></li>
                            <li><a href="single.jsp">单科分析</a></li>
                            <li><a href="double.jsp">相关性分析</a></li>
                            <li><a href="associations.jsp">关联规则挖掘</a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="LogoutServlet">退出</a></li>
                        </ul>
                    </div><!--/.nav-collapse -->
                </div><!--/.container-fluid -->
            </nav>
        </div> <!-- /container -->
        
        <div class="container">
            <div class="jumbotron">
                多次上传同一个班级的成绩以最后一次上传为准<br><br>
                <form action="UploadServlet" method="post" enctype="multipart/form-data">            
	                请选择一个以班号命名的Excel文件，例如：10010601.xls
	                <div class="row">
                        <div class="col-md-4">
                            <input type="file" name="file" id="file" accept=".xls" onchange="check()" class="filestyle" data-icon="false" data-size="sm" data-buttonName="btn-primary" data-buttonBefore="true" data-buttonText="选择文件">
                        </div>
                    </div><br>
	                <button class="btn btn-sm btn-primary" type="submit" id="upload" disabled>&nbsp;&nbsp;&nbsp;&nbsp;上传&nbsp;&nbsp;&nbsp;&nbsp;</button>
	                ${msg}
                </form>
                <br>
                <form action="UpdateRankServlet" method="get">
                    <button class="btn btn-sm btn-primary" type="submit">计算/更新年级排名</button>
                </form>
            </div>
        </div>

    </body>
    
</html>


		

