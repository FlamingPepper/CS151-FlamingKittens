package src;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class UserService {
    private List<User> users;

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
}
