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
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private PasswordEncoder encoder;

    public User signup(SignupRequest dto) throws UserAlreadyExistsException {
        boolean alreadyExist = userRepository.existsByPseudo(dto.getPseudo());
        if(alreadyExist) {
            throw new UserAlreadyExistsException(dto.getPseudo());
        } else {
            User newUser = new User(dto.getPseudo(), dto.getEmail(), encoder.encode(dto.getPassword()), dto.getFirstname(), dto.getLastname());
            //Define role
            Role roleUser = roleRepo.findByName(RoleEnum.ROLE_USER);
            List<Role> roleList = Collections.singletonList(roleUser);
            newUser.setRoleList(roleList);
            return userRepository.save(newUser);
        }
    }

    public User getUserByPseudo(String pseudo) throws UserNotFoundException {
        return userRepository
                .findUserByPseudo(pseudo)
                .orElseThrow(() -> new UserNotFoundException(pseudo) );
    }

    public void update(String pseudo, UserInfoRequest userDto) throws  UserNotFoundException{
        Optional<User> user = userRepository.findUserByPseudo(userDto.getPseudo());
        if(user.isEmpty()){
            throw new UserNotFoundException(userDto.getPseudo());
        }
        User userUpdate = user.get();
        userUpdate.setPseudo(userDto.getPseudo());
        userUpdate.setEmail(userDto.getEmail());
        userUpdate.setFirstname(userDto.getFirstname());
        userUpdate.setLastname(userDto.getLastname());
        userUpdate.setCity(userDto.getCity());
        userUpdate.setCalendarRightsList(userDto.getCalendarRightsList());
        userUpdate.setRoleList(userDto.getRoleList());
        userRepository.save(userUpdate);

    }

}
