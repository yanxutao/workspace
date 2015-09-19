<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <title>相关性分析</title>
        
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/navbar.css" rel="stylesheet">
        <link href="score.css" rel="stylesheet"/>
        
        <!-- DataTables CSS -->
		<link href="css/jquery.dataTables.min.css">
		  
		<!-- DataTables -->
		<script src="js/jquery.dataTables.min.js"></script>
        
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="score.js"></script>
        <script>
            function update() {
                var p1 = document.getElementById("pors").value;
                var p2 = document.getElementById("grade").value;
                var p3 = document.getElementById("class").value;
                var p4 = document.getElementById("course1").value;
                var p5 = document.getElementById("course2").value;
                ajax("score", "/score/DoubleServlet?p1=" + p1 + "&p2=" + p2 + "&p3=" + p3 + "&p4=" + p4 + "&p5=" + p5);
                if (p5 == "") {
                	drawRelative();
                }
                else {
                	//alert("Begin");
                	//document.getElementById('myCanvas').style.height = 400 + "px";
                	//document.getElementById('myCanvas').style.width = 400 + "px";
                	drawDots();
                }
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
    
	        <div class="jumbotron">
                <div class="row">
	                <div class="col-md-7">
	                    <div class="row">
                            <div class="col-md-4">
                                相关系数类型<br>
                                <select id="pors" onchange="update()">
                                    <option selected>Pearson</option>
                                    <option>Spearman</option>
                                </select><br><br>
                            </div>	                       
	                    </div>  
	                    <div class="row">                                                                          
		                    <div id="grade_div" class="col-md-3">
		                        年级<br>
		                        <select id="grade" onchange="fillClass(),update()" size=7></select>
		                    </div>
		                    
		                    <div id="class_div" class="col-md-3">
		                        班级<br>
		                        <select id="class" onchange="update()" size=7></select>
		                    </div>
		                    <div id="course_div" class="col-md-3">
		                        科目1<br>
		                        <select id="course1" onchange="update()" size=7></select>
		                    </div>
		                    <div id="course_div" class="col-md-3">
		                        科目2<br>
		                        <select id="course2" onchange="update()" size=7></select>
		                    </div>
	                    </div>
	                    
		                <div class="row">
	                        <div id="score" class="clear col-md-12"></div>
		                </div>
                        
	                </div>
                    <div class="col-md-5">                        
                        <br>
                        <canvas id="myCanvas" width="400" height="300" style="border:1px solid #c3c3c3"></canvas>
                    </div>	                
                </div>
               
                
	        </div>    
        </div> <!-- /container -->
        
        <script>
            fillCourse("course1");
            document.getElementById("c7").selected = true;
            fillCourse("course2");
            fillGrade();
            update();
        </script>
    </body>
    
</html>

		

