package org.gdg_lome.devfest2015.model;


import java.io.Serializable;

/**
 * Created by setico on 12/10/15.
 */
public class Contributor implements Serializable {

    private String name;
    private String rule;
    private String image;
    private String gplus;


    public Contributor() {
    }


    public Contributor(String name,String rule,String image, String gplus) {
        this.name = name;
        this.rule = rule;
        this.image = image;
        this.gplus = gplus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGplus() {
        return gplus;
    }

    public void setGplus(String gplus) {
        this.gplus = gplus;
    }
}
