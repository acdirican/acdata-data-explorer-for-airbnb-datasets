package com.acdirican.robin.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Dialog to search the dataset.
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
class FindDialog extends Dialog{

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 8892556550346757558L;

	public FindDialog(MainFrame main) {
		super(main, "Find", 400, 200);
		
		setLayout(new BorderLayout());

		JLabel searchLabel =  new JLabel("Search:");
		
		JTextField searchText =  new JTextField();
		searchText.setPreferredSize(new Dimension(200, 30));
		
		JPanel panel =  new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(50,10,10,10));
		panel.add(searchLabel);
		panel.add(searchText);
		
		JButton searchButton =  new JButton("Find Next");
		Component parent = this;
		ActionListener searchAction = new ActionListener() {
			int i = 0;
			@Override
			public void actionPerformed(ActionEvent e) {
				i = main.search(searchText.getText(), i);
				if (i==-1) {
					JOptionPane.showMessageDialog(parent, "Cannot find " + searchText.getText());
				}
			    i++;
			}
		};
		
		searchButton.addActionListener(searchAction);
		searchText.addActionListener(searchAction);
		add(panel,BorderLayout.CENTER);
		add(searchButton, BorderLayout.SOUTH);
	
		setVisible(true);
	}
}
