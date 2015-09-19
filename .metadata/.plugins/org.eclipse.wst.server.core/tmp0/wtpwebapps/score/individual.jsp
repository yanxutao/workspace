<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <title>个人成绩分析</title>
        
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/navbar.css" rel="stylesheet">
        <link href="score.css" rel="stylesheet"/>
        
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="score.js"></script>
        <script>
	        function update() {
	            var studentNo = document.getElementById("student").value;
	            var classNo = document.getElementById("class").value;
	            var grade = document.getElementById("grade").value;
	            ajax("score", "/score/IndividualScoreServlet?studentNo=" + studentNo + "&classNo=" + classNo + "&grade=" + grade);
	            draw();
	        }
        </script>        
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
                            <li class="active"><a href="individual.jsp">个人成绩分析</a></li>
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
    
	        <div class="jumbotron">
	            <div class="row">
	                <div class="col-xs-7">
	                    <div id="select">
	                        <div class="row">
		                      <div id="grade_div" class="col-xs-6 col-md-4">
		                       年级<br>
		                       <select id="grade" onchange="fillClass()" size=7></select>        
		                      </div>
		                      
		                      <div id="class_div" class="col-xs-6 col-md-4">
		                       班级<br> 
		                       <select id="class" onchange="fillStudent()" size=7></select>
		                      </div>
		                      
		                      <div id="student_div" class="col-xs-6 col-md-4">
		                       学生<br>
		                       <select id="student" onchange="update()" size=7></select>
		                      </div>                                                      
	                        </div>
	                    </div><br>
			            
			            <div id="score" class="clear">
							学生成绩(分)
							<table class="table table-bordered">
							    <tr><td>&nbsp;</td></tr>
							    <tr><td>&nbsp;</td></tr>
							</table>
							
							班级排名(%)&nbsp;班级人数:0
							<table class="table table-bordered">
							    <tr><td>&nbsp;</td></tr>
							    <tr><td>&nbsp;</td></tr>
							    <tr><td>&nbsp;</td></tr>
							</table>
							
							年级排名(%)&nbsp;年级人数:0
							<table class="table table-bordered">
							    <tr><td>&nbsp;</td></tr>                            
							    <tr><td>&nbsp;</td></tr>                            
							    <tr><td>&nbsp;</td></tr>
							</table>			            
			            </div>
	                </div>
	                <div class="col-xs-5">
	                    <br>
			            <canvas id="classRankCanvas" width="400" height="260" style="border:1px solid #c3c3c3"></canvas><br>
			            <br>
			            <canvas id="gradeRankCanvas" width="400" height="260" style="border:1px solid #c3c3c3"></canvas>
	                </div>
	            </div>
	
	        </div>
        </div> <!-- /container -->
        
        <script>
            fillGrade();
        </script>        
    </body>
    
</html>
