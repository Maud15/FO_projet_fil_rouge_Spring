package com.m2i.calendar.repository;

import com.m2i.calendar.repository.entity.Event;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

    @Transactional
    List<Event> findByCalendarId(long tutorialId);
}
