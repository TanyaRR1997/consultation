package com.service.consultation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.consultation.model.Question;

public interface ConsultationQuestionRepository extends JpaRepository<Question, Integer> {
	
}
