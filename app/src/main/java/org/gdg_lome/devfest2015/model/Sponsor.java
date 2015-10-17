package org.gdg_lome.devfest2015.model;

import java.io.Serializable;

/**
 * Created by setico on 13/10/15.
 */
public class Sponsor implements Serializable {
    private String name;
    private String logo;
    private String url;

    public Sponsor() {
    }

    public Sponsor(String name, String logo, String url) {
        this.name = name;
        this.logo = logo;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
