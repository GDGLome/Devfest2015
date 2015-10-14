package org.gdg_lome.devfest2015.model;

import java.io.Serializable;

/**
 * Created by setico on 12/10/15.
 */
public class Schedule implements Serializable {
    private String date;
    private String title;
    private String description;
    private String comment;
    private Track [] tracks;


    public Schedule() {
    }

    public Schedule(String date, String title, String description, String comment, Track[] tracks) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.comment = comment;
        this.tracks = tracks;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Track[] getTracks() {
        return tracks;
    }

    public void setTracks(Track[] tracks) {
        this.tracks = tracks;
    }
}
