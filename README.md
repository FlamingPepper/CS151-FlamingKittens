# CS151-FlamingKittens

Team members: Brandon Ho, Ken Duong, Mike Chau

Project Structure

**Classes:** <br />
**Main** <br />
Tests the usage of other classes by creating users, posts, and comments. It also includes posts and comments up and downvotes. <br />
**Post** <br />
Manages post-related operations, including getters, setters, constructors, upvoting, and karma calculation. <br />
**PostService** <br />
Handles the management of posts. It implements the CRUD(Create, Read, Update, Delete) operations for posts. It includes a method to be able to choose between sorting users/comments/posts on date created/ karma! <br />
**Comment** <br />
Contains constructors, getters, and setters which are used in the CommentService class. Modified to incorporate functionality for upvoting, downvoting, and karma calculation with comments. <br />
**CommentService** <br />
Handles the management of comments. It implements the CRUD(Create, Read, Update, Delete) operations for comments. It includes a method to be able to choose between sorting comments on date-created and karma! <br />
**User** <br />
The backbone blueprint for creating user objects. Contains information about a user, including their identification, credentials, creation timestamp, associated posts, comments, karma, and methods for accessing and modifying this information. Handles the calculation of user karma based on their posts and comments upvotes and downvotes. <br />
**UserService** <br />
Handles the management of user-related operations. It implements the CRUD(Create, Read, Update, Delete) operations for the user. Services include a method to be able to choose between sorting users on the date created and karma! <br />

**ChangeLog:** <br />
User Class: Updated to include fields for karma, associated posts, comments, and methods to calculate user karma based on the upvotes and downvotes of their posts and comments. It also includes methods for upvoting and downvoting posts/comments.<br />
Post and Comment Classes: Modified to incorporate functionality for upvoting, downvoting, and karma calculation within the classes themselves.<br />
UserService, PostService, and CommentService Classes: Remain consistent in their responsibilities for CRUD operations on users, posts, and comments respectively, without directly handling karma or voting logic, delegating these responsibilities to the respective classes (User, Post, Comment). Services includes a method to be able to choose between sorting users/comments/posts on date created/ karma!<br />
