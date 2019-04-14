package com.example.forum.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>{
	Post findBySubject(String subject);
}
