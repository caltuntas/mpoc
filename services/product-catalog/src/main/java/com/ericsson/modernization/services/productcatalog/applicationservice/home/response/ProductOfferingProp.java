package com.ericsson.modernization.services.productcatalog.applicationservice.home.response;

public class ProductOfferingProp {

	public ProductOfferingProp(String name, long count, String isSellable) {
		setName(name);
		setCount(count);
		setIsSellable(isSellable);
	}
	
	public long getCount() {
		return count;
	}


	public void setCount(long count) {
		this.count = count;
	}


	public String getIsSellable() {
		return isSellable;
	}


	public void setIsSellable(String isSellable) {
		this.isSellable = isSellable;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	private long count;

	private String isSellable ;


	private String name;
    
	
}

