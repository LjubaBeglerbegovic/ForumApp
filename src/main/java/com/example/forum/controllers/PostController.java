package com.example.forum.controllers;

import java.util.Collection;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.forum.model.Post;
import com.example.forum.model.PostRepository;

@RestController
@RequestMapping("/forum")
public class PostController{

	private PostRepository postRepository;
	
	public PostController(PostRepository postRepository) {
		this.postRepository = postRepository;
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
	
}
