package com.m2i.calendar.repository;

import com.m2i.calendar.repository.entity.Calendar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalendarRepository extends CrudRepository<Calendar, Long> {

    Optional<Calendar> findByUserAndRights(@Param("userId") long userId, @Param("rights") String rights);

}
