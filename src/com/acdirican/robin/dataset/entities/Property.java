package com.acdirican.robin.dataset.entities;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.Vector;

import com.acdirican.robin.Utils;
import com.acdirican.robin.dataset.entities.Fields;

/**
 * An airbnb property.
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */

public class Property {
	private int id;
	private String name;
	private int host_id;
	private String host_name;
	private String neighbourhood_group;
	private String neighbourhood;
	private String latitude;
	private String longitude;
	private String room_type;
	private int price;
	private int minimum_nights;
	private int number_of_reviews;
	private LocalDate last_review;
	private double reviews_per_month;
	private int calculated_host_listings_count;
	private int availability_365;

	public Property(int id, String name, int host_id, String host_name, String neighbourhood_group,
			String neighbourhood, String latitude, String longitude, String room_type, int price, int minimum_nights,
			int number_of_reviews, LocalDate last_review, double reviews_per_month, int calculated_host_listings_count,
			int availability_365) {

		this.id = id;
		this.name = name;
		this.host_id = host_id;
		this.host_name = host_name;
		this.neighbourhood_group = neighbourhood_group;
		this.neighbourhood = neighbourhood;
		this.latitude = latitude;
		this.longitude = longitude;
		this.room_type = room_type;
		this.price = price;
		this.minimum_nights = minimum_nights;
		this.number_of_reviews = number_of_reviews;
		this.last_review = last_review;
		this.reviews_per_month = reviews_per_month;
		this.calculated_host_listings_count = calculated_host_listings_count;
		this.availability_365 = availability_365;
	}

	public static Property create(List<String> fields) {
		if (fields.size() < 16) {
			return null;
		}
		try {
			int id = Integer.parseInt(fields.get(0));
			String name = fields.get(1).trim();
			int host_id = Integer.parseInt(fields.get(2));
			String host_name = fields.get(3).trim();
			String neighbourhood_group = fields.get(4).trim();
			String neighbourhood = fields.get(5).trim();
			String latitude = fields.get(6);
			String longitude = fields.get(7);
			String roomType = fields.get(8);
			int price = Integer.parseInt(fields.get(9));
			int minimum_nights = Integer.parseInt(fields.get(10));
			;
			int number_of_reviews = Integer.parseInt(fields.get(11));

			LocalDate last_review = fields.get(12).length() != 0 ? LocalDate.parse(fields.get(12)) : null;

			double reviews_per_month = fields.get(13).length() != 0 ? Double.parseDouble(fields.get(13)) : 0;

			int calculated_host_listings_count = Integer.parseInt(fields.get(14));
			int availability_365 = Integer.parseInt(fields.get(15));

			return new Property(id, name, host_id, host_name, neighbourhood_group, neighbourhood, latitude, longitude,
					roomType, price, minimum_nights, number_of_reviews, last_review, reviews_per_month,
					calculated_host_listings_count, availability_365);

		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	public List<String> list() {
		List<String> list = new ArrayList<>();
		Map<String, Object> fields = toMap();
		for (String key : fields.keySet()) {
			Object val = fields.get(key);
			if (val != null) {
				list.add(val.toString());
			}
			else {
				list.add("");
			}
		}
		return list;
	}

	public Vector<String> summary() {
		return new Vector<String>(List.of(
					String.valueOf(id),  name, String.valueOf(host_id),  host_name,  neighbourhood,  room_type,  String.valueOf(price), String.valueOf(availability_365)
				));
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",");
		Map<String, Object> fields = toMap();
		for (String key : fields.keySet()) {
			Object val = fields.get(key);
			if (val != null) {
				joiner.add(val.toString());
			}
			else {
				joiner.add("");
			}
		}
		return joiner.toString();
	}

	public String toJSON() {
		StringJoiner joiner = new StringJoiner(",");
		Map<String, Object> fields = toMap();
		for (String key : fields.keySet()) {
			Object val = fields.get(key);
			if (val != null) {
				joiner.add(Utils.intoDblQuotation(key) + ":" + Utils.intoDblQuotation(fields.get(key)));
			}
			else {
				joiner.add(Utils.intoDblQuotation(key)  + ":" + "");
			}
		}
		return "{" + joiner.toString() + "}" + System.lineSeparator();
	}

	public Map<String, Object> toMap() {
		Map<String, Object> fields = new LinkedHashMap<>();
		fields.put("id", id);
		fields.put("name",  Utils.stringContaningComma(name));
		fields.put("host_id", host_id);
		fields.put("host_name", Utils.stringContaningComma(host_name));
		fields.put("neighbourhood_group", Utils.stringContaningComma(neighbourhood_group));
		fields.put("neighbourhood", Utils.stringContaningComma(neighbourhood));
		fields.put("latitude", latitude);
		fields.put("longitude", longitude);
		fields.put("room_type", Utils.stringContaningComma(room_type));
		fields.put("price", price);
		fields.put("minimum_nights", minimum_nights);
		fields.put("number_of_reviews", number_of_reviews);
		fields.put("last_review", (last_review != null ? last_review.toString() : ""));
		fields.put("reviews_per_month", reviews_per_month);
		fields.put("calculated_host_listings_count", calculated_host_listings_count);
		fields.put("availability_365", availability_365);
		return fields;
	}

	public Double getAsDouble(Fields field) {
		return switch (field) {
		case PRICE -> Double.valueOf(this.price);
		case MINIMUM_NIGHTS -> Double.valueOf(this.minimum_nights);
		case NUMBER_OF_REVIEWS -> Double.valueOf(this.number_of_reviews);
		case REVIEWS_PER_MONTH -> this.reviews_per_month;
		case AVAILABILITY_365 -> Double.valueOf(this.availability_365);
		default -> null;
		};
	}

	public String getAsString(Fields field) {
		return switch (field) {
		case NEIGHBOURHOOD -> this.neighbourhood;
		case ROOM_TYPE -> this.room_type.toString();
		default -> null;
		};
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHost_id() {
		return host_id;
	}

	public void setHost_id(int host_id) {
		this.host_id = host_id;
	}

	public String getHost_name() {
		return host_name;
	}

	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}

	public String getNeighbourhood_group() {
		return neighbourhood_group;
	}

	public void setNeighbourhood_group(String neighbourhood_group) {
		this.neighbourhood_group = neighbourhood_group;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getRoom_type() {
		return room_type;
	}

	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getMinimum_nights() {
		return minimum_nights;
	}

	public void setMinimum_nights(int minimum_nights) {
		this.minimum_nights = minimum_nights;
	}

	public int getNumber_of_reviews() {
		return number_of_reviews;
	}

	public void setNumber_of_reviews(int number_of_reviews) {
		this.number_of_reviews = number_of_reviews;
	}

	public LocalDate getLast_review() {
		return last_review;
	}

	public void setLast_review(LocalDate last_review) {
		this.last_review = last_review;
	}

	public double getReviews_per_month() {
		return reviews_per_month;
	}

	public void setReviews_per_month(double reviews_per_month) {
		this.reviews_per_month = reviews_per_month;
	}

	public int getCalculated_host_listings_count() {
		return calculated_host_listings_count;
	}

	public void setCalculated_host_listings_count(int calculated_host_listings_count) {
		this.calculated_host_listings_count = calculated_host_listings_count;
	}

	public int getAvailability_365() {
		return availability_365;
	}

	public void setAvailability_365(int availability_365) {
		this.availability_365 = availability_365;
	}

}
