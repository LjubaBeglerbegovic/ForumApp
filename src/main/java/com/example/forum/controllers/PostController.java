package com.example.forum.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.forum.model.Comment;
import com.example.forum.model.CommentRepository;
import com.example.forum.model.Post;
import com.example.forum.model.PostRepository;

@RestController
@RequestMapping("/forum")
public class PostController{

	private PostRepository postRepository;
	private CommentRepository commentRepository;
	
	public PostController(PostRepository postRepository, CommentRepository commentRepository) {
		this.postRepository = postRepository;
		this.commentRepository = commentRepository;
	}
	
	@GetMapping("/posts")
	Collection<Post> getAllPosts(){
		return postRepository.findAll();
	}
	
	@GetMapping("/post/{id}")
	ResponseEntity<?> getPost(@PathVariable Long id){
		Optional<Post> post = postRepository.findById(id);
		return post.map(response-> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/post")
	ResponseEntity<?> createPost(@Valid @RequestBody Post post)throws URISyntaxException{
		Post newPost = postRepository.save(post);
		return ResponseEntity.created(new URI("forum/post/" + newPost.getId())).body(newPost);
	}
	
	@PutMapping("/comment/{id}")
    ResponseEntity<Post> updatePostComment(@Valid @RequestBody Comment comment, @PathVariable Long id) {
		Optional<Post> post = postRepository.findById(id);
        Set<Comment> comments = post.get().getComments();
        comments.add(comment);
        post.get().setComments(comments);
		postRepository.save(post.get());
		commentRepository.findAll().forEach(System.out::println);
        return post.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
	
}
