package com.m2i.calendar.service;

import com.m2i.calendar.repository.UserCalendarRightsId;
import com.m2i.calendar.repository.UserCalendarRightsRepository;
import com.m2i.calendar.repository.entity.Calendar;
import com.m2i.calendar.repository.entity.User;
import com.m2i.calendar.repository.entity.UserCalendarRights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCalendarRightsService {
    @Autowired
    private UserCalendarRightsRepository userCalendarRightsRepository;

    public void create(User user, Calendar calendar, String rights){
        UserCalendarRightsId userCalendarRightsId = new UserCalendarRightsId(user.getId(),calendar.getId());
        UserCalendarRights userCalendarRights = new UserCalendarRights( userCalendarRightsId,  user, calendar, rights );
        userCalendarRightsRepository.save(userCalendarRights);
    }

}
