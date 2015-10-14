package org.gdg_lome.devfest2015.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by setico on 12/10/15.
 */
public class Barcode implements Serializable {
    private String status;
    private String barcode;
    private int checkin_type;
    private String created;
    private String changed;


    public Barcode(){
    }

    public Barcode(String status, String barcode, int checkin_type, String created, String changed) {
        this.status = status;
        this.barcode = barcode;
        this.checkin_type = checkin_type;
        this.created = created;
        this.changed = changed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getCheckin_type() {
        return checkin_type;
    }

    public void setCheckin_type(int checkin_type) {
        this.checkin_type = checkin_type;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getChanged() {
        return changed;
    }

    public void setChanged(String changed) {
        this.changed = changed;
    }

    @Override
    public String toString() {
        return "Barcode{" +
                "status='" + status + '\'' +
                ", barcode='" + barcode + '\'' +
                ", checkin_type=" + checkin_type +
                ", created='" + created + '\'' +
                ", changed='" + changed + '\'' +
                '}';
    }
}
