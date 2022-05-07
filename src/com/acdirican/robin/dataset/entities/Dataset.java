package com.acdirican.robin.dataset.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * A collection of airbnb properties.
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
public abstract class Dataset {

	/**
	 * List of properties
	 */
	protected List<Property> properties;
	
	public Dataset(List<Property> properties ) {
		this.properties = properties;
	}
	
	public List<String> roomTypes() {
		Set<String> rooms = new TreeSet<>();
		for (Property property : properties) {
			rooms.add(property.getRoom_type());
		}
		return new ArrayList<String>(rooms);
	}

	public List<String> neighborhoods(){
		 Set<String> neighborhoods = new HashSet<>();
		 for (Property property : properties) {
			 neighborhoods.add(property.getNeighbourhood());
		 }
		 return new ArrayList<String>(neighborhoods);
	}
	
	public Property get(int index) {
		return properties.get(index);
	}

	public int size() {
		return properties.size();
	}
	
	public List<Property> properties() {
		return properties;
	}
	
	public static List<String> summaryTitles() {
		return List.of("ID",  "Name",  "Host ID",  "Host Name",  "Neighbourhood",  "Room Type",  "Price", "Availability 365");
	}
	
	public static String titles() {
		return "id,name,host_id,host_name,neighbourhood_group,neighbourhood,latitude,longitude,room_type,price,minimum_nights,number_of_reviews,last_review,reviews_per_month,calculated_host_listings_count,availability_365";
	}
	
}
