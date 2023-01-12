package com.m2i.calendar.service;

import com.m2i.calendar.controller.dto.EventRequest;
import com.m2i.calendar.controller.dto.EventUpdateRequest;
import com.m2i.calendar.controller.exception.CalendarNotFoundException;
import com.m2i.calendar.controller.exception.EventNotFoundException;
import com.m2i.calendar.repository.EventRepository;
import com.m2i.calendar.repository.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private CalendarService calendarService;

    public EventRequest create(EventRequest eventDto) throws CalendarNotFoundException {
        Event newEvent = new Event(eventDto.getTitle(), eventDto.getStartDate(), eventDto.getEndDate(), eventDto.isFullDay(), calendarService.fetchCalendarById(eventDto.getCalendarId()));
        Event result = eventRepository.save(newEvent);
        return new EventRequest(result.getId(), result.getTitle(), result.getStartDate(), result.getEndDate(),result.isFullDay(), result.getCalendar().getId());
    }

    public void delete(Long id) throws EventNotFoundException{
        Optional<Event> event = eventRepository.findById(id);
        if(event.isEmpty()) {
            throw new EventNotFoundException(id);
        }
        eventRepository.deleteById(id);
    }

    public EventRequest get(Long id) throws EventNotFoundException{
        Event event =  eventRepository
                .findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
        return new EventRequest(
                event.getId(),
                event.getTitle(),
                event.getStartDate(),
                event.getEndDate(),
                event.isFullDay(),
                event.getCalendar().getId()
        );
    }

    public List<EventRequest> getAll(long calendarId) {
        List<Event> events = eventRepository.findByCalendarId(calendarId);
        return events.stream().map(event -> new EventRequest(
                event.getId(),
                event.getTitle(),
                event.getStartDate(),
                event.getEndDate(),
                event.isFullDay(),
                event.getCalendar().getId()
        )).collect(Collectors.toList());
    }

    public EventUpdateRequest update(Long id, EventUpdateRequest eventDto) throws EventNotFoundException {
        Optional<Event> event = eventRepository.findById(id);
        if(event.isEmpty()){
            throw new EventNotFoundException(id);
        }
        Event eventUpdate = event.get();
        eventUpdate.setTitle(eventDto.getTitle());
        eventUpdate.setStartDate(eventDto.getStartDate());
        eventUpdate.setEndDate(eventDto.getEndDate());
        eventUpdate.setFullDay(eventDto.isFullDay());
        Event result = eventRepository.save(eventUpdate);
        return new EventUpdateRequest(result.getTitle(), result.getStartDate(), result.getEndDate(), result.isFullDay());
    }
}
