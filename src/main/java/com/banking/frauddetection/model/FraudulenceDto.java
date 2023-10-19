package com.banking.frauddetection.model;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Class representing a fraudulence in banking application.")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FraudulenceDto {

	@ApiModelProperty(notes = "Unique identifier of the Fraudulence.", example = "1")
	private Long id;

	@ApiModelProperty(notes = "Unique identifier of the Transaction.", example = "1", required = true)
	@NotNull
	private Long transactionReference;

	@ApiModelProperty(notes = "Event time.", required = true)
	@NotNull
	private LocalDateTime eventTime;
}
