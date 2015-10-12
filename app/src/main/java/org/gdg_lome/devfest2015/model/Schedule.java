package org.gdg_lome.devfest2015.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by setico on 12/10/15.
 */
public class Schedule {
    @JsonProperty("date") private String date;
    @JsonProperty("title") private String title;
    @JsonProperty("description") private String description;
    @JsonProperty("comment") private String comment;
    @JsonProperty("tracks") private Track [] tracks;

    @JsonCreator
    public Schedule() {
    }

    @JsonCreator
    public Schedule(@JsonProperty("date") String date,@JsonProperty("title") String title,@JsonProperty("description") String description,@JsonProperty("comment") String comment,@JsonProperty("tracks") Track[] tracks) {
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
