package com.tsinjo.exam.service;

import com.tsinjo.exam.model.Donation;
import com.tsinjo.exam.model.Donor;
import com.tsinjo.exam.model.Payment;
import com.tsinjo.exam.repository.DonationRepository;
import com.tsinjo.exam.repository.DonorRepository;
import com.tsinjo.exam.repository.PaymentRepository;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DonationService {
  private final DonorRepository donorRepository;
  private final DonationRepository donationRepository;
  private final PaymentRepository paymentRepository;

  public void createDonation(String email, String method, String externalId) {
    Donor donor =
        donorRepository
            .findByEmail(email)
            .orElseGet(
                () ->
                    donorRepository.save(
                        Donor.builder().email(email).fullName("Nom inconnu").build()));

    Payment payment =
        Payment.builder()
            .id(UUID.randomUUID())
            .method(method)
            .externalId(externalId)
            .status(Payment.Status.VERIFYING)
            .build();
    paymentRepository.save(payment);

    Donation donation =
        Donation.builder()
            .donor(donor)
            .payment(payment)
            .amount(1000) // fixe pour l’instant
            .date(Instant.now())
            .build();

    donationRepository.save(donation);
  }
}
