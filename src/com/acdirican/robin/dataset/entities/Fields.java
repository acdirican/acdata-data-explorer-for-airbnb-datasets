package com.acdirican.robin.dataset.entities;

/**
 * The data columns of fields of typical airbnb data set.
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
public enum Fields {
	ID("Id"),
	NAME("Name"),
	HOST_ID("Host ID"),
	HOST_NAME("Host Name"),
	NEIGHBOURHOOD_GROUP("Neigbourhood Group"),
	NEIGHBOURHOOD("Neigbourhood"),
	LATITUDE("Latitude"),
	LONGITUDE("Longitude"),
	ROOM_TYPE("Room_type"),
	PRICE("Price"),
	MINIMUM_NIGHTS("Minimum Nights"),
	NUMBER_OF_REVIEWS("Number of Reviews"),
	LAST_REVIEW("Last Review"),
	REVIEWS_PER_MONTH("Reviews per Month"),
	CALCULATED_HOST_LISTINGS_COUNT("Calculated Host Listings Count"),
	AVAILABILITY_365("Availability(365)");

	private String description;

	Fields(String description) {
		this.description = description;
	}

	public int getIndex() {
		return this.ordinal();
	}
	
	public String getDescription() {
		return this.description;
	}

}
