package com.acdirican.robin.gui.dataviewers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Panel to show single datum with its label
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
public class LabeledValue<T> extends JPanel{
	/**
	 * Serial
	 */
	private static final long serialVersionUID = -8073891263501611612L;
	private final JLabel label;
	private T value;
	private final JLabel valueLabel;
	
	public LabeledValue(String labelText, T value, int width) {
		this.value = value;
		setSize(width, 30);
		// setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setLayout(new FlowLayout(FlowLayout.LEFT));
		//setLayout(new GridLayout(1,3));
		//setLayout (new BoxLayout (this, BoxLayout.X_AXIS));  
		
		this.label =  new JLabel(labelText +": ");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setPreferredSize(new Dimension(5*width/8, 30));
		
		this.valueLabel = new JLabel(String.valueOf(value));
		valueLabel.setOpaque(true);
		valueLabel.setBackground(Color.WHITE);
		valueLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		valueLabel.setPreferredSize(new Dimension(3*width/8, 30));
		
		add(this.label);
		add(this.valueLabel);
	}
	
	public LabeledValue(String labelText, T value) {
		this(labelText, value, 500);
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
		this.valueLabel.setText(String.valueOf(value)); 
	}

	public static <T>  LabeledValue<T> create(String labelText, T value) {
		return new LabeledValue<T>(labelText, value);
	}
	
	
}
