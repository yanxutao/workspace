package bean;

import java.util.List;

public class DoubleBean {
	private List<String> courseName;
	private List<Double> relative;
	
	public DoubleBean() {
	}
	
	public List<String> getCourseName() {
		return courseName;
	}
	
    public List<Double> getRelative() {
    	return relative;
    }
    
    public void setCourseName(List<String> courseName) {
    	this.courseName = courseName;
	}
    
    public void setRelative(List<Double> relative) {
    	this.relative = relative;
    }

}
