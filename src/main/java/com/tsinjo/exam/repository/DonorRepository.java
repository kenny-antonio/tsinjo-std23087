package com.tsinjo.exam.repository;

import com.tsinjo.exam.model.Donor;
import java.util.Optional;

public interface DonorRepository extends JpaRepository<Donor, Long> {
  Optional<Donor> findByEmail(String email);
}
