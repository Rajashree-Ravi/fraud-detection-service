package com.banking.frauddetection.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.frauddetection.entity.Fraudulence;
import com.banking.frauddetection.model.FraudulenceDto;
import com.banking.frauddetection.repository.FraudulenceRepository;
import com.banking.frauddetection.service.FraudDetectionService;

@Service
public class FraudDetectionServiceImpl implements FraudDetectionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FraudDetectionServiceImpl.class);

	@Autowired
	private FraudulenceRepository fraudulenceRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public FraudulenceDto getFraudulenceById(long id) {
		Optional<Fraudulence> fraudulence = fraudulenceRepository.findById(id);
		return (fraudulence.isPresent() ? mapper.map(fraudulence.get(), FraudulenceDto.class) : null);
	}

	@Override
	public FraudulenceDto createFraudulence(FraudulenceDto fraudulenceDto) {
		Fraudulence fraudulence = mapper.map(fraudulenceDto, Fraudulence.class);
		return mapper.map(fraudulenceRepository.save(fraudulence), FraudulenceDto.class);
	}

}
