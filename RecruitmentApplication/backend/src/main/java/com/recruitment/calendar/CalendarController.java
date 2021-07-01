package com.recruitment.calendar;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import static com.recruitment.UrlMapping.CALENDAR;

@RestController
@RequiredArgsConstructor
@RequestMapping(CALENDAR)
public class CalendarController {

    private final CalendarQuickstart calendarService;

    @PostMapping
    public void setEvent(@RequestBody EventDetails eventDetails){
        System.out.println("I want to schedule event");
        try {
            calendarService.setInterview(eventDetails);
        } catch (Exception e) {
            System.out.println("Event could not be set");
        }
    }
}
