package com.banking.frauddetection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.frauddetection.entity.Fraudulence;

public interface FraudulenceRepository extends JpaRepository<Fraudulence, Long> {

}
