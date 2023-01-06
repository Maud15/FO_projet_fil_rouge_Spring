package com.m2i.calendar.repository;

import com.m2i.calendar.repository.entity.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
}
