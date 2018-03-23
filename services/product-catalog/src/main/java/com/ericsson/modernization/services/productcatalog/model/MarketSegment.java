package com.ericsson.modernization.services.productcatalog.model;

public class MarketSegment extends EntityBase implements Description {
	private String name;
	private String description;
	private String marketSegmentCategory;
	private String marketSegmentSubCategory;
	private TimePeriod validFor;
	private CompositeMarketSegment compositeMarketSegment;

	public String getMarketSegmentCategory() {
		return marketSegmentCategory;
	}

	public void setMarketSegmentCategory(String marketSegmentCategory) {
		this.marketSegmentCategory = marketSegmentCategory;
	}

	public String getMarketSegmentSubCategory() {
		return marketSegmentSubCategory;
	}

	public void setMarketSegmentSubCategory(String marketSegmentSubCategory) {
		this.marketSegmentSubCategory = marketSegmentSubCategory;
	}

	public TimePeriod getValidFor() {
		return validFor;
	}

	public void setValidFor(TimePeriod validFor) {
		this.validFor = validFor;
	}

	public CompositeMarketSegment getCompositeMarketSegment() {
		return compositeMarketSegment;
	}

	public void setCompositeMarketSegment(CompositeMarketSegment compositeMarketSegment) {
		this.compositeMarketSegment = compositeMarketSegment;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}
}
