package src;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String username;
    private String email;
    private String password;
    private LocalDateTime createdAt;

    private List<Post> posts;
    private List<Comment> comments;

    public User(String userId, String username, String email, String password) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.posts = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    // Setters
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int calculateTotalKarma() {
        int totalKarma = 0;
        for (Post post : posts) {
            totalKarma += post.calculateKarma();
        }
        for (Comment comment : comments) {
            totalKarma += comment.calculateKarma();
        }
        return totalKarma;
    }
}
