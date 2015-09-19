package score;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import bean.RankBean;
import bean.SingleScoreBean;

public class Statistic {
	
	public static double pearson(List<Double> xList, List<Double> yList) {

		double xAverage = average(xList);
		double yAverage = average(yList);
		
		int size = xList.size();
		double numerator = 0;
		for (int i = 0; i < size; i++) {
			numerator += (xList.get(i) - xAverage) * (yList.get(i) - yAverage);
		}
		numerator /= size;
		
		double denominator = standardDeviation(xList) * standardDeviation(yList);
		
		return numerator / denominator;
	}
	
	public static double average(List<Double> list) {
		
		int size = list.size();
		double sum = 0;
		for (int i = 0; i < size; i++) {
			sum += list.get(i);
		}
		
		return sum / size;
	}
	
	public static double variance(List<Double> list) {
		
		double average = average(list);
		
		int size = list.size();
		double sumOfSquare = 0;
		for (int i = 0; i < size; i++) {
			sumOfSquare += Math.pow(list.get(i) - average, 2);
		}
		
		return sumOfSquare / size;
	}
	
	public static double standardDeviation(List<Double> list) {

		return Math.sqrt(variance(list));
	}
	
	public static RankBean getRank(List<Double> list, double score) {
		
		int intHigher = 0, intEqual = 0, intLower = 0;
		for (Double d : list) {
			if (d > score ) intHigher++;
			else if (d < score ) intLower++;
			else intEqual++;
		}
		
		int size = list.size();
		
		RankBean rank = new RankBean();
		rank.setIntLower(intLower);
		rank.setIntEqual(intEqual);
		rank.setIntHigher(intHigher);
		rank.setDoubleHigher(intRankToDoubleRank(intHigher, size));
		rank.setDoubleEqual(intRankToDoubleRank(intEqual, size));
		rank.setDoubleLower(intRankToDoubleRank(intLower, size));
		
		return rank;
	}
	
	public static double intRankToDoubleRank(int count, int size) {
		
		double doubleRank = count * 100.0 / size;
		BigDecimal b = new BigDecimal(doubleRank);
		doubleRank = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		return doubleRank;
	}
	
	public static SingleScoreBean getSingleScore(List<Double> score) {
		
		double averageScore = average(score);
		BigDecimal b = new BigDecimal(averageScore);
		averageScore = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		int[] count = new int[5];
		for (Double f : score) {
			if (f < 60 ) count[0]++;
			else if (f < 70 ) count[1]++;
			else if (f < 80) count[2]++;	
			else if (f < 90) count[3]++;
			else count[4]++;
		}
		
		Double[] scoreArray = new Double[1];
		scoreArray =  score.toArray(scoreArray);
		
		Arrays.sort(scoreArray);
		int length = scoreArray.length;
		double minScore = scoreArray[0];
		double maxScore = scoreArray[length-1];
		
		double middleScore;
		if (length % 2 == 0)
			middleScore = (scoreArray[length / 2 - 1] + scoreArray[length / 2]) / 2;
		else
			middleScore = scoreArray[length / 2];
		
		double[] countPercent = new double[5];
		for (int i = 0; i < 5; i++) {
			countPercent[i] = count[i] * 100.0 / length;
			BigDecimal bd = new BigDecimal(countPercent[i]);
			countPercent[i] = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			
		}
		
		SingleScoreBean singleScore = new SingleScoreBean();
		singleScore.setMinScore(minScore);
		singleScore.setMiddleScore(middleScore);
		singleScore.setMaxScore(maxScore);
		singleScore.setAverageScore(averageScore);
		singleScore.setN(length);
		singleScore.setCount(count);
		singleScore.setCountPercent(countPercent);
		
		return singleScore;
	}

}