package com.service.consultation.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.service.consultation.model.Answer;

@DataJpaTest
public class ConsultationAnswerRepositoryTest {

	@Autowired
	private ConsultationAnswerRepository answerRepository;
	
	@Test
	public void testUniqueConstraints() {
		Answer answer1 = new Answer(1L, 1L, "Yes");
		Answer answer2 = new Answer(1L, 1L, "No");
		
		answerRepository.save(answer1);
		
		assertThrows(DataIntegrityViolationException.class, () -> answerRepository.save(answer2));
	}
	
	@Test
	public void testFindAllByCustomerIdReturnsAsExpected() {
		Answer answer1 = new Answer(1L, 1L, "Yes");
		Answer answer2 = new Answer(2L, 1L, "No");
		Answer answer3 = new Answer(2L, 2L, "No");
		Answer answer4 = new Answer(2L, 3L, "No");
		
		List<Answer> answers = Arrays.asList(answer1, answer2, answer3, answer4);
		
        answerRepository.saveAll(answers);
		
		List<Answer> actualAnswers = answerRepository.findAllByCustomerId(2L);
		
		assertThat(actualAnswers).isEqualTo(Collections.singletonList(answer3));
	}
}
