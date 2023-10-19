package com.banking.frauddetection.service;

import com.banking.frauddetection.model.FraudulenceDto;

public interface FraudDetectionService {

	FraudulenceDto getFraudulenceById(long id);

	FraudulenceDto createFraudulence(FraudulenceDto fraudulenceDto);

}
