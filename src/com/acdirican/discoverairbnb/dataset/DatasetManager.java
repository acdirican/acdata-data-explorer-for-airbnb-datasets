package com.acdirican.discoverairbnb.dataset;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.acdirican.discoverairbnb.dataset.entities.Dataset;
import com.acdirican.discoverairbnb.dataset.entities.Property;

/**
 * Facade for datasets and filters
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
public class DatasetManager {
	
	public static Dataset create(String filename) throws IOException{
		return AirBnbDataset.create(filename);
	}

	public static Dataset roomTypeFilter(Dataset dataset, String type) {
		return new RoomTypeFilter(dataset, type);
	}
	
	public static Dataset neighborhoodFilter(Dataset dataset, String type) {
		return new NeighborhoodFilter(dataset, type);
	}
		
	public static Dataset priceRangeFilter(Dataset dataset, double lowerPrice, double higherPrice) {
		return new PriceRangeFilter(dataset, lowerPrice, higherPrice);
	}

	public static Dataset create(List<Property> properties) {
		return AirBnbDataset.create(properties);
	}

}
