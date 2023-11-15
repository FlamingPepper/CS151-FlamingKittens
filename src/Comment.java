package src;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Comment {
    private String commentId;
    private String content;
    private User author;
    private LocalDateTime createdAt;
    private Post post;
    private Comment parentComment;
    private Set<String> upvotedBy;
    private Set<String> downvotedBy;

    public Comment(String commentId, String content, User author, Post post) {
        this.commentId = commentId;
        this.content = content;
        this.author = author;
        this.post = post;
        this.createdAt = LocalDateTime.now();
        this.upvotedBy = new HashSet<>();
        this.downvotedBy = new HashSet<>();
    }

    public Comment(String commentId, String content, User author, Comment parentComment) {
        this.commentId = commentId;
        this.content = content;
        this.author = author;
        this.parentComment = parentComment;
        this.createdAt = LocalDateTime.now();
        this.upvotedBy = new HashSet<>();
        this.downvotedBy = new HashSet<>();
    }

    public String getCommentId() {
        return commentId;
    }
    
    public String getContent() {
        return content;
    }
    
    public User getAuthor() {
        return author;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public Post getPost() {
        return post;
    }
    
    public Comment getParentComment() {
        return parentComment;
    }
    
    // Setters
    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public void setAuthor(User author) {
        this.author = author;
    }
    
    public void setPost(Post post) {
        this.post = post;
    }
    
    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public void upvote(String userId) {
        upvotedBy.add(userId);
        downvotedBy.remove(userId);
    }

    public void downvote(String userId) {
        downvotedBy.add(userId);
        upvotedBy.remove(userId);
    }
    
    public int calculateKarma() {
        return upvotedBy.size() - downvotedBy.size();
    }
}
