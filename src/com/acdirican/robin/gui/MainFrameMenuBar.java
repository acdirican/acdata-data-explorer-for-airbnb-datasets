package com.acdirican.robin.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainFrameMenuBar extends JMenuBar {
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 6378516518505017413L;
	
	private MainFrame main;

	public MainFrameMenuBar(MainFrame mainFrame) {
		this.main = mainFrame;
		

		// File Menu, F - Mnemonic
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		this.add(fileMenu);
		
		//Edit Menu, E - Mnemonic
		JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic(KeyEvent.VK_E);
		this.add(editMenu);
				
		// Help->About, A - Mnemonic
		JMenuItem findMenuItem = new JMenuItem("Find", KeyEvent.VK_F);
		findMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!main.isSet()) {
					JOptionPane.showMessageDialog(main, "Please load a dataset first.");
					return;
				}
				new FindDialog(main);
			}
		});
		editMenu.add(findMenuItem);
		
		// Help Menu, F - Mnemonic
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		this.add(helpMenu);
		
		// Help->About, A - Mnemonic
		JMenuItem aboutMenuItem = new JMenuItem("About", KeyEvent.VK_A);
		aboutMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AboutDialog(main);
			}
		});
		helpMenu.add(aboutMenuItem);
			
		// File->Open, O - Mnemonic
		JMenuItem newMenuItem = new JMenuItem("Open from File System", KeyEvent.VK_O);
		newMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileopen = new JFileChooser();
				File f = null;
				try {
					f = new File(new File(".").getCanonicalPath());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				fileopen.setCurrentDirectory(f);

				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
				fileopen.addChoosableFileFilter(filter);

				int ret = fileopen.showDialog(null, "Open file");

				if (ret == JFileChooser.APPROVE_OPTION) {
					File file = fileopen.getSelectedFile();
					main.setTable(file.getAbsolutePath());
				}

			}
		});
		fileMenu.add(newMenuItem);

		// File->Open Web, O - Mnemonic
				JMenuItem webMenuItem = new JMenuItem("Open from WWW", KeyEvent.VK_W);
				webMenuItem.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						new WebSourceDialog(main);
					}
				});
				fileMenu.add(webMenuItem);
				
		// File->Save, S - Mnemonic
		JMenuItem saveMenuItem = new JMenuItem("Save as CSV", KeyEvent.VK_S);
		saveMenuItem.addActionListener(createFileSaveListener(MainFrame.CSV));
		fileMenu.add(saveMenuItem);

		// File->Save as JSON, J - Mnemonic
		JMenuItem saveJSONMenuItem = new JMenuItem("Save as JSON", KeyEvent.VK_J);
		saveJSONMenuItem.addActionListener(createFileSaveListener(MainFrame.JSON));
		fileMenu.add(saveJSONMenuItem);
		
		
		// File->Exit, E - Mnemonic
		JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_E);
		exitMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fileMenu.add(exitMenuItem);
	}
	
	private ActionListener createFileSaveListener(int saveType) {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!main.isSet()) {
					JOptionPane.showMessageDialog(main, "Please load a dataset first.");
					return;
				}
								
				JFileChooser filesave  = selectAFile("csv");
				int ret = filesave  .showDialog(null, "Save file");
				
				if (ret == JFileChooser.APPROVE_OPTION) {
					File file = filesave.getSelectedFile();
					try {
						main.saveTable(file, saveType);
						JOptionPane.showMessageDialog(main, "Current view saved.");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}	

		};
	}

	private JFileChooser selectAFile(String ext) {
		JFileChooser filesave = new JFileChooser();
		File f = null;
		try {
			f = new File(new File(".").getCanonicalPath());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		filesave.setCurrentDirectory(f);

		FileNameExtensionFilter filter = new FileNameExtensionFilter(ext + " files", "ext");
		filesave.addChoosableFileFilter(filter);
		return filesave;
	}
}
