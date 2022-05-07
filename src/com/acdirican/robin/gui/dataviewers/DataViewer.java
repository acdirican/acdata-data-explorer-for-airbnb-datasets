package com.acdirican.robin.gui.dataviewers;

import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * A dataviewer
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
public abstract class DataViewer<K,V> extends JPanel{
	private static final long serialVersionUID = 1659832596103885043L;

	protected String dataTitle;
	protected Map<K,V> data;
	
	protected JLabel titleLabel;
	
	
}
	