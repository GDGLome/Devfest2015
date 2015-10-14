package org.gdg_lome.devfest2015.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by setico on 12/10/15.
 */
public class Attendee implements Serializable {
    private String id;
    private String name;
    private String email;
    private Barcode barcode;


    public Attendee(){
    }


    public Attendee(String id, String name, String email, Barcode barcode) {
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
