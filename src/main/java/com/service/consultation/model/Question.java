package com.service.consultation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String questionString;
	
	public Question() {}
	
	public Question(Long id, String questionString) {
		super();
		this.id = id;
		this.questionString = questionString;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getQuestionString() {
		return questionString;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setQuestionString(String questionString) {
		this.questionString = questionString;
	}
	
}
