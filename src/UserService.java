package src;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.scene.shape.Path;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class UserService {
    private static List<User> users;

    public UserService() {
        users = new ArrayList<>();
    }

    public void createUser(String userId, String username, String email, String password) {
        User newUser = new User(userId, username, email, password);
        users.add(newUser);
    }

    public List<User> readUsers() {
        return users.stream()
                .sorted((u1, u2) -> u2.getCreatedAt().compareTo(u1.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public void updateUser(String userId, String newUsername, String newEmail, String newPassword) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                user.setUsername(newUsername);
                user.setEmail(newEmail);
                user.setPassword(newPassword);
                break;
            }
        }
    }

    public void deleteUser(String userId) {
        users.removeIf(u -> u.getUserId().equals(userId));
    }

    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        // Check if user with the same ID already exists
        if (users.stream().anyMatch(u -> u.getUserId().equals(user.getUserId()))) {
            throw new IllegalArgumentException("User with ID " + user.getUserId() + " already exists");
        }

        users.add(user);
    }

    public Optional<User> getUserById(String userId) {
        return users.stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst();
    }

    public static void sortUsers(Comparator<User> comparator) {
        Collections.sort(users, comparator);
    }

    public static List<User> getAllUsers(String sortBy) {
        Comparator<User> comparator;

        switch (sortBy.toLowerCase()) {
            case "date":
                comparator = Comparator.comparing(User::getCreatedAt); // Assuming User class has getJoinDate method
                break;
            case "karma":
                comparator = Comparator.comparingInt(User::calculateTotalKarma); // Assuming User class has getKarma
                break;
            default:
                comparator = Comparator.comparing(User::getUsername); // Default sorting by username
                break;
        }

        sortUsers(comparator);
        return users;
    }
}
