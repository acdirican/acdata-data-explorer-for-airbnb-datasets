package com.acdirican.robin.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;

import com.acdirican.robin.dataset.DatasetManager;
import com.acdirican.robin.dataset.entities.Dataset;
import com.acdirican.robin.dataset.entities.Fields;
import com.acdirican.robin.dataset.entities.Property;
import com.acdirican.robin.dataset.statistics.Descriptive;
import com.acdirican.robin.dataset.statistics.Statistics;

/**
 * Main frame of the software
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
public class MainFrame extends JFrame {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 520734926472913727L;

	public static final int CSV = 0;
	public static final int JSON = 1;
	
	private Dataset dataset;
	private Dataset current;
	private JToolBar toolbar;
	private JScrollPane tablePane;
	private JTable table;
	
	private JComboBox<String> neighComboBox;
	private JComboBox<String> roomTypeComboBox;
	private JComboBox<String> lowerPriceComboBox;
	private JComboBox<String> higherPriceComboBox;

	private Image icon;

	public MainFrame() {
		this.icon = new ImageIcon("icon.png").getImage();
		
		setTitle("Robin - Data Explorer for Airbnb Datasets by acdirican");
		setSize(900, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(icon);
		
		setLayout(new BorderLayout());
		
		createMenuBar();
		createToolBar();
		createTablePane();

		// setTable("airbnbistanbul.csv");

	
	}
	
	public void init() {
		setVisible(true);
	}
	private void createTablePane() {
		this.tablePane = new JScrollPane();
		tablePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(tablePane, BorderLayout.CENTER);
	}

	private void createToolBar() {
		this.toolbar = new JToolBar();
		// toolbar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		toolbar.setRollover(true);
		toolbar.setFloatable(true);
		toolbar.setMargin(new Insets(20, 2, 20, 2));
		MainFrame parent = this;

		JButton statisticsButton = new JButton("Show Statistics");
		statisticsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (current == null) {
					JOptionPane.showMessageDialog(parent, "Please load a dataset first.");
					return;
				}
				new StatisticsDialog(parent, current);

			}
		});
		toolbar.add(statisticsButton);

		toolbar.add(new JLabel("      Room Type:"));
		roomTypeComboBox = new JComboBox<String>(new String[] { "" });
		toolbar.add(roomTypeComboBox);

		toolbar.add(new JLabel("      Neighborhood:"));
		neighComboBox = new JComboBox<String>(new String[] { "" });
		toolbar.add(neighComboBox);

		toolbar.add(new JLabel("      Price Range:"));
		lowerPriceComboBox = new JComboBox<String>(new String[] { "Lower Price" });
		toolbar.add(lowerPriceComboBox);
		toolbar.add(new JLabel("  -  "));
		higherPriceComboBox = new JComboBox<String>(new String[] { "Higher Price" });
		toolbar.add(higherPriceComboBox);

		Container contentPane = this.getContentPane();
		contentPane.add(toolbar, BorderLayout.NORTH);
		JTextArea textArea = new JTextArea();
		JScrollPane pane = new JScrollPane(textArea);
		contentPane.add(pane, BorderLayout.CENTER);
	}

	private void fillTable() {

		Vector<String> cols = new Vector<>(Dataset.summaryTitles());
		Vector<Vector<String>> rows = new Vector<>();

		for (Property property : current.properties()) {
			rows.add(property.summary());
		}
		this.table = new JTable(rows, cols);
		MainFrame parent = this;
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				
				if (e.getClickCount() == 2) {
					if (current == null) {
						JOptionPane.showMessageDialog(parent, "Please load a dataset first.");
						return;
					}
					
					new PropertyDialog(parent, current, table.getSelectedRow());
				}
			}

		});
		this.tablePane.setViewportView(table);
	}

	void setTable(String fileName) {

		try {
			this.dataset = DatasetManager.create(fileName);
			this.current = dataset;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		setTable();
	}

	private void setTable() {

		Descriptive priceDesc = Statistics.getDescriptive(Fields.PRICE, current);
		System.out.println(priceDesc);
		List<Integer> prices = new ArrayList<>();
		int size = (int) (Math.ceil(priceDesc.getMax() / 50)) * 10;
		for (int i = 0; i <= 5; i++) {
			int v = i * size;
			prices.add(v);
		}

		this.lowerPriceComboBox.removeAllItems();
		this.lowerPriceComboBox.addItem("All");
		for (Integer p : prices) {
			this.lowerPriceComboBox.addItem(p.toString());
		}

		this.higherPriceComboBox.removeAllItems();
		this.higherPriceComboBox.addItem("All");
		for (Integer p : prices) {
			this.higherPriceComboBox.addItem(p.toString());
		}

		ActionListener filter = createFilter();

		this.lowerPriceComboBox.addActionListener(filter);
		this.higherPriceComboBox.addActionListener(filter);

		this.neighComboBox.removeAllItems();
		this.neighComboBox.addItem("All");
		for (String neigh : dataset.neighborhoods()) {
			this.neighComboBox.addItem(neigh);
		}

		this.neighComboBox.addActionListener(filter);

		this.roomTypeComboBox.removeAllItems();
		this.roomTypeComboBox.addItem("All");
		for (String type : dataset.roomTypes()) {
			this.roomTypeComboBox.addItem(type);
		}
		this.roomTypeComboBox.addActionListener(filter);

		fillTable();

	}

	private ActionListener createFilter() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Check if the change comes from user click or addItem
				if (e.getModifiers() == 0) {
					return;
				}

				current = dataset;
				if (neighComboBox.getItemCount() != 0) {
					if (!neighComboBox.getSelectedItem().equals("All")) {
						current = DatasetManager.neighborhoodFilter(current,
								neighComboBox.getSelectedItem().toString());
					}
				}

				if (higherPriceComboBox.getItemCount() != 0) {
					if (!(higherPriceComboBox.getSelectedItem().equals("All")
							|| lowerPriceComboBox.getSelectedItem().equals("All"))) {
						double lowerPrice = Double.valueOf(lowerPriceComboBox.getSelectedItem().toString());
						double higherPrice = Double.valueOf(higherPriceComboBox.getSelectedItem().toString());
						current = DatasetManager.priceRangeFilter(current, lowerPrice, higherPrice);
					}
				}

				if (roomTypeComboBox.getItemCount() != 0) {
					if (!roomTypeComboBox.getSelectedItem().equals("All")) {
						String type = roomTypeComboBox.getSelectedItem().toString();
						current = DatasetManager.roomTypeFilter(current, type);
					}
				}

				fillTable();
			}
		};
	}

	private void createMenuBar() {
		setJMenuBar(new MainFrameMenuBar(this));
	}

	void saveTable(File file, int saveType) throws IOException {
		List<String> lines = new ArrayList<>();
		if (saveType == CSV) {
			lines.add(Dataset.titles());
		}
		else {
			lines.add("[");
		}
		for (Property property : current.properties()) {
			if (saveType == CSV) {
				lines.add(property.toString());
			}
			else if (saveType == JSON){
				lines.add(property.toJSON() + ",");
			}
			else {
				JOptionPane.showConfirmDialog(this, "Unkown file save type");
				return;
			}
		}
		file.createNewFile();
	
		if (saveType == JSON){
			lines.remove(lines.size()-1);
			lines.add(current.properties().get(current.properties().size()-1).toJSON());
			lines.add("]");
		}
		
		Files.write(file.toPath(), lines, Charset.forName("UTF-8"), StandardOpenOption.TRUNCATE_EXISTING);
		
	}

	public int search(String text, int startRow) {
		table.setRowSelectionInterval(0, 0);
		table.changeSelection(0, 0, rootPaneCheckingEnabled, rootPaneCheckingEnabled);
		for (int i = startRow; i < table.getRowCount(); i++) {// For each row
			for (int j = 0; j < table.getColumnCount(); j++) {// For each column in that row
				if (table.getModel().getValueAt(i, j).toString().contains(text)) {// Search the model
					System.out.println(table.getModel().getValueAt(i, j));// Print if found string
					table.setRowSelectionInterval(i, i);
					table.changeSelection(i, i, rootPaneCheckingEnabled, rootPaneCheckingEnabled);
					return i;
				}
			} // For loop inner
		} // For loop outer
		return -1;
	}

	public void setTable(List<Property> properties) {
		if (properties == null) {
			return;
		}
		this.dataset = DatasetManager.create(properties);
		this.current = dataset;
		setTable();
	}

	public boolean isSet() {
		return current != null;
	}
}
