package com.acdirican.discoverairbnb.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.acdirican.discoverairbnb.dataset.entities.Property;
import com.acdirican.discoverairbnb.parser.Parser;

/**
 * Dialog to search the dataset.
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
class WebSourceDialog extends Dialog{

	/**
	 * Searial
	 */
	private static final long serialVersionUID = 2845414759452911231L;

	public WebSourceDialog(MainFrame main) {
		super(main,"Web Resources", 700, 200);

		setLayout(new BorderLayout());

		JLabel urlLabel =  new JLabel("URL:");
		JTextField urlText =  new JTextField();
		urlText.setPreferredSize(new Dimension(600, 30));
		
		JPanel panel =  new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(50,10,10,10));
		panel.add(urlLabel);
		panel.add(urlText);
		
		JButton downloadButton =  new JButton("Download and Parse");
		JDialog parent = this;
		ActionListener searchAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Property> properties= null;
				try {
					properties = Parser.parseFromURL(urlText.getText(), 80);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(parent, "Cannot open url: " + urlText.getText());
					return;
				}
				
				if (properties == null) {
					JOptionPane.showMessageDialog(parent, "Problem with data source: " + urlText.getText());
					return;
				}
				main.setTable(properties);
				parent.dispose();
			}
		};
		
		downloadButton.addActionListener(searchAction);
		urlText.addActionListener(searchAction);
		add(panel,BorderLayout.CENTER);
		add(downloadButton, BorderLayout.SOUTH);
		
	
		setVisible(true);
	}
}
