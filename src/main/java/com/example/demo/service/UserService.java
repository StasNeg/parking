package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.user.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createOrEdit(UserDto user) {

        if (user.isNew())
            return userRepository.save(new User(user.getEmail(), user.getFirstName(), user.getLastName(), PasswordUtil.encode(user.getPassword())));

        User newUser = userRepository.findById(user.getId()).orElse(null);
        newUser.setEmail(user.getEmail());
        newUser.setLastName(user.getLastName());
        newUser.setFirstName(user.getFirstName());
        newUser.setPassword(PasswordUtil.encode(user.getPassword()));
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
