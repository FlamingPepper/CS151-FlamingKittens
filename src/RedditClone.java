package src;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class RedditClone extends Application {

    // Member variables for the different pages/layouts
    private VBox homePage;
    private VBox usersPage;
    private BorderPane root;
    private PostService postService;
    private CommentService commentService;
    private UserService userService;

    @Override
    public void start(Stage primaryStage) {
        // Initialize the root layout
        root = new BorderPane();
        root.setPadding(new Insets(10));

        // Initialize services
        postService = new PostService();
        commentService = new CommentService();
        userService = new UserService();

        // Initialize Users and Data
        User user1 = new User("0123", "FlamingPepper", "Flamingpepper@gmail.com", "12345678");
        User user2 = new User("0124", "iLikeCats", "catsarecool@yahoo.com", "87654321");
        userService.addUser(user1);
        userService.addUser(user2);
        Post post = new Post("postTest", "Test Post", "Test Content", user1);
        postService.addPost(post);
        post.upvote("user1");
        Post post2 = new Post("postTest", "Test Post 2", "Test Content 2", user2);
        postService.addPost(post2);
        Comment newComment = new Comment("comment3", "I love this new website!!", user1, post);
        commentService.addComment(newComment);

        // Initialize the pages
        homePage = createHomePage();
        usersPage = createUserPage();

        // Top navigation bar
        HBox topNav = new HBox(10);
        topNav.setAlignment(Pos.CENTER_LEFT);
        Button homeButton = new Button("Home");
        Button usersButton = new Button("Users");

        // Set the event handler for the Home button to switch to the Home Page
        homeButton.setOnAction(event -> root.setCenter(homePage));

        // Set the event handler for the Users button to switch to the Users Page
        usersButton.setOnAction(event -> root.setCenter(usersPage));

        // Add buttons to the top navigation bar
        topNav.getChildren().addAll(homeButton, usersButton);
        root.setTop(topNav);

        // Set the initial page in the root layout
        root.setCenter(homePage); // Set the Home page as the initial content

        // Create a scene with the root layout
        Scene scene = new Scene(root, 800, 600);

        // Set the scene to the stage and show it
        primaryStage.setTitle("Reddit Clone");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private VBox createUserPage() {
        VBox usersPage = new VBox(10);
        usersPage.setPadding(new Insets(10));
        usersPage.setAlignment(Pos.TOP_CENTER);

        // Assuming UserService provides a method to get all users
        List<User> users = userService.getAllUsers("karma");

        for (User user : users) {
            HBox userBox = new HBox(10);
            userBox.setAlignment(Pos.CENTER_LEFT);

            ImageView postProfileView = new ImageView();
            if (user.getProfileImageUrl() != null) {
                postProfileView.setImage(new Image(user.getProfileImageUrl()));
            } else {
                postProfileView.setImage(new Image(
                        "file:///C:/Users/mikec/Desktop/CS151/FlamingKittens Project/FlamingKittens Project/CS151-FlamingKittens/Images/default.jpg")); // Set
                                                                                                                                                        // a
                                                                                                                                                        // default
                                                                                                                                                        // image
            }
            postProfileView.setFitHeight(50);
            postProfileView.setFitWidth(50);

            VBox scoreBox = new VBox(5);
            scoreBox.setAlignment(Pos.CENTER_LEFT);

            Label karmaLabel = new Label("karma");
            Label usernameLabel = new Label(user.getUsername());
            Label userScoreLabel = new Label(Integer.toString(user.calculateTotalKarma()));

            scoreBox.getChildren().addAll(karmaLabel, userScoreLabel);
            userBox.getChildren().addAll(postProfileView, usernameLabel, userScoreLabel);
            usersPage.getChildren().add(userBox);
        }

        return usersPage;
    }

    private VBox createHomePage() {
        // Posts section
        VBox postsSection = new VBox(10);
        postsSection.setAlignment(Pos.TOP_LEFT);

        // Assuming PostService provides a method to get all posts
        List<Post> posts = postService.getAllPosts();

        for (Post post : posts) {
            HBox postBox = new HBox(10);
            postBox.setAlignment(Pos.TOP_LEFT);

            ImageView postImageView = new ImageView(new Image(
                    "file:///C:/Users/mikec/Desktop/CS151/FlamingKittens Project/FlamingKittens Project/CS151-FlamingKittens/Images/default.jpg"));
            postImageView.setFitHeight(50);
            postImageView.setFitWidth(50);

            VBox postContent = new VBox(5);
            Label usernameLabel = new Label(post.getUser().getUsername()); // Replace with actual username from post
            Label postTextLabel = new Label(post.getContent()); // Replace with actual post content
            Button viewCommentsButton = new Button("View Comments");

            // Code for upvote and downvote
            HBox voteBox = new HBox(5);
            Button upvoteButton = new Button("Upvote");
            Button downvoteButton = new Button("Downvote");
            Label voteCountLabel = new Label("0"); // Default vote count
            upvoteButton.setOnAction(e -> {
                int currentVote = Integer.parseInt(voteCountLabel.getText());
                voteCountLabel.setText(Integer.toString(currentVote + 1));
            });
            downvoteButton.setOnAction(e -> {
                int currentVote = Integer.parseInt(voteCountLabel.getText());
                voteCountLabel.setText(Integer.toString(currentVote - 1));
            });
            voteBox.getChildren().addAll(upvoteButton, downvoteButton, voteCountLabel);

            viewCommentsButton.setOnAction(event -> {
                VBox commentsPage = createCommentsPage(post); // Create comments page for the post
                root.setCenter(commentsPage); // Switch to comments page
            });

            postContent.getChildren().addAll(usernameLabel, postTextLabel, voteBox, viewCommentsButton);
            postBox.getChildren().addAll(postImageView, postContent);
            postsSection.getChildren().add(postBox);
        }

        // Sidebar for the logged-in user
        VBox sidebar = new VBox(10);
        sidebar.setAlignment(Pos.TOP_RIGHT);
        ImageView userImageView = new ImageView(new Image(
                "file:///C:/Users/mikec/Desktop/CS151/FlamingKittens Project/FlamingKittens Project/CS151-FlamingKittens/Images/default.jpg"));

        userImageView.setFitHeight(50);
        userImageView.setFitWidth(50);
        Button setProfilePictureButton = new Button("Set profile picture");
        setProfilePictureButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));

            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                Image newImage = new Image(selectedFile.toURI().toString());
                userImageView.setImage(newImage);
            }
        });

        sidebar.getChildren().addAll(userImageView, setProfilePictureButton);

        // Combine posts section and sidebar into a BorderPane
        BorderPane homeLayout = new BorderPane();
        homeLayout.setCenter(postsSection); // Posts in the center
        homeLayout.setRight(sidebar); // Sidebar on the right

        // Wrap the combined layout in a VBox and return it
        VBox homePage = new VBox(homeLayout);
        homePage.setPadding(new Insets(10));
        homePage.setAlignment(Pos.TOP_LEFT);

        return homePage;
    }

    private VBox createCommentsPage(Post post) {
        VBox commentsPage = new VBox(10);
        commentsPage.setAlignment(Pos.TOP_CENTER);
        commentsPage.setPadding(new Insets(10));

        // Display post details
        VBox postDetails = new VBox(10);
        postDetails.setAlignment(Pos.CENTER);
        Label postUsernameLabel = new Label(post.getUser().getUsername()); // Replace with actual username
        Label postContentLabel = new Label(post.getContent()); // Replace with actual post content
        postDetails.getChildren().addAll(postUsernameLabel, postContentLabel);

        commentsPage.getChildren().add(postDetails);

        // Fetch and display comments
        List<Comment> comments = commentService.getAllComments(); // Assuming such a method exists
        for (Comment comment : comments) {
            HBox commentBox = new HBox(10);
            commentBox.setAlignment(Pos.CENTER_LEFT);

            ImageView commenterImageView = new ImageView(new Image(
                    "file:///C:/Users/mikec/Desktop/CS151/FlamingKittens Project/FlamingKittens Project/CS151-FlamingKittens/Images/default.jpg"));
            commenterImageView.setFitHeight(30);
            commenterImageView.setFitWidth(30);

            VBox commentContent = new VBox(5);
            Label commentUsernameLabel = new Label(comment.getAuthor().getUsername()); // Replace with actual
                                                                                       // username
            Label commentLabel = new Label(comment.getContent()); // Replace with actual comment text

            // Vote box for comments
            HBox voteBox = createVoteBox(comment);

            Button replyButton = new Button("Reply");
            replyButton.setOnAction(event -> {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Reply to Comment");
                dialog.setHeaderText("Enter your reply:");
                dialog.setContentText("Reply:");

                Optional<String> result = dialog.showAndWait();
                result.ifPresent(reply -> {
                    String replyText = commentUsernameLabel.getText() + " replied: " + reply;
                    Label replyLabel = new Label(replyText);
                    VBox.setMargin(replyLabel, new Insets(0, 0, 0, 20)); // Indent the reply for clarity

                    // Vote box for replies
                    HBox replyVoteBox = createVoteBox(comment);

                    VBox replyContainer = new VBox(replyLabel, replyVoteBox);
                    commentContent.getChildren().add(commentContent.getChildren().size() - 1, replyContainer);
                });
            });

            commentContent.getChildren().addAll(commentUsernameLabel, commentLabel, voteBox, replyButton);
            commentBox.getChildren().addAll(commenterImageView, commentContent);
            commentsPage.getChildren().add(commentBox);
        }

        return commentsPage;
    }

    private HBox createVoteBox(Object item) {
        HBox voteBox = new HBox(5);
        Button upvoteButton = new Button("Upvote");
        Button downvoteButton = new Button("Downvote");
        Label voteCountLabel = new Label("0"); // Initialize with 0, will be updated

        // Update the vote count label based on the type of item
        if (item instanceof Post) {
            voteCountLabel.setText(Integer.toString(((Post) item).calculateKarma()));
        } else if (item instanceof Comment) {
            voteCountLabel.setText(Integer.toString(((Comment) item).calculateKarma()));
        }

        // Event handler for upvote button
        upvoteButton.setOnAction(e -> {
            if (item instanceof Post) {
                ((Post) item).upvote("user1"); // Replace "user1" with the actual user ID
                voteCountLabel.setText(Integer.toString(((Post) item).calculateKarma()));
            } else if (item instanceof Comment) {
                ((Comment) item).upvote("user1"); // Replace "user1" with the actual user ID
                voteCountLabel.setText(Integer.toString(((Comment) item).calculateKarma()));
            }
        });

        // Event handler for downvote button
        downvoteButton.setOnAction(e -> {
            if (item instanceof Post) {
                ((Post) item).downvote("user1"); // Replace "user1" with the actual user ID
                voteCountLabel.setText(Integer.toString(((Post) item).calculateKarma()));
            } else if (item instanceof Comment) {
                ((Comment) item).downvote("user1"); // Replace "user1" with the actual user ID
                voteCountLabel.setText(Integer.toString(((Comment) item).calculateKarma()));
            }
        });
        voteBox.getChildren().addAll(upvoteButton, downvoteButton, voteCountLabel);
        return voteBox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}