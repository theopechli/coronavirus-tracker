package com.github.theopechli.coronavirustracker.modules;

import java.util.Arrays;

public class LocationStats {

    private String state;
    private String country;
    private double latitude;
    private double longitude;
    private String[] totalCases;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String[] getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(String[] totalCases) {
        this.totalCases = totalCases;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", totalCases=" + Arrays.toString(totalCases) +
                '}';
    }
}
