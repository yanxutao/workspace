<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<br>
<%=session.getAttribute("caption") %>
<table class="table table-bordered" id="relative">
    <tr>
    <%
        @SuppressWarnings("unchecked")
        List<String> courseName = (List<String>)session.getAttribute("courseName");
        for (int i = 0; i < courseName.size(); i++) {
            %>
            <td id="c<%=i + 1%>"><%=courseName.get(i) %></td>
            <%
        }
    %>
    </tr>
    <tr>
    <%
        @SuppressWarnings("unchecked")
        List<Double> relative = (List<Double>)session.getAttribute("relative");
        for (int i = 0; i < relative.size(); i++) {
            %>
            <td id="r<%=i + 1%>"><%=relative.get(i) %></td>
            <%
        }
    %>
    </tr>
</table>

<form action="ExportExcelServlet" method="get">
    <button class="btn btn-sm btn-primary" type="submit">保存为Excel</button>
</form>