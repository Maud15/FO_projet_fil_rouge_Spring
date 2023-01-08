package com.m2i.calendar.controller;

import com.m2i.calendar.controller.dto.CalendarRequest;
import com.m2i.calendar.controller.exception.CalendarNotFoundException;
import com.m2i.calendar.service.CalendarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class CalendarController {

    private CalendarService calendarService;
//    @PostMapping("/calendar/add")
//    public ResponseEntity<?> createCalendar(@RequestBody CalendarRequest calendarDto){
//        calendarService.createCalendar(calendarDto);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }

    @DeleteMapping("/calendar/{id}")
    public ResponseEntity<?> deleteCalendar(@PathVariable("id") long id){
        try{
            calendarService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/calendar/{id}")
    public ResponseEntity<?> updateCalendar(@PathVariable("id") long id, @RequestBody CalendarRequest calendarDto){
        try{
            calendarService.update(id, calendarDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/calendar/{id}")
    public ResponseEntity<?> getCalendarById(@PathVariable("id") long id) throws CalendarNotFoundException {
        CalendarRequest calendar = calendarService.fetchCalendarById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




}
