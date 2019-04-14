package com.example.forum.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@Entity
public class Comment {
	
	@GeneratedValue
	@Id
	private Long id;
	@NonNull
	private String commentText;
	private Date postDate = new Date();
	
	public Comment(String commentText) {
		this.commentText = commentText;
	}
	
	public String getId() {
		if(id == null) {
			return "";
		}
		return id.toString();
	}
	
}
