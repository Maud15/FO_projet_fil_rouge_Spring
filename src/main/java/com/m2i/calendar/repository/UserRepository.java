package com.m2i.calendar.repository;

import com.m2i.calendar.repository.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByPseudo(String pseudo);

    Optional<User> findUserByPseudo(String pseudo);
}
