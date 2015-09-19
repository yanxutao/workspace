package bean;

import java.util.List;

public class IndividualScoreBean {
	private List<String> courseName;
	private List<Double> score;
    private	List<RankBean> classRank;
    private	List<RankBean> gradeRank;
    
    public IndividualScoreBean() {
    }
    
	public List<String> getCourseName() {
		return courseName;
	}
    
    public List<Double> getScore() {
    	return score;
    }
    
    public List<RankBean> getClassRank() {
    	return classRank;
    }
    
    public List<RankBean> getGradeRank() {
    	return gradeRank;
    }
    
	public void setCourseName(List<String> courseName) {
		this.courseName = courseName;
	}
    
    public void setScore(List<Double> score) {
    	this.score = score;
    }
    
    public void setClassRank(List<RankBean> classRank) {
    	this.classRank = classRank;
    }
    
    public void setGradeRank(List<RankBean> gradeRank) {
    	this.gradeRank = gradeRank;
    }

}
