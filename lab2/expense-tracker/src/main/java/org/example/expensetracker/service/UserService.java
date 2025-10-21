package org.example.expensetracker.service;

import org.example.expensetracker.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {
    private final Map<UUID, User> userStorage = new ConcurrentHashMap<>();

    public User createUser(User user) {
        UUID id = UUID.randomUUID();
        user.setId(id);
        userStorage.put(id, user);
        return user;
    }

    public User getUserById(UUID userId) {
        return userStorage.get(userId);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userStorage.values());
    }

    public boolean deleteUser(UUID userId) {
        return userStorage.remove(userId) != null;
    }
}
