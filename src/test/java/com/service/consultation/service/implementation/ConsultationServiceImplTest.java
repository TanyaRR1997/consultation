package com.service.consultation.service.implementation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.service.consultation.model.Answer;
import com.service.consultation.model.Question;
import com.service.consultation.repository.ConsultationAnswerRepository;
import com.service.consultation.repository.ConsultationQuestionRepository;

@ExtendWith(MockitoExtension.class)
public class ConsultationServiceImplTest {

	@Mock ConsultationQuestionRepository questionRepository;
	@Mock ConsultationAnswerRepository answerRepository;
	@InjectMocks ConsultationServiceImpl consultationServiceImpl;
	
	@Test
	public void testGetAllQuestionsReturnsListOfQuestion() {
		List<Question> expectedQuestions = Arrays.asList(new Question(1L, "Age?"), new Question(2L, "Allergies?"));
		when(questionRepository.findAll()).thenReturn(expectedQuestions);
		
		List<Question> actualQuestions = consultationServiceImpl.getAllQuestions();

		assertThat(actualQuestions).isEqualTo(expectedQuestions);
	}
	
	@Test
	public void testSaveAnswerCallsRepositorySaveAll() {
		List<Answer> answers = Arrays.asList(new Answer(1L, 1L, "Yes"), new Answer(2L, 1L, "No"));
		consultationServiceImpl.saveAnswer(answers);
		
		verify(answerRepository, times(1)).saveAll(answers);
	}
	
	@Test
	public void testIsPrescriptionEligibleReturnsTrueWhenEligible() {
		List<Answer> answers = Arrays.asList(new Answer(1L, 1L, "Yes"), new Answer(2L, 1L, "No"), new Answer(3L, 1L, "No"));
		when(answerRepository.findAllByCustomerId(anyLong())).thenReturn(answers);
		
		boolean eligibility = consultationServiceImpl.isPrescriptionEligible(1L);
		
		assertTrue(eligibility);
	}
	
	@ParameterizedTest
	@MethodSource("provideIneligibleAnswers")
	public void testIsPrescriptionEligibleReturnsFalseWhenIneligible(String ans1, String ans2, String ans3) {
		List<Answer> answers = Arrays.asList(new Answer(1L, 1L, ans1), new Answer(2L, 1L, ans2), new Answer(3L, 1L, ans3));
		when(answerRepository.findAllByCustomerId(anyLong())).thenReturn(answers);
		
		boolean eligibility = consultationServiceImpl.isPrescriptionEligible(1L);
		
		assertFalse(eligibility);
	}
	
	private static Stream<Arguments> provideIneligibleAnswers() {
	    return Stream.of(
	      Arguments.of("No", "No", "No"),
	      Arguments.of("Yes", "Yes", "No"),
	      Arguments.of("Yes", "No", "Yes")
	    );
	}
	
	@Test
	public void testIsPrecriptionEligibleThrowsInternalErrorWhenNoAnswersFound() {
		when(answerRepository.findAllByCustomerId(anyLong())).thenReturn(new ArrayList<Answer>());
		
		assertThrows(InternalError.class, () -> consultationServiceImpl.isPrescriptionEligible(1L), "No answers found for this customer");
	}
}
