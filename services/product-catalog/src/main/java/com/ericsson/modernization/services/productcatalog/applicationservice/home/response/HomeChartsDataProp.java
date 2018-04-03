package com.ericsson.modernization.services.productcatalog.applicationservice.home.response;

public class HomeChartsDataProp {

	public HomeChartsDataProp(long data, String label, String datalabel) {
		setData(data);
		setDataLabel(datalabel);
		setLabel(label);
	}


	public long getData() {
		return data;
	}

	public String getDataLabel() {
		return dataLabel;
	}

	public String getLabel() {
		return label;
	}

	public void setData(long data) {
		this.data = data;
	}

	public void setDataLabel(String dataLabel) {
		this.dataLabel = dataLabel;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	private long data;
	private String dataLabel;
	private String label;
    
	
}

