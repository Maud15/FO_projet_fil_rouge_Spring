package com.m2i.calendar.service;

import com.m2i.calendar.controller.dto.SignupRequest;
import com.m2i.calendar.controller.dto.UserInfoRequest;
import com.m2i.calendar.controller.exception.UserAlreadyExistsException;
import com.m2i.calendar.controller.exception.UserNotFoundException;
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

    public User signup(SignupRequest dto) throws UserAlreadyExistsException {
        if(userRepository.existsByPseudo(dto.getPseudo())) {
            throw new UserAlreadyExistsException(dto.getPseudo());
        } else {
            //Define role
            Role roleUser = roleRepo.findByName(RoleEnum.ROLE_USER);
            List<Role> roleList = Collections.singletonList(roleUser);
            //TODO : replace null by City
            User newUser = new User(dto.getPseudo(), dto.getEmail(), encoder.encode(dto.getPassword()),dto.getFirstname(), dto.getLastname(),null, roleList);
            return userRepository.save(newUser);
        }
    }

    public User getUserByPseudo(String pseudo) throws UserNotFoundException {
        return userRepository
                .findUserByPseudo(pseudo)
                .orElseThrow(() -> new UserNotFoundException(pseudo) );
    }

    public void update(User user, UserInfoRequest userDto) {
//        Optional<User> user = userRepository.findById(id);
//        if(user.isEmpty()){
//            throw new UserNotFoundException(userDto.getPseudo());
//        }
        user.setPseudo(userDto.getPseudo());
        user.setEmail(userDto.getEmail());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setCity(userDto.getCity());
        user.setCalendarRightsList(userDto.getCalendarRightsList());
        user.setRoleList(userDto.getRoleList());
        userRepository.save(user);
    }

}
