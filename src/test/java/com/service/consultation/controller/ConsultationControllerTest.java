package com.service.consultation.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.service.consultation.model.Answer;
import com.service.consultation.model.Question;
import com.service.consultation.service.ConsultationService;

@ExtendWith(MockitoExtension.class)
public class ConsultationControllerTest {

	@Mock ConsultationService consultationService;
	@InjectMocks ConsultationController consultationController;
	
	@Test
	public void testGetQuestionsReturnsSetOfQuestions() {
		List<Question> expectedQuestions = Arrays.asList(new Question(1L, "Age?"), new Question(2L, "Allergies?"));
		when(consultationService.getAllQuestions()).thenReturn(expectedQuestions);
		
		ResponseEntity<List<Question>> response = consultationController.getQuestions();
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
		assertThat(response.getBody()).isEqualTo(expectedQuestions);
	}
	
	@Test
	public void testSendAnswersIsSuccessful() {
		List<Answer> answers = Arrays.asList(new Answer(1L, 1L, "Yes"), new Answer(2L, 1L, "No"));
		doNothing().when(consultationService).saveAnswer(answers);
		
		ResponseEntity<String> response = consultationController.sendAnswers(answers);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
		assertThat(response.getBody()).isEqualTo("Answered successfully");
	}
	
	@Test
	public void testGetPrecriptionEligibilityReturnsEligibilityMap() {
		Map<String, Boolean> expectedResponseBody = new HashMap<>();
		expectedResponseBody.put("eligible", true);
		when(consultationService.isPrescriptionEligible(any())).thenReturn(true);
		
		ResponseEntity<Map<String, Boolean>> response = consultationController.getPrescriptionEligibility(1L);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
		assertThat(response.getBody()).isEqualTo(expectedResponseBody);
	}
	
}
