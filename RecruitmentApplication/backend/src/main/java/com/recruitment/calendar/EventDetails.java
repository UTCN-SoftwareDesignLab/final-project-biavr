package com.recruitment.calendar;

import lombok.Data;

@Data
public class EventDetails {
    private String startDate;
    private String endDate;
    private String attendeeEmail;
    private String location;
    private String description;
}
