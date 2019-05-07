package com.example.forum.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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
	@NotEmpty
	private String subject;
	private String message;
	private Date creationDate = new Date();
	@Lob
	private byte[] image;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<Comment> comments;
	
	public Post(String subject, String message) {		
		this.subject = subject;
		this.message = message;
	}
	
	public String getId() {
		if(id==null) {
			return "";
		}
		return id.toString();
	}
	
}
