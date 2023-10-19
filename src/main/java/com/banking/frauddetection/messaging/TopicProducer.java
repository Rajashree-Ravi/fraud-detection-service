package com.banking.frauddetection.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.banking.frauddetection.model.FraudulenceDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicProducer {

	@Value("${producer.config.topic.name}")
	private String topicName;

	private final KafkaTemplate<String, FraudulenceDto> kafkaTemplate;

	public void send(FraudulenceDto fraudulenceDto) {
		log.info("Payload : {}", fraudulenceDto.toString());
		kafkaTemplate.send(topicName, fraudulenceDto);
	}

}