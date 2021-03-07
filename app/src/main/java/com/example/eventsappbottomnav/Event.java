package com.example.eventsappbottomnav;

public class Event {

    int eventDay;
    String eventImage, eventLocation, eventMonth, eventName;

    public Event() {
    }

    public Event(int eventDay, String eventImage, String eventLocation, String eventMonth, String eventName) {
        this.eventDay = eventDay;
        this.eventImage = eventImage;
        this.eventLocation = eventLocation;
        this.eventMonth = eventMonth;
        this.eventName = eventName;
    }

    public int getEventDay() {
        return eventDay;
    }

    public void setEventDay(int eventDay) {
        this.eventDay = eventDay;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventMonth() {
        return eventMonth;
    }

    public void setEventMonth(String eventMonth) {
        this.eventMonth = eventMonth;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
