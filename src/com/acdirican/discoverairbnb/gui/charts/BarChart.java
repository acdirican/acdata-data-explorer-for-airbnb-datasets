package com.acdirican.discoverairbnb.gui.charts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import com.acdirican.discoverairbnb.Utils;

/**
 * Bar chart panel.
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
class BarChart extends Chart {
	/**
	 * Serial
	 */
	private static final long serialVersionUID = -2721473049916079127L;
	private Color barColor = Color.BLUE;
	private Color lineColor = Color.BLACK;
	

	public BarChart(String title, Map<String, Double> data) {
		super(title, data);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (data.size() == 0) {
			return;
		}
		
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		
		for (Double value : data.values()) {
			if (min > value)  {
				min = value;
			}
			if (max < value) {
				max = value;
			}
		}

		Dimension dim = getSize();
		int clientWidth = dim.width;
		int clientHeight = dim.height;
		
		int barWidth = clientWidth / data.size();

		//Fonts
		Font titleFont = new Font("Arial", Font.BOLD, 20);
		FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
		
		Font labelFont = new Font("Arial", Font.PLAIN, 10);
		FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

		int titleWidth = titleFontMetrics.stringWidth(title);
		int y = titleFontMetrics.getAscent();
		int x = (clientWidth - titleWidth) / 2;
		g.setFont(titleFont);
		g.drawString(title, x, y);

		int top = titleFontMetrics.getHeight();
		int bottom = labelFontMetrics.getHeight();
		if (max == min) {
			return;
		}
		
		
		double scale = (clientHeight - top - bottom) / (max - min);
	
		y = clientHeight - labelFontMetrics.getDescent();
		g.setFont(labelFont);

		int i = 0;
		for (var e : data.entrySet()) {
			
			int valueX = i * barWidth + 1;
			int valueY = top;
			
			int height = (int) (e.getValue() * scale);
			
			if (e.getValue() >= 0) {
				valueY += (int) ((max - e.getValue()) * scale);
			}
			else {
				valueY += (int) (max * scale);
				height = -height;
			}

			g.setColor(barColor);
			g.fillRect(valueX, valueY, barWidth - 2, height);
			
			g.setColor(lineColor);
			g.drawRect(valueX, valueY, barWidth - 2, height);

			int labelWidth = labelFontMetrics.stringWidth(e.getKey());
			
			x = i * barWidth + (barWidth - labelWidth) / 2;
			// g.drawString(names[i], x, valueY-10);
			drawRotate((Graphics2D) g, x + labelWidth / 2, valueY - 10, -90,e.getKey() + " (" + Utils.toStringTwoZeroAfterPointIfNumber(e.getValue()) + ")");
			
			i++;
			
		}
	}

	/**
	 * Draw rotated text
	 * @param g2d Graphics2D 
	 * @param x
	 * @param y
	 * @param angle
	 * @param text
	 */
	public static void drawRotate(Graphics2D g2d, double x, double y, int angle, String text) {
		g2d.translate((float) x, (float) y);
		g2d.rotate(Math.toRadians(angle));
		g2d.drawString(text, 0, 0);
		g2d.rotate(-Math.toRadians(angle));
		g2d.translate(-(float) x, -(float) y);
	}

	public static void main(String[] argv) {
		JFrame f = new JFrame();
		f.setSize(400, 300);
		double[] values = new double[3];
		String[] names = new String[3];
		values[0] = 1;
		names[0] = "Item 1";

		values[1] = 2;
		names[1] = "Item 2";

		values[2] = 4;
		names[2] = "Item 3";

		Map<String, Double> map = new HashMap<>();
		for (int i = 0; i < 3; i++) {
			map.put(names[i], values[i]);
		}
		f.getContentPane().add(new BarChart("title", map));

		WindowListener wndCloser = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		f.addWindowListener(wndCloser);
		f.setVisible(true);
	}
}
