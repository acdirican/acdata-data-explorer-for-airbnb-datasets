package com.acdirican.robin.gui.dataviewers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.acdirican.robin.Utils;


/**
 * Panel to show a group of data in tablular form.
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
class TabularDataViewer<K,V> extends DataViewer<K, V>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1659832596103885043L;

	public TabularDataViewer(String dataTitle, Map<K,V> data) {
		this.dataTitle = dataTitle;
		
		this.data = data;
		

		setSize(300, 300);
		setBorder(BorderFactory.createEmptyBorder(20, 20,20,20));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setLayout(new BorderLayout());
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		titleLabel =  new JLabel(dataTitle, SwingConstants.CENTER);
		titleLabel.setVerticalAlignment(SwingConstants.CENTER);
//		title.setBackground(Color.ORANGE);
//		title.setOpaque(true);
		
		titleLabel.setFont(new Font("Arial",Font.BOLD, 20));
	    titleLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	    
			
	    int c=2;
	    int r = (int) Math.ceil(data.size()/2.0);
		JPanel panel =  new JPanel();
		panel.setLayout(new GridLayout(r, c, 5, 5));
		for(var e : data.entrySet()) {
			panel.add(new LabeledValue<>(e.getKey().toString(), Utils.toStringTwoZeroAfterPointIfNumber(e.getValue()), 200));
		}


		mainPanel.add(new JScrollPane(panel));
			
		add(titleLabel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		
		setVisible(true);

	}
	
	
	
	public static void main(String[] args) {
		Map<String, Integer> data =  new HashMap<>();
		data.put("A", 1);
		data.put("B", 2);
		data.put("C", 3);

		JFrame frame = new JFrame();
	    frame.getContentPane().add(new TabularDataViewer<String, Integer>("Test", data));
	    frame.setSize(300, 500);
	    frame.setVisible(true);
	}
	
}
