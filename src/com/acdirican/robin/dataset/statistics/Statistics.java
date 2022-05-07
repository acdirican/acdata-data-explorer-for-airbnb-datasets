package com.acdirican.robin.dataset.statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.acdirican.robin.dataset.entities.Dataset;
import com.acdirican.robin.dataset.entities.Fields;
import com.acdirican.robin.dataset.entities.Property;

public class Statistics {
	
	public static Descriptive getDescriptive(Fields field, Dataset dataset) {
		List<Double> values =  new ArrayList<>();
		for (Property property : dataset.properties()) {
			 values.add(property.getAsDouble(field));
		 }
		return Descriptive.get(values);
	}
	
		
	public static Map<String, Integer> numberOfPropertiesPerNeighborhood(Dataset dataset){
		Map<String, Integer> numbers =  new HashMap<>();
		for (Property property : dataset.properties()) {
			Integer total = numbers.get(property.getNeighbourhood());
			if (total != null) {
				numbers.replace(property.getNeighbourhood(), total + 1);
			}
			else {
				numbers.put(property.getNeighbourhood(), 1);
			}
		 }
		return numbers;
	}
	
	public static Map<String, Double> averagePricesPerNeighborhood(Dataset dataset){
		Map<String, Integer> sums =  new HashMap<>();
		for (Property property : dataset.properties()) {
			Integer totalPrice = sums.get(property.getNeighbourhood());
			if (totalPrice != null) {
				totalPrice += property.getPrice();
				sums.replace(property.getNeighbourhood(), totalPrice);
			}
			else {
				sums.put(property.getNeighbourhood(), property.getPrice());
			}
		 }
	
		Map<String, Integer> numbers = numberOfPropertiesPerNeighborhood(dataset);
		
		Map<String, Double> averages =  new HashMap<>();
		for(String neighborhood : sums.keySet()) {
			averages.put(neighborhood, (double) (sums.get(neighborhood) / numbers.get(neighborhood)));
		}
		return averages;
	}
	
	public static Map<String, List<Property>> propertiesPerNeighborhood(Dataset dataset){
		Map<String, List<Property>> groups =  new HashMap<>();
		for (Property property : dataset.properties()) {
			List<Property> group = groups.get(property.getNeighbourhood());
			if (group != null) {
				group.add(property);
			}
			else {
				groups.put(property.getNeighbourhood(), new ArrayList<Property>(List.of(property)));
			}
		}
		return groups;
		
	}




	public static Map<String, Double> getGroupCounts(Fields type, Dataset dataset) {
		Map<String, Double> groups =  new HashMap<>();
		for (Property property : dataset.properties()) {
			String value = property.getAsString(type);
			Double group = groups.get(value);
			if (group != null) {
				groups.put(value, 1 + group);
			}
			else {
				groups.put(value, 1.0);
			}
		}
		return groups;
	}

}
