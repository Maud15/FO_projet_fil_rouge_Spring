package com.m2i.calendar.controller;

import com.m2i.calendar.controller.dto.UserInfoRequest;
import com.m2i.calendar.controller.exception.UserNotFoundException;
import com.m2i.calendar.repository.entity.User;
import com.m2i.calendar.security.jwt.JwtUtils;
import com.m2i.calendar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class UserInfoController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

//    @GetMapping("/users/{name}")
//    public ResponseEntity<?> getUserByPseudo(@PathVariable("name") String name) throws UserNotFoundException {
//        UserInfoRequest userDto = new UserInfoRequest();
//        User user = userService.getUserByPseudo(name);
//        userDto.setPseudo(user.getPseudo());
//        userDto.setEmail(user.getEmail());
//        userDto.setFirstname(user.getFirstname());
//        userDto.setLastname(user.getLastname());
//        userDto.setCity(user.getCity());
//        userDto.setRoleList(user.getRoleList());
//        userDto.setCalendarRightsList(user.getCalendarRightsList());
//        return new ResponseEntity<>(userDto, HttpStatus.OK);
//    }
//
//    @PutMapping("/users/{pseudo}")
//    public ResponseEntity<?> updateUser(@PathVariable("pseudo") String pseudo, @RequestBody UserInfoRequest userDto){
//        try{
//            userService.update(pseudo, userDto);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/calendar/{calendarId}/user")
    public ResponseEntity<?> getUserByCalendarId(@RequestHeader("auth-token") String token, @PathVariable("calendarId") long calendarId) {
        UserInfoRequest userDto = new UserInfoRequest();
        try {
            //TODO: vérifier les droits
            User user = userService.getUserByCalendarId(calendarId);
            userDto.setPseudo(user.getPseudo());
            userDto.setEmail(user.getEmail());
            userDto.setFirstname(user.getFirstname());
            userDto.setLastname(user.getLastname());
            userDto.setCity(user.getCity());
            userDto.setRoleList(user.getRoleList());
            userDto.setCalendarRightsList(user.getCalendarRightsList());
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/users/profile")
    public ResponseEntity<?> getUserByPseudo(@RequestHeader("auth-token") String token ) throws UserNotFoundException {
        UserInfoRequest userDto = new UserInfoRequest();
        String pseudo = jwtUtils.getUsernameFromToken(token);
        User user = userService.getUserByPseudo(pseudo);
        userDto.setPseudo(user.getPseudo());
        userDto.setEmail(user.getEmail());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setCity(user.getCity());
        userDto.setRoleList(user.getRoleList());
        userDto.setCalendarRightsList(user.getCalendarRightsList());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PutMapping("/users/profile")
    public ResponseEntity<?> updateUser(@RequestHeader("auth-token") String token, @RequestBody UserInfoRequest userDto){
        try{
            String pseudo = jwtUtils.getUsernameFromToken(token);
            User user = userService.getUserByPseudo(pseudo);
            userService.update(user, userDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
