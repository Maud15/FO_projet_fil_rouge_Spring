package com.m2i.calendar.controller;

import com.m2i.calendar.controller.dto.SigninRequest;
import com.m2i.calendar.controller.dto.SignupRequest;
import com.m2i.calendar.controller.exception.UserAlreadyExistsException;
import com.m2i.calendar.repository.entity.Calendar;
import com.m2i.calendar.repository.entity.User;
import com.m2i.calendar.security.jwt.JwtResponse;
import com.m2i.calendar.security.jwt.JwtUtils;
import com.m2i.calendar.service.CalendarService;
import com.m2i.calendar.service.UserCalendarRightsService;
import com.m2i.calendar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/auth")
public class AuthRequestController {

    @Autowired
    private UserService userService;
    @Autowired
    private CalendarService calendarService;
    @Autowired
    private UserCalendarRightsService userCalendarRightsService;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest dto) {
        try {
            User newUser = userService.signup(dto);
            Calendar newCalendar = calendarService.create(true);
            userCalendarRightsService.create(newUser, newCalendar, "owner" );
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch(UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> signin(@RequestBody SigninRequest dto) throws Exception {
        // UsernamePasswordAuthenticationToken : implementation of the Authentication interface which specifies
        // that the user wants to authenticate using a username and password
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(dto.getPseudo(), dto.getPassword());
        Authentication authentication;
        try {
            authentication = authManager.authenticate(auth);

            // The SecurityContextHolder is where Spring security stores the details of who is authenticated
            // Spring Security does not care how the SecurityContextHolder is populated.
            // If it contains a value, it is used as the currently authenticated user.
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JSON Web Token (JWT) (RFC 7519)
            String generatedToken = jwtUtils.generateJwt(authentication);

            // get username from Authentication SecurityContextHolder
            User connectedUser = (User) authentication.getPrincipal();
            JwtResponse jwtResp = new JwtResponse(connectedUser.getUsername(), generatedToken);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(jwtResp);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch(BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
