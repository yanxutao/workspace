package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class single_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"zh-CN\">\n");
      out.write("  <head>\n");
      out.write("  \t<meta charset=\"utf-8\">\n");
      out.write("      <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("      \n");
      out.write("      <title>单科分析</title>\n");
      out.write("      \n");
      out.write("      <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("      <link href=\"css/navbar.css\" rel=\"stylesheet\">\n");
      out.write("      <link href=\"score.css\" rel=\"stylesheet\"/>\n");
      out.write("      \n");
      out.write("      <script src=\"js/jquery.min.js\"></script>\n");
      out.write("      <script src=\"js/bootstrap.min.js\"></script>\n");
      out.write("      <script src=\"score.js\"></script>\n");
      out.write("      <script>\n");
      out.write("        function update() {\n");
      out.write("          var courseId = document.getElementById(\"course\").value;\n");
      out.write("          var grade = document.getElementById(\"grade\").value;\n");
      out.write("          var classNo = document.getElementById(\"class\").value;\n");
      out.write("          ajax(\"score\", \"/score/SingleScoreServlet?courseId=\" + courseId + \"&classNo=\" + classNo + \"&grade=\" + grade);\n");
      out.write("          drawSingle();\n");
      out.write("        }\n");
      out.write("      </script>\n");
      out.write("  </head>\n");
      out.write("\n");
      out.write("  <body>\n");
      out.write("  \n");
      out.write("  \t");

  	String username = (String)session.getAttribute("username");
  	if (username == null) {
  		response.sendRedirect(request.getContextPath() + "/login.jsp");
  	}
  	
      out.write("\n");
      out.write("  \n");
      out.write("    <div class=\"container\">\n");
      out.write("      <!-- Static navbar -->\n");
      out.write("      <nav class=\"navbar navbar-default\">\n");
      out.write("  \t    <div class=\"container-fluid\">\n");
      out.write("\t      <div class=\"navbar-header\"><a class=\"navbar-brand\" href=\"single.jsp\">学生成绩统计分析系统</a></div>\n");
      out.write("\t      <div id=\"navbar\" class=\"navbar-collapse collapse\">\n");
      out.write("\t        <ul class=\"nav navbar-nav\">\n");
      out.write("\t          <li><a href=\"upload.jsp\">上传成绩</a></li>\n");
      out.write("\t          <li><a href=\"individual.jsp\">个人成绩分析</a></li>\n");
      out.write("\t          <li class=\"active\"><a href=\"single.jsp\">单科分析</a></li>\n");
      out.write("              <li><a href=\"double.jsp\">相关性分析</a></li>\n");
      out.write("              <li><a href=\"associations.jsp\">关联规则挖掘</a></li>\n");
      out.write("\t        </ul>\n");
      out.write("            <ul class=\"nav navbar-nav navbar-right\">\n");
      out.write("              <li><a href=\"LogoutServlet\">退出</a></li>\n");
      out.write("            </ul>\n");
      out.write("\t      </div><!--/.nav-collapse -->\n");
      out.write("  \t    </div><!--/.container-fluid -->\n");
      out.write("      </nav>\n");
      out.write("  \n");
      out.write("  \t  <div class=\"jumbotron\">\n");
      out.write("  \t    <div class=\"row\">\n");
      out.write("  \t      <div class=\"col-xs-7\">\n");
      out.write("  \t\t    <div id=\"select\">\n");
      out.write("  \t\t        <div class=\"row\">\n");
      out.write("                  <div id=\"course_div\" class=\"col-md-4\">\n");
      out.write("                    科目<br>\n");
      out.write("                    <select id=\"course\" onchange=\"update()\" size=7></select>\n");
      out.write("                  </div>\n");
      out.write("  \t              <div id=\"grade_div\" class=\"col-md-4\">\n");
      out.write("  \t                年级<br>\n");
      out.write("  \t                <select id=\"grade\" onchange=\"fillClass(),update()\" size=7></select>\n");
      out.write("  \t              </div>\n");
      out.write("  \t              \n");
      out.write("  \t              <div id=\"class_div\" class=\"col-md-4\">\n");
      out.write("  \t                班级<br> \n");
      out.write("  \t                <select id=\"class\" onchange=\"update()\" size=7></select>\n");
      out.write("  \t              </div>\n");
      out.write("  \t            </div>\n");
      out.write("             </div><br>\n");
      out.write("             <div id=\"score\" class=\"clear\"></div>\n");
      out.write("  \t      </div>\n");
      out.write("  \t      <div class=\"col-xs-5\">\n");
      out.write("  \t        <br>\n");
      out.write("            <canvas id=\"myCanvas\" width=\"400\" height=\"300\" style=\"border:1px solid #c3c3c3\"></canvas>\n");
      out.write("  \t      </div>\n");
      out.write("  \t    </div>\n");
      out.write("  \t  </div>\n");
      out.write("    </div> <!-- /container -->\n");
      out.write("    \n");
      out.write("    <script>\n");
      out.write("      fillCourse(\"course\");\n");
      out.write("      document.getElementById(\"c7\").selected = true;\n");
      out.write("      fillGrade();\n");
      out.write("      update();\n");
      out.write("    </script>\n");
      out.write("  </body>\n");
      out.write("\t\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else log(t.getMessage(), t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
