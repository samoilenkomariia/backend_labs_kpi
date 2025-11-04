package org.example.expensetracker.service;

import org.example.expensetracker.exception.UserAlreadyExistsException;
import org.example.expensetracker.exception.UserNotFoundException;
import org.example.expensetracker.model.User;
import org.example.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public User createUser(User user) {
        userRepository.findByName(user.getName())
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException(u.getName());
                });
        user.setId(null);
        return userRepository.save(user);
    }

    public void deleteUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userRepository.delete(user);
    }
}
