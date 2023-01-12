package com.m2i.calendar.service;

import com.m2i.calendar.controller.exception.UserCalendarRightsNotFound;
import com.m2i.calendar.repository.RightsEnum;
import com.m2i.calendar.repository.UserCalendarRightsId;
import com.m2i.calendar.repository.UserCalendarRightsRepository;
import com.m2i.calendar.repository.entity.Calendar;
import com.m2i.calendar.repository.entity.User;
import com.m2i.calendar.repository.entity.UserCalendarRights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCalendarRightsService {
    @Autowired
    private UserCalendarRightsRepository userCalendarRightsRepository;

    public void create(User user, Calendar calendar, RightsEnum rights){
        UserCalendarRightsId userCalendarRightsId = new UserCalendarRightsId(user.getId(),calendar.getId());
        UserCalendarRights userCalendarRights = new UserCalendarRights( userCalendarRightsId,  user, calendar, rights.name() );
        userCalendarRightsRepository.save(userCalendarRights);
    }

    public Optional<RightsEnum> getRightsFromUserAndCalendar(User user,Calendar calendar) throws UserCalendarRightsNotFound {
        if(true) {
            throw new UserCalendarRightsNotFound();
        }
        return Optional.empty();
    }

}
