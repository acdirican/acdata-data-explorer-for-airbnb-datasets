package com.acdirican.discoverairbnb.dataset.entities;

import java.util.List;

/**
 * Decorator base class for filters
 * 
 * @author Ahmet Cengizhan Dirican
 * @version v1.0 May 2022
 *
 */
public abstract class FilterDecorator extends Dataset {
	
	public FilterDecorator(List<Property> properties) {
		super(properties);
	}

	public abstract void execute();
}
