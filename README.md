# CS151-FlamingKittens

Team members: Brandon Ho, Ken Duong, Mike Chau

Project Structure

**Classes:** <br />
**Main**
Tests the usage of other classes by creating users, posts, and comments <br />
**Post**
Manages post-related operations which include getters, setters, and constructors used in the PostService class <br />
**PostService**
Handles the management of posts. It implements the CRUD(Create, Read,Update,Delete) operations for posts <br />
**Comment**
Contains constructor, getters, and setters which are used in the CommentService class
**CommentService**
Handles the management of comments. It implements the CRUD(Create, Read,Update,Delete) operations for comments
**User**
The backbone blueprint for creating user objects. It takes information about a user, including their identification, credentials, creation timestamp, and associated posts and comments. The class also provides methods for accessing and modifying this information.
**UserService**
Handles the management of user-related operations. It implements the CRUD(Create, Read,Update,Delete) operations for the user
