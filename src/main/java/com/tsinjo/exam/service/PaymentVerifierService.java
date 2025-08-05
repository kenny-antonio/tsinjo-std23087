package com.tsinjo.exam.service;

import com.tsinjo.exam.model.Payment;
import com.tsinjo.exam.model.Status;
import com.tsinjo.exam.repository.PaymentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentVerifierService {

  private final PaymentRepository paymentRepository;
  private final RestTemplate restTemplate;

  private static final String VOLA_API_URL =
      "https://42cwka3n4ifcp7ufheyrpmph240iuaxo.lambda-url.eu-west-3.on.aws/v3/payments/";
  private static final String API_KEY = "VOLA_API_KEY";

  @Scheduled(fixedDelay = 10000)
  public void checkVerifyingPayments() {
    List<Payment> verifyingPayments = paymentRepository.findByStatus(Status.VERIFYING);

    for (Payment payment : verifyingPayments) {
      try {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", API_KEY);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<VolaResponse> response =
            restTemplate.exchange(
                VOLA_API_URL + payment.getExternalId(), HttpMethod.GET, entity, VolaResponse.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
          String newStatus = response.getBody().status();

          switch (newStatus) {
            case "SUCCEEDED" -> {
              payment.setStatus(Status.SUCCEEDED);
              paymentRepository.save(payment);
              log.info("Paiement {} confirmé", payment.getId());
            }
            case "FAILED" -> {
              payment.setStatus(Status.FAILED);
              paymentRepository.save(payment);
              log.warn("Paiement {} a échoué", payment.getId());
            }
            default -> log.info("Paiement {} toujours en cours", payment.getId());
          }
        } else {
          log.warn("Réponse vide ou invalide pour {}", payment.getExternalId());
        }

      } catch (Exception e) {
        log.error(
            "Erreur lors de la vérification du paiement {} : {}",
            payment.getExternalId(),
            e.getMessage());
      }
    }
  }

  private record VolaResponse(String status) {}
}
