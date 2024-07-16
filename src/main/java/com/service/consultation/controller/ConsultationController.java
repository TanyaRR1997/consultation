package com.service.consultation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.consultation.model.Answer;
import com.service.consultation.model.Question;
import com.service.consultation.service.ConsultationService;

@RestController
public class ConsultationController {
	
	private static Logger logger = LogManager.getLogger(ConsultationController.class);
	private final ConsultationService consultationService;
	
	public ConsultationController(ConsultationService consultationService) {
		this.consultationService = consultationService;
	}
	
	@GetMapping("/questions")
	public ResponseEntity<List<Question>> getQuestions() {
		logger.info("Attempting to get all consultation questions");
		return ResponseEntity.ok(consultationService.getAllQuestions());
	}
	
	@PostMapping("/answers")
	public ResponseEntity<String> sendAnswers(@RequestBody List<Answer> answers) {
		logger.info("Attempting to send answers");
		consultationService.saveAnswer(answers);
		return ResponseEntity.ok("Answered successfully");
	}
	
	@GetMapping("/prescription/{customerId}")
	public ResponseEntity<Map<String, Boolean>> getPrescriptionEligibility(@PathVariable("customerId") Long customerId) {
		logger.info("Checking prescription eligibility");
		boolean eligible = consultationService.isPrescriptionEligible(customerId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("eligible", eligible);
		return ResponseEntity.ok(response);
	}
	
}
