package com.acdirican.robin.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * About dialog to describe the software.
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
class AboutDialog extends Dialog {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1941437021480027001L;

	public AboutDialog(MainFrame main) {
		super(main, "About",600, 600);
		
		setLayout(new BorderLayout());

		JLabel textLabel = new JLabel("<html><body><center>ACD Data Explorer for Airbn Datasets v1.0 <br>"
				+ "Copyright (c) 2022 Ahmet Cengizhan Dirican <br>" + "All rights reserved <br> "
				+ "<a href='https://github.com/acdirican'>https://github.com/acdirican</a></body></html>");
		textLabel.setHorizontalAlignment(JLabel.CENTER);
		textLabel.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel descLabel = new JLabel(
				"<html><body><p>This software was coded fully in Java using Swing and Collections. I hope you enjoy it. </p></body></html>");
		descLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		ImageIcon abo2utImage = new ImageIcon("about.png");
		JLabel pictureLabel = new JLabel(abo2utImage);

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));

		panel.add(pictureLabel);
		panel.add(textLabel);
		panel.add(descLabel);
		
		JButton closeButton = new JButton("Close");
		JDialog parent = this;
		ActionListener searchAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.dispose();
			}
		};

		closeButton.addActionListener(searchAction);
		add(panel, BorderLayout.CENTER);
		add(closeButton, BorderLayout.SOUTH);

		setVisible(true);
	}
}
