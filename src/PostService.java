package src;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class PostService {
    private final List<Post> posts;

    public PostService() {
        this.posts = new ArrayList<>();
    }

    // Create
    public void addPost(Post post) {
        posts.add(post);
    }

    // Read
    public Optional<Post> getPostById(String postId) {
        return posts.stream()
                .filter(post -> post.getPostId().equals(postId))
                .findFirst();
    }

    public List<Post> getAllPosts() {
        posts.sort(Comparator.comparing(Post::getCreatedAt)); // Sort by creation time
        return new ArrayList<>(posts);
    }

    // Update
    public void updatePost(String postId, String newTitle, String newContent) {
        getPostById(postId).ifPresent(post -> {
            post.setTitle(newTitle);
            post.setContent(newContent);
        });
    }

    // Delete
    public void deletePost(String postId) {
        posts.removeIf(post -> post.getPostId().equals(postId));
    }
}
