package com.banking.frauddetection.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fraudulence")
public class Fraudulence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Long transactionReference;

	@NotNull
	private LocalDateTime eventTime;

	public Fraudulence updateWith(Fraudulence fraudulence) {
		return new Fraudulence(this.id, fraudulence.transactionReference, fraudulence.eventTime);
	}

}
