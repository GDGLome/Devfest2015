package org.gdg_lome.devfest2015.model;

/**
 * Created by setico on 14/10/15.
 */
public class Location {

    private String lat;
    private String lng;

    public Location() {
    }

    public Location(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
