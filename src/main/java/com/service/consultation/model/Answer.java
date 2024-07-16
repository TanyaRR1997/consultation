package com.service.consultation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"questionId", "customerId"}))
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long questionId;
	private Long customerId;
	private String answerString;
	
	public Answer() {}
	
	public Answer(Long questionId, Long customerId, String answerString) {
		super();
		this.questionId = questionId;
		this.customerId = customerId;
		this.answerString = answerString;
	}
	
	public Long getId() {
		return id;
	}
	
	public Long getQuestionId() {
		return questionId;
	}
	
	public Long getCustomerId() {
		return customerId;
	}
	
	public String getAnswerString() {
		return answerString;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	public void setAnswerString(String answerString) {
		this.answerString = answerString;
	}

}
