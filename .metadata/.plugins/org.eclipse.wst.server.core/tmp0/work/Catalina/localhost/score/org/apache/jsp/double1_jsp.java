package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import java.util.List;

public final class double1_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<br>\n");
      out.print(session.getAttribute("caption") );
      out.write("\n");
      out.write("<table class=\"table table-bordered\" id=\"relative\">\n");
      out.write("    <tr>\n");
      out.write("    ");

        @SuppressWarnings("unchecked")
        List<String> courseName = (List<String>)session.getAttribute("courseName");
        for (int i = 0; i < courseName.size(); i++) {
            
      out.write("\n");
      out.write("            <td id=\"c");
      out.print(i + 1);
      out.write('"');
      out.write('>');
      out.print(courseName.get(i) );
      out.write("</td>\n");
      out.write("            ");

        }
    
      out.write("\n");
      out.write("    </tr>\n");
      out.write("    <tr>\n");
      out.write("    ");

        @SuppressWarnings("unchecked")
        List<Double> relative = (List<Double>)session.getAttribute("relative");
        for (int i = 0; i < relative.size(); i++) {
            
      out.write("\n");
      out.write("            <td id=\"r");
      out.print(i + 1);
      out.write('"');
      out.write('>');
      out.print(relative.get(i) );
      out.write("</td>\n");
      out.write("            ");

        }
    
      out.write("\n");
      out.write("    </tr>\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("<form action=\"ExportExcelServlet\" method=\"get\">\n");
      out.write("    <button class=\"btn btn-sm btn-primary\" type=\"submit\">保存为Excel</button>\n");
      out.write("</form>");
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
