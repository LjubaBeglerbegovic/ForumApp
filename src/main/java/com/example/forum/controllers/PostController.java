package com.example.forum.controllers;

import java.util.Collection;

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
	
}
