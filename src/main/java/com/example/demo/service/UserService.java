package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.user.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createOrEdit(UserDto user) {
        if (user.isNew())
            return userRepository.save(new User(user.getEmail(), user.getFirstName(), user.getLastName(), user.getPassword()));

        User newUser = userRepository.findById(user.getId()).get();
        newUser.setEmail(user.getEmail());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setFirstName(user.getFirstName());
        newUser.setPassword(user.getPassword());
        return userRepository.save(newUser);
    }

    public UserDto findByEmail(String email) {
        return new UserDto(userRepository.getUserByEmail(email).orElse(new User()));
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }
}
