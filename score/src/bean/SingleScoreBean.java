package bean;

public class SingleScoreBean {
    private double minScore;
    private double middleScore;
    private double maxScore;
    private double averageScore;
    private int n;
    private int[] count = new int[5];
    private double[] countPercent = new double[5];

    public SingleScoreBean() {
    }

    public double getMinScore() {
        return minScore;
    }

    public double getMiddleScore() {
        return middleScore;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public int getN() {
        return n;
    }

    public int[] getCount() {
        return count;
    }

    public double[] getCountPercent() {
    	return countPercent;
    }
    
    public void setMinScore(double minScore) {
        this.minScore = minScore;
    }

    public void setMiddleScore(double middleScore) {
        this.middleScore = middleScore;
    }

    public void setMaxScore(double maxScore) {
        this.maxScore = maxScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setCount(int[] count) {
        this.count = count;
    }
    
    public void setCountPercent(double[] countPercent) {
    	this.countPercent = countPercent;
    }

}