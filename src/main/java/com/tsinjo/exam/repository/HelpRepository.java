package com.tsinjo.exam.repository;

import com.tsinjo.exam.model.Help;
import java.util.List;

public interface HelpRepository extends JpaRepository<Help, Long> {
  List<Help> findAllByOrderByDateDesc();
}
