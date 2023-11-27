package edu.gmu.surveyservice.repository;

import edu.gmu.surveyservice.entity.SurveyResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyResponseRepository extends JpaRepository<SurveyResponse, Long> {
}
