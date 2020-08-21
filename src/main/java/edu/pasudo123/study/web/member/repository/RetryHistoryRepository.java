package edu.pasudo123.study.web.member.repository;

import edu.pasudo123.study.web.member.model.RetryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetryHistoryRepository extends JpaRepository<RetryHistory, Long> {
}
