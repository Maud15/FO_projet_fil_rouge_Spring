package com.m2i.calendar.controller;

import com.m2i.calendar.controller.dto.EventRequest;
import com.m2i.calendar.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping("/calendar/event")
    public ResponseEntity<?> createEvent(@RequestBody EventRequest eventDto) {
        eventService.create(eventDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/calendar/event/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable("id") long id){

        try{
            eventService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/calendar/event/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable("id") long id, @RequestBody EventRequest eventDto){
        try{
            eventService.update(id, eventDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
