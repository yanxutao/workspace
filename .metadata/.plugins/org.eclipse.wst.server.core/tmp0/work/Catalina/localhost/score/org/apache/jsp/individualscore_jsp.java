package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import bean.RankBean;
import bean.IndividualScoreBean;
import java.util.ArrayList;
import java.util.List;

public final class individualscore_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");

	IndividualScoreBean individualScore = (IndividualScoreBean)session.getAttribute("individualScore");
	List<String> courseName = individualScore.getCourseName();
	List<Double> studentScore = individualScore.getScore();
    List<RankBean> classRank = individualScore.getClassRank();
    List<RankBean> gradeRank = individualScore.getGradeRank();

      out.write("\n");
      out.write("\n");
      out.write("学生成绩(分)\n");
      out.write("<table class=\"table table-bordered\">\n");
      out.write("    <tr>\n");
      out.write("    ");

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

        for (int i = 0; i < studentScore.size(); i++) {
            
      out.write("\n");
      out.write("            <td>");
      out.print(studentScore.get(i) );
      out.write("</td>\n");
      out.write("            ");

        }
    
      out.write("\n");
      out.write("    </tr>\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("班级排名(%)\n");
      out.write("<table class=\"table table-bordered\">\n");
      out.write("    <tr>\n");
      out.write("    ");

        for (int i = 0; i < classRank.size(); i++) {
            
      out.write("\n");
      out.write("            <td id=\"class_percent[");
      out.print(i );
      out.write("][0]\">");
      out.print(classRank.get(i).getDoubleHigher() );
      out.write("</td>\n");
      out.write("            ");

        }
    
      out.write("\n");
      out.write("    </tr>   \n");
      out.write("    <tr>\n");
      out.write("    ");

        for (int i = 0; i < classRank.size(); i++) {
            
      out.write("\n");
      out.write("            <td id=\"class_percent[");
      out.print(i );
      out.write("][1]\">");
      out.print(classRank.get(i).getDoubleEqual() );
      out.write("</td>\n");
      out.write("            ");

        }
    
      out.write(" \n");
      out.write("    </tr>   \n");
      out.write("    <tr>\n");
      out.write("    ");

        for (int i = 0; i < classRank.size(); i++) {
            
      out.write("\n");
      out.write("            <td id=\"class_percent[");
      out.print(i );
      out.write("][2]\">");
      out.print(classRank.get(i).getDoubleLower() );
      out.write("</td>\n");
      out.write("            ");

        }
    
      out.write(" \n");
      out.write("    </tr>\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("年级排名(%)\n");
      out.write("<table class=\"table table-bordered\">\n");
      out.write("    <tr>\n");
      out.write("    ");

        for (int i = 0; i < gradeRank.size(); i++) {
            
      out.write("\n");
      out.write("            <td id=\"grade_percent[");
      out.print(i );
      out.write("][0]\">");
      out.print(gradeRank.get(i).getDoubleHigher() );
      out.write("</td>\n");
      out.write("            ");

        }
    
      out.write(" \n");
      out.write("    </tr>                            \n");
      out.write("    <tr>                             \n");
      out.write("    ");

        for (int i = 0; i < gradeRank.size(); i++) {
            
      out.write("\n");
      out.write("            <td id=\"grade_percent[");
      out.print(i );
      out.write("][1]\">");
      out.print(gradeRank.get(i).getDoubleEqual() );
      out.write("</td>\n");
      out.write("            ");

        }
    
      out.write(" \n");
      out.write("    </tr>                            \n");
      out.write("    <tr>                             \n");
      out.write("    ");

        for (int i = 0; i < gradeRank.size(); i++) {
            
      out.write("\n");
      out.write("            <td id=\"grade_percent[");
      out.print(i );
      out.write("][2]\">");
      out.print(gradeRank.get(i).getDoubleLower() );
      out.write("</td>\n");
      out.write("            ");

        }
    
      out.write(" \n");
      out.write("    </tr>\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("说明：在班级（年级）排名表中，第一／二／三行分别表示成绩高于／等于／低于所选学生的人数在班级（年级）中的比例；在右面的图中，红色部分表示的是所选学生的成绩在班级（年级）中所处的位置。\n");
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
