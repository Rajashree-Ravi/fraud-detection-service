package com.banking.frauddetection.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.frauddetection.exception.BankingException;
import com.banking.frauddetection.model.FraudulenceDto;
import com.banking.frauddetection.service.FraudDetectionService;
import com.banking.frauddetection.messaging.TopicProducer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(produces = "application/json", value = "Operations pertaining to manage fraudulence in banking application")
@RequestMapping("/api/fraudulence")
public class FraudDetectionController {

	@Autowired
	FraudDetectionService fraudDetectionService;

	private TopicProducer topicProducer;

	@Autowired
	public FraudDetectionController(TopicProducer topicProducer) {
		this.topicProducer = topicProducer;
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retrieve specific fraudulence with the specified fraudulence id", response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved fraudulence with the fraudulence id"),
			@ApiResponse(code = 404, message = "Fraudulence with specified fraudulence id not found"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	private ResponseEntity<FraudulenceDto> getFraudulenceById(@PathVariable("id") long id) {

		FraudulenceDto fraudulence = fraudDetectionService.getFraudulenceById(id);
		if (fraudulence != null)
			return new ResponseEntity<>(fraudulence, HttpStatus.OK);
		else
			throw new BankingException("fraudulence-not-found", String.format("Fraudulence with id=%d not found", id),
					HttpStatus.NOT_FOUND);
	}

	@PostMapping("/createFraudulence")
	@ApiOperation(value = "Create a new fraudulence", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created a fraudulence"),
			@ApiResponse(code = 500, message = "Application failed to process the request") })
	public ResponseEntity<FraudulenceDto> createFraudulence(@RequestBody FraudulenceDto fraudulence) {
		FraudulenceDto suspiciousActivity = fraudDetectionService.createFraudulence(fraudulence);
		topicProducer.send(suspiciousActivity);
		return new ResponseEntity<>(suspiciousActivity, HttpStatus.CREATED);
	}

	@GetMapping("/analyzeTransaction")
	public ResponseEntity<Void> analyseTransaction() {
		// Add logic to fetch and analyse transaction

		// Creating test suspicious activity
		FraudulenceDto fraudulenceDto = new FraudulenceDto();
		fraudulenceDto.setEventTime(LocalDateTime.now());
		fraudulenceDto.setTransactionReference(Long.valueOf("1"));
		createFraudulence(fraudulenceDto);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
