package com.acdirican.robin.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.acdirican.robin.dataset.entities.Dataset;
import com.acdirican.robin.dataset.entities.Property;
import com.acdirican.robin.gui.dataviewers.LabeledValue;

/**
 * Dialog to show the details of a property
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
class PropertyDialog extends Dialog {
	
	/**
	 * Serial
	 */
	private static final long serialVersionUID = -7697497479629863889L;
	private Dataset dataset;
	private Property property;
	private int index;
	private LabeledValue<Integer> id;
	private LabeledValue<String> name;
	private LabeledValue<Integer> hostId;
	private LabeledValue<String> hostName;
	private LabeledValue<Integer> availability_365;
	private LabeledValue<String> neigGroup;
	private LabeledValue<String> neig;
	private LabeledValue<String> latitude;
	private LabeledValue<String> longitude;
	private LabeledValue<Integer> price;
	private LabeledValue<Integer> minimum_nights;
	private LabeledValue<Integer> number_of_reviews;
	private LabeledValue<LocalDate> last_review;
	private LabeledValue<Double> reviews_per_month;
	private LabeledValue<Integer> calculated_host_listings_count;
	private JLabel numberLabel;
	private LabeledValue<String> room_type;
	
	public PropertyDialog(MainFrame main, Dataset current, int index) {
		super(main, "Property Details", 500, 700);
		this.dataset = current;
		this.index = index;
		this.property = current.get(index);
		
		setLayout(new BorderLayout());
		
		add(createComponents(), BorderLayout.CENTER);
		add(createNavigation(), BorderLayout.SOUTH);
		
		set(index);
		
		setVisible(true);
	}

	private Component createNavigation() {
		JPanel panel =  new JPanel(new FlowLayout());
		JButton firstButton =  new JButton("First");
		firstButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				set(0);
			}
		});
		JButton previousButton =  new JButton("Previous");
		previousButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				set(index - 1);
			}
		});
		
		JButton nextButton =  new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				set(index + 1);
			}
		});
		
		JButton lastButton =  new JButton("First");
		lastButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				set(dataset.size()-1);
			}
		});
		
		numberLabel =  new JLabel("...");
		
		panel.add(firstButton);
		panel.add(previousButton);
		panel.add(numberLabel);
		panel.add(nextButton);
		panel.add(lastButton);
		return panel;
	}

	private JPanel createComponents() {
		id = LabeledValue.create("ID", property.getId());
		name  = LabeledValue.create("Name", property.getName());
		hostId = LabeledValue.create("Host ID", property.getHost_id());
		hostName = LabeledValue.create("Host Name", property.getHost_name());
		neigGroup = LabeledValue.create("Neighborhood Group", property.getNeighbourhood_group());
		neig = LabeledValue.create("Neighborhood", property.getNeighbourhood());
		latitude = LabeledValue.create("Latitude", property.getLatitude());
		longitude = LabeledValue.create("Longitude", property.getLongitude());
		room_type = LabeledValue.create("Room Type", property.getRoom_type());
		price = LabeledValue.create("Price", property.getPrice());
		minimum_nights = LabeledValue.create("Minimum Nights", property.getMinimum_nights());
		number_of_reviews = LabeledValue.create("Number of Reviews", property.getNumber_of_reviews());
		last_review = LabeledValue.create("Last Review", property.getLast_review());
		reviews_per_month = LabeledValue.create("Reviews per Month", property.getReviews_per_month());
		calculated_host_listings_count = LabeledValue.create("Calculated Host Listings Count", property.getCalculated_host_listings_count());
		availability_365 = LabeledValue.create("Availability", property.getAvailability_365());
		
		JPanel panel =  new JPanel(new GridLayout(16, 1,5,5));
		
		panel.add(id);
		panel.add(name);
		panel.add(hostId);
		panel.add(hostName);
		panel.add(neigGroup);
		panel.add(neig);
		panel.add(latitude);
		panel.add(longitude);
		panel.add( room_type);
		panel.add(price);
		panel.add(minimum_nights);
		panel.add(number_of_reviews);
		panel.add(last_review);
		panel.add(reviews_per_month);
		panel.add(calculated_host_listings_count);
		panel.add(availability_365);
		
		return panel;
	}

	private void set(int index) {
		if (index<0 || index>=dataset.size()) {
			return;
		}
		this.index = index;
		property = dataset.get(index);
		
		id.setValue(property.getId());
		name.setValue(property.getName());
		hostId.setValue(property.getHost_id());
		hostName.setValue(property.getHost_name());
		neigGroup.setValue(property.getNeighbourhood_group());
		neig.setValue(property.getNeighbourhood());
		latitude.setValue(property.getLatitude());
		longitude.setValue(property.getLongitude());
		room_type.setValue(property.getRoom_type());
		price.setValue(property.getPrice());
		minimum_nights.setValue(property.getMinimum_nights());
		number_of_reviews.setValue( property.getNumber_of_reviews());
		last_review.setValue(property.getLast_review());
		reviews_per_month.setValue(property.getReviews_per_month());
		calculated_host_listings_count.setValue(property.getCalculated_host_listings_count());
		availability_365.setValue(property.getAvailability_365());
			
		numberLabel.setText(String.valueOf(index));
	}
}
