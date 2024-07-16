package com.service.consultation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.consultation.model.Answer;

public interface ConsultationAnswerRepository extends JpaRepository<Answer, Integer> {

	List<Answer> findAllByCustomerId(Long customerId);
	
}
