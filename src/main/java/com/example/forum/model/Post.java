package com.example.forum.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity
public class Post {
	
	@GeneratedValue
	@Id
	private Long id;
	@NonNull
	private String subject;
	private String message;
	private Date creationDate;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<Comment> comments;
	
	public Post(String subject, String message) {		
		this.subject = subject;
		this.message = message;
		this.creationDate = new Date();
	}
	
	public String getId() {
		if(id==null) {
			return "";
		}
		return id.toString();
	}
	
}
