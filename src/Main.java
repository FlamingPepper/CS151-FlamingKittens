package src;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // User Service
        UserService userService = new UserService();

        // Create a user
        User user1 = new User("1", "FlamingKitten", "FlamingKitten@gmail.com", "123qwe123");
        userService.addUser(user1);

        // Read user by ID
        Optional<User> fetchedUser = userService.getUserById("1");
        fetchedUser.ifPresent(user -> System.out.println("User fetched: " + user.getUsername()));

        // Post Service
        PostService postService = new PostService();

        // Create a post
        Post post1 = new Post("p1", "My First Post", "This is the content of my first post.", user1);
        postService.addPost(post1);

        // Read post by ID
        Optional<Post> fetchedPost = postService.getPostById("p1");
        fetchedPost.ifPresent(post -> System.out.println("Post fetched: " + post.getTitle()));

        // Comment Service
        CommentService commentService = new CommentService();

        // Create a comment for a post
        Comment comment1 = new Comment("c1", "Great post!", user1, post1);
        commentService.addComment(comment1);

        // Read comment by ID
        Optional<Comment> fetchedComment = commentService.getCommentById("c1");
        fetchedComment.ifPresent(comment -> System.out.println("Comment fetched: " + comment.getContent()));

        // Create a reply to a comment
        Comment reply1 = new Comment("c2", "Thank you!", user1, comment1);
        commentService.addComment(reply1);

        // Fetching all comments
        for (Comment c : commentService.getAllComments()) {
            System.out.println("Comment ID: " + c.getCommentId() + ", Content: " + c.getContent());
        }

        // Updating a comment
        commentService.updateComment("c1", "Amazing post!");
        System.out.println("Updated Comment: " + commentService.getCommentById("c1").get().getContent());

        // Deleting a comment
        commentService.deleteComment("c2");
        System.out.println("All comments after deletion:");
        for (Comment c : commentService.getAllComments()) {
            System.out.println("Comment ID: " + c.getCommentId() + ", Content: " + c.getContent());
        }
    }
}
