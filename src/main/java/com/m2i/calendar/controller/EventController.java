package com.m2i.calendar.controller;

import com.m2i.calendar.controller.dto.CalendarRequest;
import com.m2i.calendar.controller.dto.EventRequest;
import com.m2i.calendar.controller.dto.EventUpdateRequest;
import com.m2i.calendar.repository.entity.User;
import com.m2i.calendar.security.jwt.JwtUtils;
import com.m2i.calendar.service.CalendarService;
import com.m2i.calendar.service.EventService;
import com.m2i.calendar.service.UserService;
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
    @Autowired
    private CalendarService calendarService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/calendar/{calendarId}/event")
    public ResponseEntity<?> createEvent(@RequestHeader("auth-token") String token, @PathVariable("calendarId") long calendarId, @RequestBody EventRequest eventDto) {
        try {
            String pseudo = jwtUtils.getUsernameFromToken(token);
            User user = userService.getUserByPseudo(pseudo);
            CalendarRequest cal = calendarService.fetchCalendarById(calendarId);
            //TODO: vérifier les droits de l'utilisateur sur ce calendrier -> owner/edit/create OK
            eventService.create(eventDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/calendar/{calendarId}/event/{eventId}")
    public ResponseEntity<?> deleteEvent(@RequestHeader("auth-token") String token, @PathVariable("calendarId") long calendarId, @PathVariable("eventId") long id){
        try{
            String pseudo = jwtUtils.getUsernameFromToken(token);
            User user = userService.getUserByPseudo(pseudo);
            CalendarRequest cal = calendarService.fetchCalendarById(calendarId);
            //TODO: vérifier les droits de l'utilisateur sur ce calendrier -> owner/edit OK

            eventService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/calendar/{calendarId}/event/{eventId}")
    public ResponseEntity<?> updateEvent(@RequestHeader("auth-token") String token, @PathVariable("calendarId") long calendarId, @PathVariable("eventId") long eventId, @RequestBody EventUpdateRequest eventDto){
        try{
            String pseudo = jwtUtils.getUsernameFromToken(token);
            User user = userService.getUserByPseudo(pseudo);
            CalendarRequest cal = calendarService.fetchCalendarById(calendarId);
            //TODO: vérifier les droits de l'utilisateur sur ce calendrier -> owner/edit OK

            eventService.update(eventId, eventDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
