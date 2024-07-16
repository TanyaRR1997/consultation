package com.service.consultation.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class QuestionTest {
	
	@Test
	public void testGetAndSetId() {
		Question question = new Question();
		question.setId(1L);
		assertThat(question.getId()).isEqualTo(1L);
	}
	
	@Test
	public void testGetAndSetQuestionString() {
		Question question = new Question();
		question.setQuestionString("Age?");
		assertThat(question.getQuestionString()).isEqualTo("Age?");
	}

}
