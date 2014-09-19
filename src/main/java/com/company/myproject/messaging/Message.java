package com.company.myproject.messaging;

import java.io.Serializable;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"timestamp", "requestId"})
public class Message implements Serializable{

    private static final long serialVersionUID = 5999644124806145413L;
    //Levels describe how the message should be displayed. Use these constants whenever possible.
    public static final String ERROR = "error";
    public static final String WARNING = "warning";
    public static final String PROMPT = "prompt";
    public static final String INFO = "info";
    // Levels above 100 aren't shown in a pop-up
    public static final String NOTICE_ERROR = "notice_error";
    public static final String NOTICE_INFO = "notice_info";

    @JsonIgnore
    private long requestId;

    private String level;
    private String value;
    @JsonIgnore
    private LocalDate timestamp;
    
    public Message() {
    	timestamp = new LocalDate();
    }

    public Message(String level, String message, Long requestId){
        setLevel(level);
        setValue(message);
        setRequestId(requestId);
        timestamp = new LocalDate();
    }

    public Message(String message, Long requestId){
        this(INFO, message, requestId);
    }

    public String getLevel(){
        return level;
    }

    public void setLevel(String level){
        this.level = level;
    }

    public String getValue(){
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    public String toString(){
        return "("+requestId+", " + timestamp + ")" + level.toUpperCase() + ": " + value;
    }

    public long getRequestId(){
        return requestId;
    }

    public void setRequestId(long l){
        this.requestId = l;
    }

    public LocalDate getTimestamp(){
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp){
        this.timestamp = timestamp;
    }
}
