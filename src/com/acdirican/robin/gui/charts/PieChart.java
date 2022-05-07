package com.acdirican.robin.gui.charts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;

import com.acdirican.robin.Utils;
import com.acdirican.robin.dataset.DatasetManager;
import com.acdirican.robin.dataset.entities.Dataset;
import com.acdirican.robin.dataset.entities.Fields;
import com.acdirican.robin.dataset.statistics.Statistics;



/**
 * Panel for pie chart.
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */

class PieChart extends Chart {
	/**
	 * Serial
	 */
	private static final long serialVersionUID = -4491313744805824974L;
	
	private List<Slice> slices;

	public PieChart(String title, Map<String, Double> data) {
		super(title, data);
		double sum = 0;
		for (var v : data.values()) {
			sum += v;
		}
		slices = new ArrayList<>(data.size());
		for (var e : data.entrySet()) {
			slices.add(new Slice(e.getKey(), e.getValue(), Utils.randomColor()));
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawPie((Graphics2D) g, getBounds(), slices);
	}

	void drawPie(Graphics g, Rectangle area, List<Slice> slices) {
		double total = 0.0D;
		for (Slice slice : slices) {
			total += slice.value;
		}

		double curValue = 0.0D;
		int startAngle = 0;
	
		for (Slice slice : slices) {
			
			startAngle = (int) (curValue * 360 / total);
			int arcAngle = (int) (slice.value * 360 / total);

			
			int width = (int) (area.width * 0.9);
			int height = (int) (area.height * 0.9);

			int halfWidth = (int) (width/2);
			int halfHeight = (int) (height/2);
			
			int x0 = (int) (width * 0.05);
			int y0 = (int) (height * 0.05);
			
			g.setColor(slice.color);
			g.fillArc(x0, y0, width, height, startAngle, arcAngle);
			
			curValue += slice.value;
		}
		g.setColor(Color.WHITE);
		int boxWidth = 75;
		g.fillRect(area.width-boxWidth, 5, boxWidth, 25*slices.size());
		int x = area.width-(boxWidth - 5);
		int y=25;
		for (Slice slice : slices) {
			g.setColor(slice.color);
			g.fillOval(x, y-12, 15, 15);
			g.setColor(Color.BLACK);
			g.drawString(slice.title, x+20, y);
			y+=20;
		}
		g.setFont(new Font("Arial",Font.PLAIN, 25));
		g.drawString(title, area.width/2 - title.length()*12/2, area.height-20);
			
	}

	static class Slice {
		double value;
		Color color;
		private String title;

		public Slice(String title, double value, Color color) {
			this.title = title;
			this.value = value;
			this.color = color;
		}

	}

	public static void main(String[] args) throws IOException {
		Dataset dataset = DatasetManager.create("airbnbistanbul.csv");
		Map<String, Double> roomTypeGroup = Statistics.getGroupCounts(Fields.ROOM_TYPE, dataset);
		PieChart roomTypeGroupChart = new PieChart("Room Types", roomTypeGroup);

		JFrame frame = new JFrame();
		frame.add(roomTypeGroupChart);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}

}

