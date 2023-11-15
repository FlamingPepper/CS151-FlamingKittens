package src;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

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
        // Testing Upvote/Downvote Functionality
    testUpvoteDownvoteFunctionality();

    // Testing Sorting Functionality
    testSortingFunctionality();
    }

    private static void testUpvoteDownvoteFunctionality() {
        User user = new User("user1", "User One", "user1@example.com", "password");
        Post post = new Post("post1", "Title 1", "Content 1", user);
        Comment comment = new Comment("comment1", "Content", user, post);

        // Simulate upvotes and downvotes
        post.upvote("user2");
        post.upvote("user4");
        post.downvote("user3");
        comment.upvote("user2");
        comment.downvote("user3");

        // Output the results
        System.out.println("Post Karma: " + post.calculateKarma());
        System.out.println("Comment Karma: " + comment.calculateKarma());
    }

    private static void testSortingFunctionality() {
        List<Post> posts = createSamplePosts();

        // Sorting posts by Karma
        PostService postService = new PostService();
        postService.sortPosts(posts, "karma");

        // Output sorted posts
        System.out.println("Posts sorted by Karma:");
        for (Post post : posts) {
            System.out.println(post.getTitle() + " - Karma: " + post.calculateKarma());
        }
    }

    private static List<Post> createSamplePosts() {
        List<Post> posts = new ArrayList<>();
        User user = new User("user1", "User One", "user1@example.com", "password");

        // Create sample posts with different Karma values
        for (int i = 1; i <= 5; i++) {
            Post post = new Post("post" + i, "Title " + i, "Content " + i, user);
            // Simulate upvotes and downvotes
            for (int j = 0; j < i; j++) {
                post.upvote("user" + (j + 2)); // Different users upvoting
            }
            if (i % 2 == 0) {
                post.downvote("user" + (i + 1)); // Simulate a downvote for even posts
            }
            posts.add(post);
        }

        return posts;
    }
}
