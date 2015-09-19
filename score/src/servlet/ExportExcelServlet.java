package servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExportExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String title = (String)session.getAttribute("caption");
		List<String> courseName = (List<String>)session.getAttribute("courseName");
		List<Double> relative = (List<Double>)session.getAttribute("relative");
		
		//String tableId = request.getParameter("tableId");
		
		//创建HSSFWorkbook对象(excel的文档对象)
		HSSFWorkbook wkb = new HSSFWorkbook();
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wkb.createSheet();
		
		HSSFRow row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue(title);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,courseName.size() - 1));
        
		HSSFRow row2 = sheet.createRow(1);
        for (int i = 0; i < courseName.size(); i++) {
        	row2.createCell(i).setCellValue(courseName.get(i));
        }

        HSSFRow row3 = sheet.createRow(2);
        for (int i = 0; i < relative.size(); i++) {
        	row3.createCell(i).setCellValue(relative.get(i));
        }
        
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		
//		response.setContentType("text/html;charset=GBK");
//		response.setContentType("application/xml");
		response.setHeader("Content-disposition", "attachment;filename=pearson.xls");
//		response.setContentType("application/msexcel");
		wkb.write(response.getOutputStream());
		wkb.close();
		
		return;
	}

}
