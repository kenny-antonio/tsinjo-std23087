package com.tsinjo.exam.repository;

import com.tsinjo.exam.model.Payment;
import com.tsinjo.exam.model.Status;
import java.util.List;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
  List<Payment> findByStatus(Status status);
}
