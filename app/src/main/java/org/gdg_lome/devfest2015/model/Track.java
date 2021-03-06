package org.gdg_lome.devfest2015.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by setico on 12/10/15.
 */
public class Track implements Serializable {

    private String title;
    private String room;
    private String description;
    private String start;
    private String end;
    private Speaker speaker;


    public Track() {
    }


    public Track(String title, String room, String description, String start, String end, Speaker speaker) {
        this.title = title;
        this.room = room;
        this.description = description;
        this.start = start;
        this.end = end;
        this.speaker = speaker;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }
}
