package com.acdirican.robin.gui.dataviewers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.acdirican.robin.Utils;
import com.acdirican.robin.dataset.statistics.Descriptive;

/**
 * Panel to show a group of data sequentially.
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
class SequentialDataViewer<K,V> extends DataViewer<K, V>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6771215022185899278L;
	
	private ArrayList< Map.Entry<K,V>> dataList;
	private ListIterator<Map.Entry<K,V>> it;
	private Map.Entry<K,V> current = null;
	
	private JButton prev;
	private JButton next;

	private JLabel key;
	private JLabel value;

	public SequentialDataViewer(String dataTitle, Map<K,V> data) {
		this.dataTitle = dataTitle;
		
		this.data = data;
		this.dataList = new ArrayList<>();
		
		for(var e : data.entrySet()) {
			dataList.add(e);
		}
		this.it = dataList.listIterator();

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
	    
		prev =  new JButton("<");
		prev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (it.hasPrevious()) {
					current = it.previous();
					displayData();
				}
			}
		});
		
		next =  new JButton(">");
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (it.hasNext()) {
					current = it.next();
					displayData();
				}
			}	
		});
		

		JPanel panel =  new JPanel();
		panel.setLayout(new GridLayout(2,1));
		
		key =  new JLabel("Key", SwingConstants.CENTER);
		key.setFont(new Font("Arial",Font.BOLD, 15));

		
		
		value = new JLabel("Value", SwingConstants.CENTER);
		value.setFont(new Font("Arial",Font.BOLD, 15));

		key.setVerticalAlignment(SwingConstants.CENTER);
		value.setVerticalAlignment(SwingConstants.CENTER);
		
		panel.add(value, 0,0);
		panel.add(key, 1,0);
				
		mainPanel.add(titleLabel, BorderLayout.NORTH);
		mainPanel.add(prev, BorderLayout.WEST);
		mainPanel.add(next, BorderLayout.EAST);
		mainPanel.add(panel, BorderLayout.CENTER);
		
		add(mainPanel, BorderLayout.CENTER);
		
		setVisible(true);
		
		if (it.hasNext()) {
			current = it.next();
			displayData();
		}
	}
	
	private void displayData() {
		key.setText(current.getKey().toString());
		value.setText(Utils.toStringTwoZeroAfterPointIfNumber(current.getValue()));
		
	}
	
	public static void main(String[] args) {
		Map<String, Integer> data =  new HashMap<>();
		data.put("A", 1);
		data.put("B", 2);
		data.put("C", 3);
		new SequentialDataViewer<String, Integer>("Test", data);
	}
	
}
