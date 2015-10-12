package org.gdg_lome.devfest2015.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by setico on 12/10/15.
 */
public class Speaker implements Serializable {

    @JsonProperty("name") private String name;
    @JsonProperty("description") private String description;
    @JsonProperty("image") private String image;

    @JsonCreator
    public Speaker() {
    }

    @JsonCreator
    public Speaker(@JsonProperty("name") String name,@JsonProperty("description") String description,@JsonProperty("image") String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
