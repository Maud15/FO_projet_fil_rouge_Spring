package com.m2i.calendar.service;

import com.m2i.calendar.controller.dto.CalendarRequest;
import com.m2i.calendar.controller.exception.CalendarNotFoundException;
import com.m2i.calendar.repository.CalendarRepository;
import com.m2i.calendar.repository.RightsEnum;
import com.m2i.calendar.repository.entity.Calendar;
import com.m2i.calendar.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    public Calendar create(boolean isMainCalendar){
        Calendar newCalendar = new Calendar(  isMainCalendar);
        return calendarRepository.save(newCalendar);
    }


    public Calendar fetchCalendarById(Long calendarId) throws CalendarNotFoundException {
        return calendarRepository
                .findById(calendarId)
                .orElseThrow(() -> new CalendarNotFoundException(calendarId)) ;
    }
    public Calendar fetchCalendarByUserAndRights(User user, RightsEnum rights) throws Exception {
        Optional<Calendar> optCal = calendarRepository
                .findByUserAndRights(user.getId(),rights.name()) ;
        if(optCal.isPresent()) {
            return optCal.get();
        }
        throw new Exception("Calendar not found");
    }

    public void delete(Long id) throws CalendarNotFoundException {
        Optional<Calendar> calendar = calendarRepository.findById(id);
        if(calendar.isEmpty()){
            throw new CalendarNotFoundException(id);
        }
        calendarRepository.deleteById(id);
    }

    public void update(Long id, CalendarRequest calendarDto) throws CalendarNotFoundException{
        Optional<Calendar> calendar = calendarRepository.findById(id);
        if(calendar.isEmpty()){
            throw new CalendarNotFoundException(id);
        }
        Calendar calendarUpdate = calendar.get();
        calendarUpdate.setMainCalendar(calendarDto.isMainCalendar());
        calendarUpdate.setCalendarUserRightsList(calendarDto.getCalendarUserRightsList());
        calendarRepository.save(calendarUpdate);

    }


}
