package com.m2i.calendar.repository;

import com.m2i.calendar.repository.entity.UserCalendarRights;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCalendarRightsRepository extends CrudRepository<UserCalendarRights, UserCalendarRightsId> {


}
