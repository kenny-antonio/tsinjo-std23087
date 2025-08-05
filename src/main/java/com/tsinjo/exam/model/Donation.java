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
public class Donation {
  private Long id;
  private Donor donor;
  private Instant date;
  private Integer amount;
  private Payment payment;
}
