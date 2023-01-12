package com.m2i.calendar.controller;

import com.m2i.calendar.controller.dto.CalendarRequest;
import com.m2i.calendar.controller.exception.CalendarNotFoundException;
import com.m2i.calendar.controller.exception.UserNotFoundException;
import com.m2i.calendar.repository.entity.Calendar;
import com.m2i.calendar.repository.entity.User;
import com.m2i.calendar.security.jwt.JwtUtils;
import com.m2i.calendar.service.CalendarService;
import com.m2i.calendar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class CalendarController {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;

    private CalendarService calendarService;
//    @PostMapping("/calendar/add")
//    public ResponseEntity<?> createCalendar(@RequestBody CalendarRequest calendarDto){
//        calendarService.createCalendar(calendarDto);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }

    /*@DeleteMapping("/calendar/{id}")
    public ResponseEntity<?> deleteCalendar(@RequestHeader("auth-token") String token, @PathVariable("id") long id){
        try{
            String pseudo = jwtUtils.getUsernameFromToken(token);
            User user = userService.getUserByPseudo(pseudo);
            //TODO: vérifier les droits de l'utilisateur sur ce calendrier -> owner only
            calendarService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @PutMapping("/calendar/{id}")
    public ResponseEntity<?> updateCalendar(@RequestHeader("auth-token") String token, @PathVariable("id") long id, @RequestBody CalendarRequest calendarDto){
        try{
            String pseudo = jwtUtils.getUsernameFromToken(token);
            User user = userService.getUserByPseudo(pseudo);
            //TODO: vérifier les droits de l'utilisateur sur ce calendrier -> owner only
            calendarService.update(id, calendarDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/calendar/{calendarId}")
    public ResponseEntity<?> getCalendarById(@RequestHeader("auth-token") String token, @PathVariable("calendarId") long calendarId) {
        try {
            String pseudo = jwtUtils.getUsernameFromToken(token);
            User user = userService.getUserByPseudo(pseudo);
            //TODO: vérifier les droits de l'utilisateur sur ce calendrier -> owner/edit/create/view OK
            Calendar calendar = calendarService.fetchCalendarById(calendarId);
            return ResponseEntity.ok(calendar);

        } catch(UserNotFoundException | CalendarNotFoundException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /*@GetMapping("/calendar/main")
    public ResponseEntity<?> getMainCalendar(@RequestHeader("auth-token") String token) {
        *//*try {
            String pseudo = jwtUtils.getUsernameFromToken(token);
            User user = userService.getUserByPseudo(pseudo);
            CalendarRequest cal = calendarService.fetchCalendarByUserMain(user);

        } catch(UserNotFoundException | CalendarNotFoundException e) {
            e.printStackTrace();
        }*//*
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }*/



}
