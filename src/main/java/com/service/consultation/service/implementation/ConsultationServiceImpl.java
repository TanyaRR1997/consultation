package com.service.consultation.service.implementation;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.service.consultation.model.Answer;
import com.service.consultation.model.Question;
import com.service.consultation.repository.ConsultationAnswerRepository;
import com.service.consultation.repository.ConsultationQuestionRepository;
import com.service.consultation.service.ConsultationService;

@Service
public class ConsultationServiceImpl implements ConsultationService {
	
	private static Logger logger = LogManager.getLogger(ConsultationServiceImpl.class);
	private final ConsultationQuestionRepository questionRepository;
	private final ConsultationAnswerRepository answerRepository;
	
	public ConsultationServiceImpl(ConsultationQuestionRepository questionRepository, ConsultationAnswerRepository answerRepository) {
		this.questionRepository = questionRepository;
		this.answerRepository = answerRepository;
	}

	@Override
	public List<Question> getAllQuestions() {
		logger.info("Attempting to fetch all consultation questions");
		return questionRepository.findAll();
	}

	@Override
	public void saveAnswer(List<Answer> answers) {
		logger.info("Attempting to save answers");
		answerRepository.saveAll(answers);
	}

	@Override
	public boolean isPrescriptionEligible(Long customerId) {
		List<Answer> answers = answerRepository.findAllByCustomerId(customerId);
		if (answers.isEmpty()) {
			throw new InternalError("No answers found for this customer");
		}
		
		for (Answer answer : answers) {
			Long questionId = answer.getQuestionId();
			String answerString = answer.getAnswerString();
			
			if (questionId == 1 && answerString.equalsIgnoreCase("no")) {
				return false;
			}
			
			if ((questionId == 2 || questionId == 3) && answerString.equalsIgnoreCase("yes")) {
				return false;
			}
		}
		
		return true;
	}

}
