package com.acdirican.discoverairbnb.dataset;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.acdirican.discoverairbnb.dataset.entities.Dataset;
import com.acdirican.discoverairbnb.dataset.entities.Property;
import com.acdirican.discoverairbnb.parser.Parser;

/**
 * Concrete collection of airbnb properties.
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
class AirBnbDataset extends Dataset {
	
	public AirBnbDataset( List<Property> properties) {
		super(properties);
	}
		
	public static AirBnbDataset create(String fileName) throws IOException {
		List<Property> properties = Collections.unmodifiableList(Parser.parse(fileName ));
		return new AirBnbDataset(properties);
	}
	
	public static AirBnbDataset create(List<Property> properties) {
		return new AirBnbDataset(properties);
	}
	


	

	
}
