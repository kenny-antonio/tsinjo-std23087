package com.tsinjo.exam.repository;

import com.tsinjo.exam.model.Donation;
import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
  List<Donation> findAllByOrderByDateDesc();
}
