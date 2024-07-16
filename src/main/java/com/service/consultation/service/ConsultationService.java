package com.service.consultation.service;

import java.util.List;

import com.service.consultation.model.Answer;
import com.service.consultation.model.Question;

public interface ConsultationService {

	public List<Question> getAllQuestions();
	
	public void saveAnswer(List<Answer> answers);
	
	public boolean isPrescriptionEligible(Long customerId);
	
}
