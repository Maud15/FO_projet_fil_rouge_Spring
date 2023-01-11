package com.m2i.calendar.repository;

import com.m2i.calendar.repository.entity.Calendar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends CrudRepository<Calendar, Long> {

}
