package org.gdg_lome.devfest2015.model;

import java.io.Serializable;

/**
 * Created by setico on 14/10/15.
 */
public class Venue implements Serializable {

    private String name;
    private Location location;

    public Venue() {
    }

    public Venue(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
