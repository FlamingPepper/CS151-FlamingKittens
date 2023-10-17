package src;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {
    private String postId;
    private String title;
    private String content;
    private User user;
    private LocalDateTime createdAt;

    private List<Comment> comments;

    public Post(String postId, String title, String content, User user) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = LocalDateTime.now();
        this.comments = new ArrayList<>();
    }

    public String getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
    return user;
    }

    public LocalDateTime getCreatedAt() {
    return createdAt;
    }

    public List<Comment> getComments() {
    return new ArrayList<>(comments);
    }

    // Setters
    public void setPostId(String postId) {
    this.postId = postId;
    }

    public void setTitle(String title) {
    this.title = title;
    }

    public void setContent(String content) {
    this.content = content;
    }

    public void setUser(User user) {
    this.user = user;
    }

    // Additional methods for comments
    public void addComment(Comment comment) {
    this.comments.add(comment);
    }

    public void removeComment(Comment comment) {
    this.comments.remove(comment);
    }
}