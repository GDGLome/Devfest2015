package org.gdg_lome.devfest2015.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by setico on 12/10/15.
 */
public class Attendee {
    @JsonProperty("id") private String id;
    @JsonProperty("name") private String name;
    @JsonProperty("email") private String email;
    @JsonProperty("barcode") private Barcode barcode;

    @JsonCreator
    public Attendee(){
    }

    @JsonCreator
    public Attendee(@JsonProperty("id") String id, @JsonProperty("name") String name, @JsonProperty("email") String email, @JsonProperty("barcode") Barcode barcode) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.barcode = barcode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Barcode getBarcode() {
        return barcode;
    }

    public void setBarcode(Barcode barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return "Attendee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", barcode=" + barcode +
                '}';
    }
}
