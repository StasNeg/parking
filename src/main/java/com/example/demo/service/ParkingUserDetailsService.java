package com.example.demo.service;

import com.example.demo.model.user.ParkingUserPrincipal;
import com.example.demo.model.user.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ParkingUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public ParkingUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.getUserByEmail(email).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new ParkingUserPrincipal(user);
    }
}