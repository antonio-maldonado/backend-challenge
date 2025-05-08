package com.app.backend_challenge;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.*;
import java.util.*;

public class App {
	private static final String POSTS_URL = "https://coderbyte.com/api/challenges/json/all-posts";
	  private static final String COMMENTS_URL = "https://coderbyte.com/api/challenges/json/all-comments";
	  private static final Gson GSON = new Gson();

	  record Post(
	    int userId,
	    int id
	  ) {}

	  record Comment(
	    int postId,
	    int id
	  ) {}

	  public static List<Post> fetchPosts() {
	    final HttpRequest postsRequest = HttpRequest.newBuilder()
	    .uri(URI.create(POSTS_URL))
	    .GET()
	    .build();


	    try{
	      final HttpResponse<String> postsResponse =
	          HttpClient
	          .newHttpClient()
	            .send(postsRequest, HttpResponse.BodyHandlers.ofString());

	      return Arrays.stream(GSON.fromJson(postsResponse.body(), Post[].class))
	      .toList();
	    }catch(Exception e){
	      return List.of();
	    }
	  }

	  public static List<Comment> fetchComments() {
	    final HttpRequest commentsRequest = HttpRequest.newBuilder()
	    .uri(URI.create(COMMENTS_URL))
	    .GET().build();

	    try{

	      final HttpResponse<String> commentsResponse = HttpClient.newHttpClient()
	      .send(commentsRequest, HttpResponse.BodyHandlers.ofString());

	      return Arrays.stream(GSON.fromJson(commentsResponse.body(), Comment[].class)).toList();

	    }catch(Exception e){
	      return List.of();
	    }
	  }

	  public static List<Map<String, Integer>> aggregateComments(List<Post> posts, List<Comment> comments) { 
	    final Map<Integer, Integer> commentsCount = new HashMap<>();

	    for(Post post : posts){
	      commentsCount.put(post.id(), 0);
	    }

	    for(Comment comment : comments){
	      commentsCount.computeIfPresent(comment.postId, 
	      (key, value)-> value + 1);
	    }

	    final List<Map<String, Integer>> commentsPerPostList = new ArrayList<>();

	    for(Map.Entry<Integer, Integer> commentEntry : commentsCount.entrySet()){
	      final Map<String, Integer> commentsPerPost = new HashMap<>();

	      commentsPerPost.put("postId", commentEntry.getKey());
	      commentsPerPost.put("numberOfComments", commentEntry.getValue());

	      commentsPerPostList.add(commentsPerPost);
	    }

	    commentsPerPostList.sort((firstPost, secondPost) -> {
	      int countCompare = secondPost.get("numberOfComments")
	        .compareTo(firstPost.get("numberOfComments"));

	      return countCompare != 0 ? countCompare : 
	        secondPost.get("postId").compareTo(firstPost.get("postId"));
	    });

	    return commentsPerPostList;
	  }

	  public static void main (String[] args) {
	    // Fetch posts and comments from APIs
	    List<Post> posts = fetchPosts();
	    List<Comment> comments = fetchComments();

	    // Aggregate the comments and print the result
	    List<Map<String, Integer>> aggregatedComments = aggregateComments(posts, comments);
	    System.out.println(aggregatedComments);
	  }
	  

}
