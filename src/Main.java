package src;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private UserService userService;
    private PostService postService;
    private CommentService commentService;

    @Before
    public void setUp() {
        userService = new UserService();
        postService = new PostService();
        commentService = new CommentService();
    }

    @Test
    public void testAddUser() {
        User newUser = new User("2", "TestUser", "flamingkittens@example.com", "password");
        userService.addUser(newUser);
        Optional<User> fetchedUser = userService.getUserById("2");
        Assert.assertTrue(fetchedUser.isPresent());
        Assert.assertEquals("TestUser", fetchedUser.get().getUsername());
    }

    @Test
    public void testGetUserById() {
        User newUser = new User("3", "AnotherUser", "flamingkittens@example.com", "password");
        userService.addUser(newUser);
        Optional<User> fetchedUser = userService.getUserById("3");
        Assert.assertTrue(fetchedUser.isPresent());
        Assert.assertEquals("AnotherUser", fetchedUser.get().getUsername());
    }

    @Test
    public void testAddPost() {
        User user = new User("4", "PostUser", "flamingkittens@example.com", "password");
        userService.addUser(user);
        Post newPost = new Post("post2", "Test Post", "Content of the test post.", user);
        postService.addPost(newPost);
        Optional<Post> fetchedPost = postService.getPostById("p2");
        Assert.assertTrue(fetchedPost.isPresent());
        Assert.assertEquals("Test Post", fetchedPost.get().getTitle());
    }

    @Test
    public void testGetPostById() {
        User user = new User("5", "PostUser2", "flamingkittens@example.com", "password");
        userService.addUser(user);
        Post newPost = new Post("post3", "Another Test Post", "Content of another test post.", user);
        postService.addPost(newPost);
        Optional<Post> fetchedPost = postService.getPostById("p3");
        Assert.assertTrue(fetchedPost.isPresent());
        Assert.assertEquals("Another Test Post", fetchedPost.get().getTitle());
    }

    @Test
    public void testSortPostsByKarma() {
        List<Post> posts = new ArrayList<>();

        // Create posts with varying karma values
        for (int i = 0; i < 5; i++) {
            User user = new User("user" + i, "User" + i, "user" + i + "@example.com", "password");
            userService.addUser(user);
            Post post = new Post("post" + i, "Title" + i, "Content" + i, user);

            // Simulate different numbers of upvotes and downvotes
            for (int j = 0; j <= i; j++) {
                post.upvote("user" + j);
            }
            if (i % 2 == 0) {
                post.downvote("user" + (i + 1));
            }

            posts.add(post);
        }

        // Sort the posts by karma
        postService.sortPosts(posts, "karma");

        // Verify the sorting order
        Assert.assertTrue(isSortedByKarma(posts));
    }

    private boolean isSortedByKarma(List<Post> posts) {
        for (int i = 1; i < posts.size(); i++) {
            if (posts.get(i - 1).calculateKarma() < posts.get(i).calculateKarma()) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testAddComment() {
        User user = new User("6", "CommentUser", "flamingkittens@example.com", "password");
        userService.addUser(user);
        Post post = new Post("post4", "Post for Comment", "Content for post", user);
        postService.addPost(post);
        Comment newComment = new Comment("comment3", "Test Comment", user, post);
        commentService.addComment(newComment);
        Optional<Comment> fetchedComment = commentService.getCommentById("c3");
        Assert.assertTrue(fetchedComment.isPresent());
        Assert.assertEquals("Test Comment", fetchedComment.get().getContent());
    }

    @Test
    public void testGetCommentById() {
        User user = new User("7", "CommentUser2", "flamingkittens@example.com", "password");
        userService.addUser(user);
        Post post = new Post("post5", "Another Post for Comment", "Content for another post", user);
        postService.addPost(post);
        Comment newComment = new Comment("comment4", "Another Test Comment", user, post);
        commentService.addComment(newComment);
        Optional<Comment> fetchedComment = commentService.getCommentById("c4");
        Assert.assertTrue(fetchedComment.isPresent());
        Assert.assertEquals("Another Test Comment", fetchedComment.get().getContent());
    }

    @Test
    public void testUpdateComment() {
        User user = new User("8", "UpdateCommentUser", "flamingkittens@example.com", "password");
        userService.addUser(user);
        Post post = new Post("post6", "Post for Updated Comment", "Content for post", user);
        postService.addPost(post);
        Comment comment = new Comment("comment5", "Original Comment", user, post);
        commentService.addComment(comment);
        commentService.updateComment("comment5", "Updated Comment");
        Optional<Comment> fetchedComment = commentService.getCommentById("c5");
        Assert.assertTrue(fetchedComment.isPresent());
        Assert.assertEquals("Updated Comment", fetchedComment.get().getContent());
    }

    @Test
    public void testUpvoteDownvoteFunctionality() {
        User user = new User("userTest", "User Test", "flamingkittens@example.com", "password");
        userService.addUser(user);
        Post post = new Post("postTest", "Test Post", "Test Content", user);
        postService.addPost(post);
        Comment comment = new Comment("commentTest", "Test Comment", user, post);
        commentService.addComment(comment);

        // Simulate upvotes and downvotes
        post.upvote("user1");
        post.downvote("user2");
        comment.upvote("user3");
        comment.downvote("user4");

        // Check the karma calculations
        int expectedPostKarma = 0; // Assuming 1 upvote - 1 downvote
        int expectedCommentKarma = 0; // Assuming 1 upvote - 1 downvote
        Assert.assertEquals(expectedPostKarma, post.calculateKarma());
        Assert.assertEquals(expectedCommentKarma, comment.calculateKarma());
    }
}