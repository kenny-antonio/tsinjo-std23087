package com.tsinjo.exam.endpoint.rest.controller.health;

import com.tsinjo.exam.repository.DonationRepository;
import com.tsinjo.exam.repository.HelpRepository;
import com.tsinjo.exam.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class TsinjoController {

  private final DonationService donationService;
  private final DonationRepository donationRepository;
  private final HelpRepository helpRepository;

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("donations", donationRepository.findAllByOrderByDateDesc());
    model.addAttribute("helps", helpRepository.findAllByOrderByDateDesc());
    return "index";
  }

  @PostMapping("/donate")
  public String donate(
      @RequestParam String email, @RequestParam String method, @RequestParam String externalId) {
    donationService.createDonation(email, method, externalId);
    return "redirect:/";
  }
}
