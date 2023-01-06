package com.m2i.calendar.service;

import com.m2i.calendar.controller.dto.EventRequest;
import com.m2i.calendar.controller.exception.EventNotFoundException;
import com.m2i.calendar.repository.EventRepository;
import com.m2i.calendar.repository.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public void create(EventRequest eventDto){
        Event newEvent = new Event(eventDto.getTitle(), eventDto.getStartDate(), eventDto.getEndDate(), eventDto.isFullDay());
        eventRepository.save(newEvent);
    }

    public void delete(Long id) throws EventNotFoundException{
        Optional<Event> event = eventRepository.findById(id);
        if(event.isEmpty()){
            throw new EventNotFoundException(id);
        }
        eventRepository.deleteById(id);
    }

    public void update(Long id, EventRequest eventDto) throws EventNotFoundException {
        Optional<Event> event = eventRepository.findById(id);
        if(event.isEmpty()){
            throw new EventNotFoundException(id);
        }
        Event eventUpdate = event.get();
        eventUpdate.setTitle(eventDto.getTitle());
        eventUpdate.setStartDate(eventDto.getStartDate());
        eventUpdate.setEndDate(eventDto.getEndDate());
        eventUpdate.setFullDay(eventDto.isFullDay());
        eventRepository.save(eventUpdate);


    }
}
