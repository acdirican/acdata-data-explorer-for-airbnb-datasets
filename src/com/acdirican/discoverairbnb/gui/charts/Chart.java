package com.acdirican.discoverairbnb.gui.charts;

import java.util.Map;


import javax.swing.JPanel;
/**
 * A chart
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
public abstract class Chart extends JPanel{
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 7174781993184809117L;
	
	protected String title;
	protected Map<String, Double> data;

	public Chart(String title, Map<String, Double> data) {
		 this.title = title;
		 this.data = data;
				 
	 }
}
