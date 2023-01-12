package com.m2i.calendar.repository;

import com.m2i.calendar.repository.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByName(RoleEnum role);
}
