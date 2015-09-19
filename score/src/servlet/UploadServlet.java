package servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import dao.ClassDao;
import dao.CourseDao;
import dao.GradeDao;
import dao.ScoreDao;
import dao.StudentDao;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String grade;
	private String classNo;
	private String fileName;
	private String filePath;
	private String msg;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SmartUpload smartUpload = new SmartUpload();
		ServletConfig config = this.getServletConfig();
		smartUpload.initialize(config, request, response);
		
		try {
			smartUpload.upload();
			File smartFile = smartUpload.getFiles().getFile(0);

			fileName = smartFile.getFileName();
			grade = "20" + fileName.substring(4, 6);
			classNo = fileName.substring(0, 8);
			
            //filePath = "/home/yanxutao/" + fileName;
            filePath = "/usr/Tomcat6/os_assignment/" + fileName;
            smartFile.saveAs(filePath, SmartUpload.SAVE_PHYSICAL);
			msg = fileName + "上传成功！";
		} catch (SmartUploadException e) {
			e.printStackTrace();
			msg = fileName + "上传失败！";
		}

		insertScore();
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath() + "/upload.jsp");
	}
	
	private void insertScore() {
		GradeDao gradeDao = new GradeDao();
		ClassDao classDao = new ClassDao();
		CourseDao courseDao = new CourseDao();
		StudentDao studentDao = new StudentDao();
		ScoreDao scoreDao = new ScoreDao();
		
		List<String> course = new ArrayList<String>();
		List<Double> score = new ArrayList<Double>();
		
		gradeDao.insertGrade(grade);
		classDao.insertClass(classNo, gradeDao.findGradeIdByGrade(grade));

		try {
			Workbook wb = WorkbookFactory.create(new FileInputStream(filePath));
			Sheet sheet = wb.getSheetAt(0);
			Row row = sheet.getRow(0);
			if (row != null) {
                int cells = row.getPhysicalNumberOfCells();
                for (int i = 1; i < cells; i++) {
                    Cell cell = row.getCell(i);
                    String c = cell.getStringCellValue();
                    courseDao.insertCourse(c);
                    course.add(c);
                }
			}
			int rows = sheet.getPhysicalNumberOfRows();
			for (int i = 1; i < rows; i++) {
				score.removeAll(score);
				row = sheet.getRow(i);
				if (row != null) {
					String studentNo = null;
					if (row.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC) {
						DecimalFormat df = new DecimalFormat("0");
						studentNo = df.format(row.getCell(0).getNumericCellValue()).toString();
					} else {
						studentNo = row.getCell(0).getStringCellValue();
					}
					int cells = row.getPhysicalNumberOfCells();
					int flag = 1;
					for (int j = 1; j < cells; j++) {
						Cell cell = row.getCell(j);
						if (cell != null) {
							Double s = cell.getNumericCellValue();
							if (s == 0) {
								flag = 0;
								break;
							} else {
								score.add(s);
							}
						}
					}
					if (flag == 1) {
						studentDao.insertStudent(studentNo, classDao.findClassIdByClassNo(classNo));
						for (int j = 0; j < course.size(); j++) {
							int courseId= courseDao.findCourseIdByCourseName(course.get(j));
							scoreDao.insertScore(studentDao.findStudentIdByStudentNo(studentNo), courseId, score.get(j));
						}
					}
				}
			}
		} catch (InvalidFormatException e1) {	
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
