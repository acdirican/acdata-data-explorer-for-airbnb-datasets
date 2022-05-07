package com.acdirican.discoverairbnb.gui.dataviewers;

import java.util.Map;

/**
 * Facade to create dataviewers. It is also a factory of dataviewers
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
public class DataViewerFactory {
	public static final int SEQUENTIAL = 0;
	public static final int TABULAR = 1;
	
	public static  <K,V> DataViewer<K, V> create(int type, String title, Map<K, V> data) throws IllegalArgumentException{
		return switch (type) {
			case SEQUENTIAL -> createSequentialDataViewer(title, data);
			case TABULAR -> createTabularDataViewer(title, data);		
			default ->
			throw new IllegalArgumentException("Unexpected chart type: " + type);
		};
	}

	public static <K,V> DataViewer<K, V> createTabularDataViewer(String title, Map<K, V> data) {
		return new TabularDataViewer<>(title, data);
	}

	public static <K,V> DataViewer<K, V>  createSequentialDataViewer(String title, Map<K, V> data) {
		return new SequentialDataViewer<>(title, data);
	}
}
