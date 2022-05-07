package com.acdirican.robin.gui.charts;

import java.util.Map;

/**
 * Facade to create charts
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
public class ChartFactory {
	public static final int BARCHART = 0;
	public static final int PIECHART = 1;
	
	public static Chart create(int type, String title, Map<String, Double> data) throws IllegalArgumentException{
		return switch (type) {
			case BARCHART -> createBarChart(title, data);
			case PIECHART -> createPieChart(title, data);		
			default ->
			throw new IllegalArgumentException("Unexpected chart type: " + type);
		};
	}

	public static Chart createPieChart(String title, Map<String, Double> data) {
		return new PieChart(title, data);
	}

	public static Chart createBarChart(String title, Map<String, Double> data) {
		// TODO Auto-generated method stub
		return new BarChart(title, data);
	}
}
