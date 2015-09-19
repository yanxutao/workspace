package servlet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;
import dao.RankDao;
import dao.ScoreDao;
import dao.StudentDao;
import entity.Rank;
import entity.Score;

public class AprioriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private char score(double s) {
		char c;
		
		if (s >= 80)
			c = 'A';
		else if (s >= 70)
			c = 'B';
		else if (s >= 60)
			c = 'C';
		else
			c = 'D';
		
		return c;
	}
	
	private char rank(double s) {
		char c;
		
		if (s <= 20)
			c = 'A';
		else if (s <= 40)
			c = 'B';
		else if (s <= 60)
			c = 'C';
		else if (s <= 80)
			c = 'D';
		else
			c = 'E';
		
		return c;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// load data
		String filename = "/home/yanxutao/data.csv";
		//String filename = "/usr/Tomcat6/os_assignment/data.csv";
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));;
		out.println("C1,C2,C3,C4,C5,C6,C7");
		
		String table = request.getParameter("table");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.print("科目&nbsp; C1&nbsp;高等数学&nbsp;&nbsp; C2&nbsp;外语&nbsp;&nbsp; C3&nbsp;离散数学&nbsp;&nbsp;");
		pw.print("C4&nbsp;C语言&nbsp;&nbsp; C5&nbsp;数据结构&nbsp;&nbsp; C6&nbsp;组成原理&nbsp;&nbsp; C7&nbsp;操作系统<br><br>");
		
		StudentDao studentDao = new StudentDao();
		List<Integer> studentIds = studentDao.findAllStudentIds();
		
		if (table.equals("score")) {
			ScoreDao scoreDao = new ScoreDao();
			for (Integer studentId : studentIds) {
				List<Score> s =  scoreDao.findScoreByStudentId(studentId);
				for (int i = 0; i < s.size() - 1; i++) {
					out.print(score(s.get(i).getScore()));
					out.print(",");
				}
				out.print(score(s.get(s.size() - 1).getScore()));
				out.print("\n");
			}
			pw.print("成绩(分)&nbsp;A&nbsp;[100, 80]&nbsp;&nbsp;B&nbsp;(80, 70]&nbsp;&nbsp;C&nbsp;(70, 60]&nbsp;&nbsp;D&nbsp;(60, 0]<br><br>");
		} else {
			RankDao rankDao = new RankDao();
			for (Integer studentId : studentIds) {
				List<Rank> r =  rankDao.findRankByStudentId(studentId);
				for (int i = 0; i < r.size() - 1; i++) {
					out.print(rank(r.get(i).getRank()));
					out.print(",");
				}
				out.print(rank(r.get(r.size() - 1).getRank()));
				out.print("\n");
			}
			pw.print("年级排名(%)&nbsp;A&nbsp;[0, 20]&nbsp;&nbsp;B&nbsp;(20, 40]&nbsp;&nbsp;C&nbsp;(40, 60]&nbsp;&nbsp;D&nbsp;(60, 80]&nbsp;&nbsp;E&nbsp;(80, 100]<br><br>");
		}

		out.close();
		
		Instances data = null;

		try {
			data = DataSource.read(filename);
			data.setClassIndex(data.numAttributes() - 1);

			// build associator
			Apriori apriori = new Apriori();
			String[] options = null;
			
			String num = request.getParameter("num");
			String min = request.getParameter("min");
			String upper = request.getParameter("upper");
			String lower = request.getParameter("lower");
			String delta = request.getParameter("delta");
			
			options = Utils.splitOptions("-N "+ num +" -C "+ min +" -D "+ delta +" -U "+ upper +" -M "+ lower +" -A");
			apriori.setOptions(options);

			apriori.buildAssociations(data);
			
//			FastVector[] rules = apriori.getAllTheRules();
//			FastVector.FastVectorEnumeration(rules);
//			for (int i = 0; i < rules.length; i++) {
//				pw.println(rules.toString());
//			}
			
			String res = String.valueOf(apriori);

			int i;
			for (i = 0; i < res.length(); i++) {
				if (res.charAt(i) == 'B') {
					break;
				}
			}
			
			for (; i < res.length(); i++) {
				char c = res.charAt(i);
				if (c == ' ') {
					pw.print("&nbsp;");
				} else if (res.charAt(i) == '\n') {
					pw.print("<br>");	
				} else {
					pw.print(c);	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
