package com.example.forum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.forum.model.Comment;
import com.example.forum.model.Post;
import com.example.forum.model.PostRepository;

@Component
public class Initializer implements CommandLineRunner{

	private final PostRepository postRepository;
	
	public Initializer(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	@Override
	public void run(String... args) throws Exception{		
		Stream.of("Title 1", "Title 2").forEach(title -> postRepository.save(new Post(title, "")));
		Post post = postRepository.findBySubject("Title 1");
		post.setMessage("Message 1");
		Set<Comment> comments = new HashSet<>();
		comments.add(new Comment("Comment on post 1"));
		comments.add(new Comment("Another comment on post 1"));
		post.setComments(comments);
		postRepository.save(post);
		
		postRepository.findAll().forEach(System.out::println);
	}

}
