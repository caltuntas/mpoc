package com.ericsson.modernization.services.productcatalog.applicationservice.home.response;



public class HomeChartsData {
    private String name;
    private int[] data;
    private String[] datalabels;
    private String[] labels;

    public HomeChartsData(String name, int[] data, String[] datalabels, String[] labels) {
        this.name = name;
        this.data = data;
        this.datalabels = datalabels;
        this.labels = labels;
    }

    public String getName() {
        return name;
    }

    public int[] getData() {
        return data;
    }

    public String[] getDatalabels() {
        return datalabels;
    }

    public String[] getLabels() {
        return labels;
    }
}
