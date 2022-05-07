package com.acdirican.discoverairbnb.statistics;


import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The Descriptive class calculates and presents the descriptive statistics of a double data collection.
 *  
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
public class Descriptive {
	private double min;
	private double max;
	private double range;
	private double sum;
	private double avg;
	private double var;
	private double std;
	private int count;
		
	public Descriptive(Collection<Double> values) {
		count = values.size();
		sum = 0;
		min = Double.MAX_VALUE;
		max = Double.MIN_VALUE;
		for(Double v : values) {
			sum += v;
			if (v<min) {
				min = v;
			}
			if (v>max) {
				max=v;
			}
		}
		range = max - min;
		avg = sum / values.size();
		
		var = 0;
		for(Double v : values) {
			var += Math.pow(v-avg, 2);
		}
		var /= values.size();
		std = Math.sqrt(var);
	}

	public static Descriptive get(List<Double> data) {
		return new Descriptive(data);
	}
	
	
	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

	public double getSum() {
		return sum;
	}

	public double getAvg() {
		return avg;
	}

	public double getVar() {
		return var;
	}

	public double getStd() {
		return std;
	}

	public double getRange() {
		return range;
	}

	public int getCount() {
		return count;
	}

	public Map<String, Double> getMap() {
		Map<String, Double> map =  new LinkedHashMap<>();
		map.put("Count", (double) count);
		map.put("Sum", sum);
		map.put("Average", avg);
		map.put("Variance", var);
		map.put("Standart Deviation", std);
		map.put("Maxium", max);
		map.put("Minimum", min);
		map.put("Range", range);
		return map;
	}

	@Override
	public String toString() {
		return "Descriptive [min=" + min + ", max=" + max + ", range=" + range + ", sum=" + sum + ", avg=" + avg
				+ ", var=" + var + ", std=" + std + ", count=" + count + "]";
	}
	
	
	
	

}
