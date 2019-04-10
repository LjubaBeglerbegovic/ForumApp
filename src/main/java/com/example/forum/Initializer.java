package com.example.forum;

import java.util.Collections;
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
		Comment comment = new Comment("Comment on post 1");
		post.setMessage("Message 1");
		post.setComments(Collections.singleton(comment));
		postRepository.save(post);
		
		postRepository.findAll().forEach(System.out::println);
	}

}
