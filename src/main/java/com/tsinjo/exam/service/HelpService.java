package com.tsinjo.exam.service;

import com.tsinjo.exam.model.Beneficiary;
import com.tsinjo.exam.model.Help;
import com.tsinjo.exam.model.Payment;
import com.tsinjo.exam.model.Status;
import com.tsinjo.exam.repository.BeneficiaryRepository;
import com.tsinjo.exam.repository.HelpRepository;
import com.tsinjo.exam.repository.PaymentRepository;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelpService {

  private final BeneficiaryRepository beneficiaryRepository;
  private final HelpRepository helpRepository;
  private final PaymentRepository paymentRepository;

  public Help createHelp(
      String email,
      String fullName,
      Integer amount,
      String description,
      String method,
      String externalId) {
    Beneficiary beneficiary =
        beneficiaryRepository
            .findByEmail(email)
            .orElseGet(
                () ->
                    beneficiaryRepository.save(
                        Beneficiary.builder().email(email).fullName(fullName).build()));

    Payment payment =
        Payment.builder()
            .id(UUID.randomUUID())
            .method(method)
            .externalId(externalId)
            .status(Status.SUCCEEDED)
            .build();
    paymentRepository.save(payment);

    Help help =
        Help.builder()
            .beneficiary(beneficiary)
            .amount(amount)
            .description(description)
            .date(Instant.now())
            .payment(payment)
            .build();

    return helpRepository.save(help);
  }
}
