package com.ericsson.modernization.services.productcatalog.applicationservice.home.response;


import java.util.ArrayList;

public class HomeChartsData {
    private String name;
    private ArrayList<Long> data;
    private String dataLabel;
    private ArrayList<String> labels;

    public HomeChartsData(String name, ArrayList<Long> data, String dataLabel, ArrayList<String>  labels) {
        this.name = name;
        this.data = data;
        this.dataLabel = dataLabel;
        this.labels = labels;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Long> getData() {
        return data;
    }

    public String getDatalabel() {
        return dataLabel;
    }

    public ArrayList<String>  getLabels() {
        return labels;
    }
}
