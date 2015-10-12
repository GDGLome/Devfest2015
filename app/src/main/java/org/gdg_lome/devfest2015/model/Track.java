package org.gdg_lome.devfest2015.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by setico on 12/10/15.
 */
public class Track {

    @JsonProperty("title") private String title;
    @JsonProperty("room") private String room;
    @JsonProperty("description") private String description;
    @JsonProperty("start") private String start;
    @JsonProperty("end") private String end;
    @JsonProperty("speaker") private Speaker speaker;


    @JsonCreator

    public Track(@JsonProperty("title") String title,@JsonProperty("room") String room,@JsonProperty("description") String description,@JsonProperty("start") String start,@JsonProperty("end") String end,@JsonProperty("speaker") Speaker speaker) {
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
