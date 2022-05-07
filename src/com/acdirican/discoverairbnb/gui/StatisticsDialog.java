package com.acdirican.discoverairbnb.gui;

import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.acdirican.discoverairbnb.dataset.entities.Dataset;
import com.acdirican.discoverairbnb.dataset.entities.Fields;
import com.acdirican.discoverairbnb.gui.charts.Chart;
import com.acdirican.discoverairbnb.gui.charts.ChartFactory;
import com.acdirican.discoverairbnb.gui.dataviewers.DataViewer;
import com.acdirican.discoverairbnb.gui.dataviewers.DataViewerFactory;
import com.acdirican.discoverairbnb.statistics.Descriptive;
import com.acdirican.discoverairbnb.statistics.Statistics;


/**
 * Dialog to show the statistics
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
class StatisticsDialog extends Dialog{
	
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 5165215322814325671L;
	private Dataset dataset;

	public StatisticsDialog(MainFrame main, Dataset dataset) {
		super(main, "Statistics",1000, 800);
		this.dataset = dataset;

		setLayout(new GridLayout(2,1));
		JPanel thisPanel = (JPanel) this.getContentPane();
		thisPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		Descriptive priceDesc = Statistics.getDescriptive(Fields.PRICE, dataset);
		DataViewer<String, Double> priceViewer = DataViewerFactory.createSequentialDataViewer("Price", priceDesc.getMap());
		
		Descriptive reviewDesc = Statistics.getDescriptive(Fields.NUMBER_OF_REVIEWS, dataset);
		DataViewer<String, Double> reviewViewer =DataViewerFactory.createSequentialDataViewer("Numer of Reviews", reviewDesc.getMap());
		
		
		Descriptive reviewPerMonthDesc = Statistics.getDescriptive(Fields.REVIEWS_PER_MONTH, dataset);
		DataViewer<String, Double> reviewPerMonthViewer = DataViewerFactory.createSequentialDataViewer("Reviews per Month", reviewPerMonthDesc.getMap());
		
		Descriptive availabilityDesc = Statistics.getDescriptive(Fields.AVAILABILITY_365, dataset);
		DataViewer<String, Double>  availabilityViewer = DataViewerFactory.createSequentialDataViewer("Availability 365", availabilityDesc.getMap());
		
		Map<String, Double> roomTypeGroup = Statistics.getGroupCounts(Fields.ROOM_TYPE, dataset);
		Chart roomTypeGroupChart = ChartFactory.createPieChart("Room Types", roomTypeGroup);

		Map<String, Double> neigGroup = Statistics.getGroupCounts(Fields.NEIGHBOURHOOD, dataset);
		Chart neigGroupChart = ChartFactory.createBarChart("Number of Properties per Neighborhood",neigGroup);
		
		DataViewer<String, Double> priceTabularViewer = DataViewerFactory.createTabularDataViewer("Price", priceDesc.getMap());

			JPanel subPanel =  new JPanel();
			subPanel.setLayout(new GridLayout(1,2,20,20));
				JPanel subLeftPanel =  new JPanel();
				subLeftPanel.setLayout(new GridLayout(2,2,20,20));
				
				subLeftPanel.add(availabilityViewer);
				subLeftPanel.add(reviewViewer);
				subLeftPanel.add(reviewPerMonthViewer);
				subLeftPanel.add(roomTypeGroupChart);
			subPanel.add(subLeftPanel);
			subPanel.add(priceTabularViewer);
		
		JPanel footPanel =  new JPanel();
		footPanel.setLayout(new GridLayout(1,2,20,20));
		footPanel.add(subPanel);	
		
		JPanel headPanel =  new JPanel();
		headPanel.setLayout(new BoxLayout(headPanel, BoxLayout.Y_AXIS));
   		headPanel.add(neigGroupChart);

		add(headPanel);
		add(footPanel);	
		
		
		setVisible(true);
	}
}
