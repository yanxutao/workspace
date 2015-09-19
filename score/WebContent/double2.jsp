<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<br>
<table style="display: none">
    <tr>
        <td id="cn1"><%=session.getAttribute("c1") %></td>
        <td id="cn2"><%=session.getAttribute("c2") %></td>
        <td id="r"><%=session.getAttribute("r") %></td>
    </tr>
</table>
<table style="display: none">
    <%
        @SuppressWarnings("unchecked")
        List<Double> x = (List<Double>)session.getAttribute("x");
        @SuppressWarnings("unchecked")
        List<Double> y = (List<Double>)session.getAttribute("y");
        for (int i = 0; i < x.size(); i++) {
            %>
            <tr>
                <td id="x<%=i + 1%>"><%=x.get(i) %></td>
                <td id="y<%=i + 1%>"><%=y.get(i) %></td>
            </tr>
            <%
        }
    %>
</table>