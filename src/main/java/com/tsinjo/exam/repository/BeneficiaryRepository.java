package com.tsinjo.exam.repository;

import com.tsinjo.exam.model.Beneficiary;
import java.util.Optional;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
  Optional<Beneficiary> findByEmail(String email);
}
