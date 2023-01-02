package com.m2i.calendar.service;

import com.m2i.calendar.controller.dto.SignupRequest;
import com.m2i.calendar.controller.exception.UserAlreadyExistsException;
import com.m2i.calendar.repository.RoleEnum;
import com.m2i.calendar.repository.RoleRepository;
import com.m2i.calendar.repository.UserRepository;
import com.m2i.calendar.repository.entity.Role;
import com.m2i.calendar.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private PasswordEncoder encoder;

    public void signup(SignupRequest dto) throws UserAlreadyExistsException {
        boolean alreadyExist = userRepository.existsByPseudo(dto.getPseudo());
        if(alreadyExist) {
            throw new UserAlreadyExistsException(dto.getPseudo());
        } else {
            User newUser = new User(dto.getEmail(), dto.getPseudo(), encoder.encode(dto.getPassword()), dto.getFirstname(), dto.getLastname());
            //Define role
            Role roleUser = roleRepo.findByName(RoleEnum.ROLE_USER);
            List<Role> roleList = Collections.singletonList(roleUser);
            newUser.setRoleList(roleList);
            userRepository.save(newUser);
        }
    }

}
