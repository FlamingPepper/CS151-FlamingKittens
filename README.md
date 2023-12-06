# CS151-FlamingKittens

Team members: Brandon Ho, Ken Duong, Mike Chau

### Project Structure

#### Classes

- **Main**: Tests the functionality of other classes by creating users, posts, and comments. Manages up and downvotes for posts and comments.
- **Post**: Manages post-related operations including getters, setters, constructors, upvoting, and karma calculation.
- **PostService**: Manages posts, implementing CRUD operations for posts. Includes methods to sort posts by date-created and karma.
- **Comment**: Contains constructors, getters, and setters for use in the CommentService class. Modified for upvoting, downvoting, and karma calculation.
- **CommentService**: Manages comments, implementing CRUD operations. Includes methods to sort comments by date-created and karma.
- **User**: Blueprint for creating user objects, managing user information, associated posts, comments, karma, and calculations based on upvotes/downvotes.
- **UserService**: Handles user-related operations, implementing CRUD operations. Includes methods to sort users by date-created and karma.
- **RedditClone**: Manages the overall application functionality, initializing UI and coordinating interactions between components. Handles page creation, UI layout, and user interaction.

### Change Log

#### Changes Made:

- **User Class**: Updated to include fields for karma, associated posts, comments, and methods for karma calculation based on upvotes and downvotes. Added methods for upvoting and downvoting posts/comments.
- **Post and Comment Classes**: Modified to incorporate functionality for upvoting, downvoting, and karma calculation within the classes.
- **UserService, PostService, and CommentService Classes**: Remain consistent in CRUD responsibilities for users, posts, and comments. Delegates karma/voting logic to respective classes (User, Post, Comment). Includes methods for sorting users/posts/comments by date created/karma.
- **RedditClone Class**: Implemented Home, Comments, and Users pages. Designed UI layout for navigation, post display, and user interaction using JavaFX components. Handles post/comment upvoting and downvoting. Allows profile picture uploads via FileChooser.

