<%@page import="bean.RankBean"%>
<%@page import="bean.IndividualScoreBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	IndividualScoreBean individualScore = (IndividualScoreBean)session.getAttribute("individualScore");
	List<String> courseName = individualScore.getCourseName();
	List<Double> studentScore = individualScore.getScore();
    List<RankBean> classRank = individualScore.getClassRank();
    List<RankBean> gradeRank = individualScore.getGradeRank();
%>

学生成绩(分)
<table class="table table-bordered">
    <tr>
    <%
        for (int i = 0; i < courseName.size(); i++) {
            %>
            <td id="c<%=i + 1%>"><%=courseName.get(i) %></td>
            <%
        }
    %>
    </tr>
    <tr>
    <%
        for (int i = 0; i < studentScore.size(); i++) {
            %>
            <td><%=studentScore.get(i) %></td>
            <%
        }
    %>
    </tr>
</table>

班级排名(%)
<table class="table table-bordered">
    <tr>
    <%
        for (int i = 0; i < classRank.size(); i++) {
            %>
            <td id="class_percent[<%=i %>][0]"><%=classRank.get(i).getDoubleHigher() %></td>
            <%
        }
    %>
    </tr>   
    <tr>
    <%
        for (int i = 0; i < classRank.size(); i++) {
            %>
            <td id="class_percent[<%=i %>][1]"><%=classRank.get(i).getDoubleEqual() %></td>
            <%
        }
    %> 
    </tr>   
    <tr>
    <%
        for (int i = 0; i < classRank.size(); i++) {
            %>
            <td id="class_percent[<%=i %>][2]"><%=classRank.get(i).getDoubleLower() %></td>
            <%
        }
    %> 
    </tr>
</table>

年级排名(%)
<table class="table table-bordered">
    <tr>
    <%
        for (int i = 0; i < gradeRank.size(); i++) {
            %>
            <td id="grade_percent[<%=i %>][0]"><%=gradeRank.get(i).getDoubleHigher() %></td>
            <%
        }
    %> 
    </tr>                            
    <tr>                             
    <%
        for (int i = 0; i < gradeRank.size(); i++) {
            %>
            <td id="grade_percent[<%=i %>][1]"><%=gradeRank.get(i).getDoubleEqual() %></td>
            <%
        }
    %> 
    </tr>                            
    <tr>                             
    <%
        for (int i = 0; i < gradeRank.size(); i++) {
            %>
            <td id="grade_percent[<%=i %>][2]"><%=gradeRank.get(i).getDoubleLower() %></td>
            <%
        }
    %> 
    </tr>
</table>

说明：在班级（年级）排名表中，第一／二／三行分别表示成绩高于／等于／低于所选学生的人数在班级（年级）中的比例；在右面的图中，红色部分表示的是所选学生的成绩在班级（年级）中所处的位置。
