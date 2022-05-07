package com.acdirican.discoverairbnb.gui;

import javax.swing.JDialog;

class Dialog extends JDialog {
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 5400501658920649173L;
	protected String title;
	protected MainFrame  main;
	public Dialog(MainFrame main, String title, int width, int height) {
		this.main = main;
		setTitle(title);
		setSize(width, height);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		setIconImage(main.getIconImage());
	}
}
