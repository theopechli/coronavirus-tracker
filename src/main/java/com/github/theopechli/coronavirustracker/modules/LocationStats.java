package com.github.theopechli.coronavirustracker.modules;

import java.util.Arrays;

public class LocationStats {

    private String state;
    private String country;
    private String latitude;
    private String longitude;
    private String[] totalCases;
    private Long diffFromPrevDay;

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

    public String[] getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(String[] totalCases) {
        this.totalCases = totalCases;
    }

    public Long getDiffFromPrevDay() {
        return diffFromPrevDay;
    }

    public void setDiffFromPrevDay(Long diffFromPrevDay) {
        this.diffFromPrevDay = diffFromPrevDay;
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
