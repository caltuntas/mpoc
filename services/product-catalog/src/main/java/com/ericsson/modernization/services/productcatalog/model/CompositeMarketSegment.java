package com.ericsson.modernization.services.productcatalog.model;

import java.util.ArrayList;
import java.util.List;

public class CompositeMarketSegment extends MarketSegment {
private List<MarketSegment> children;
	
	public CompositeMarketSegment() {
		children =new ArrayList<MarketSegment>();
	}

	public List<MarketSegment> getSegments() {
		return children;
	}

	public void setSegments(List<MarketSegment> segments) {
		this.children = segments;
	}	
}
