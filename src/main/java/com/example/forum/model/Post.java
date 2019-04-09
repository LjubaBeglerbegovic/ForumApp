package com.example.forum.model;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity
public class Post {
	
	public Post(String subject, String message) {
		if(message == null) {
			throw new NullPointerException("Message must not be null!");
		}
		
		this.subject = subject;
		this.message = message;
		this.creationDate = new Date();
	}
	
	@GeneratedValue
	@Id
	private Long id;
	@NonNull
	private String subject;
	private String message;
	private Date creationDate;
	
	public String getId() {
		if(id==null) {
			return "";
		}
		return id.toString();
	}
	
}
