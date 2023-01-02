package com.m2i.calendar.service;

import com.m2i.calendar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {

        UserDetails user = userRepository.findUserByPseudo(pseudo)
                .orElseThrow(() -> new UsernameNotFoundException("pseudo " + pseudo + " not found"));

        return user;
    }

}
