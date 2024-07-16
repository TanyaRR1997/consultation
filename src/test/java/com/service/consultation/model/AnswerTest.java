package com.service.consultation.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class AnswerTest {

	@Test
	public void testGetAndSetId() {
		Answer answer = new Answer();
		answer.setId(1L);
		assertThat(answer.getId()).isEqualTo(1L);
	}
	
	@Test
	public void testGetAndSetQuestionId() {
		Answer answer = new Answer();
		answer.setQuestionId(1L);
		assertThat(answer.getQuestionId()).isEqualTo(1L);
	}
	
	@Test
	public void testGetAndSetCustomerId() {
		Answer answer = new Answer();
		answer.setCustomerId(1L);
		assertThat(answer.getCustomerId()).isEqualTo(1L);
	}
	
	@Test
	public void testGetAndSetAnswerString() {
		Answer answer = new Answer();
		answer.setAnswerString("Yes");
		assertThat(answer.getAnswerString()).isEqualTo("Yes");
	}
}
