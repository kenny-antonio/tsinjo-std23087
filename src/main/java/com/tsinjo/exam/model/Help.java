package com.tsinjo.exam.model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Help {
  private Long id;
  private Beneficiary beneficiary;
  private Instant date;
  private Integer amount;
  private String description;
  private Payment payment;
}
