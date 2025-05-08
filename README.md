# Back-end Challenge

## Description
For this challenge you will be writing three Java functions. The first two functions will fetch data from public APIs, and the third function will aggregate the data to provide a summary of comments per post.

Function 1: fetchPosts()
Write a GET request to the URL
https://codechytc.com/api/challenges/json/allposts to retrieve a list of posts. The function should return the data as a list of post objects. Each post object contains fields like userId and id.

Function 2: fetchComments()
Write a GET request to the URL
https://codechytc.com/api/challenges/json/allcomments to retrieve a list of comments. The function should return the data as a list of comment objects. Each comment object contains fields like postId and id.

Function 3: aggregateComments()
This function should aggregate the number of comments for each post. It should return a list of objects in the format below. The resulting list should be sorted by numberOfComments in descending order. If multiple posts have the same numberOfComments, they should be sorted by postId in descending order. Finally, print this aggregated list.

## Required Functions

### 1. `fetchPosts()`
**Objective**: Perform a GET request to:  
[https://codechytc.com/api/challenges/json/allposts](https://codechytc.com/api/challenges/json/allposts)  
**Returns**: A list of `Post` objects containing fields such as:
- `userId`
- `id`

### 2. `fetchComments()`
**Objective**: Perform a GET request to:  
[https://codechytc.com/api/challenges/json/allcomments](https://codechytc.com/api/challenges/json/allcomments)  
**Returns**: A list of `Comment` objects containing fields such as:
- `postId`
- `id`

### 3. `aggregateComments()`
**Objective**: Aggregate the number of comments for each post.  
**Output Format**:
```java
List<Map<String, Integer>>
```

### Output Example
```java
[{postId=10, numberOfComments=0}, {postId=9, numberOfComments=0}, {postId=8, numberOfComments=0}, {postId=7, numberOfComments=0}, {postId=6, numberOfComments=0}, {postId=5, numberOfComments=0}, {postId=4, numberOfComments=0}, {postId=3, numberOfComments=0}, {postId=2, numberOfComments=0}, {postId=1, numberOfComments=0}]
```


