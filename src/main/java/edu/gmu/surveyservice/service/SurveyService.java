package edu.gmu.surveyservice.service;

import edu.gmu.surveyservice.entity.SurveyResponse;
import edu.gmu.surveyservice.repository.SurveyResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {

    SurveyResponseRepository surveyResponseRepository;
    SurveyService(SurveyResponseRepository surveyResponseRepository) {
        this.surveyResponseRepository = surveyResponseRepository;
    }

    public boolean storeSurveyResponse(SurveyResponse surveyResponse) {
        Long id = surveyResponseRepository.save(surveyResponse).getSurveyId();
        return id != null;
    }

    public List<SurveyResponse> getAllSurveyResponse() {
        return surveyResponseRepository.findAll();
    }

    public boolean updateSurveyResponse(Long surveyId, SurveyResponse surveyResponse) {
        SurveyResponse response = findSurveyById(surveyId);
        if (response != null) {
            surveyResponse.setSurveyId(response.getSurveyId());
            this.surveyResponseRepository.save(surveyResponse);
            return true;
        }
        return false;
    }

    public boolean deleteSurvey(Long surveyId) {
        SurveyResponse response = findSurveyById(surveyId);
        if(response != null) {
            this.surveyResponseRepository.delete(response);
            return true;
        }
        return false;
    }

    public SurveyResponse findSurveyById(Long surveyId) {
        Optional<SurveyResponse> response = this.surveyResponseRepository.findById(surveyId);
        return response.orElse(null);
    }

}
