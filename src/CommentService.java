package src;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Collections;

public class CommentService {
    private final List<Comment> comments;

    public CommentService() {
        this.comments = new ArrayList<>();
    }

    // Create
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    // Read
    public Optional<Comment> getCommentById(String commentId) {
        return comments.stream()
                .filter(comment -> comment.getCommentId().equals(commentId))
                .findFirst();
    }

    public List<Comment> getAllComments() {
        comments.sort(Comparator.comparing(Comment::getCreatedAt)); // Sort by creation time
        return new ArrayList<>(comments);
    }

    // Update
    public void updateComment(String commentId, String newContent) {
        getCommentById(commentId).ifPresent(comment -> comment.setContent(newContent));
    }

    // Delete
    public void deleteComment(String commentId) {
        comments.removeIf(comment -> comment.getCommentId().equals(commentId));
    }

    public void sortComments(List<Comment> comments, String sortBy) {
        if ("karma".equals(sortBy)) {
            Collections.sort(comments, Comparator.comparingInt(Comment::calculateKarma).reversed());
        } else if ("date".equals(sortBy)) {
            Collections.sort(comments, Comparator.comparing(Comment::getCreatedAt));
        }
    }
}