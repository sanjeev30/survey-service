package edu.gmu.surveyservice.controller;

import edu.gmu.surveyservice.entity.SurveyResponse;
import edu.gmu.surveyservice.service.SurveyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/survey")
public class SurveyController {

    private final SurveyService surveyService;

    SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping("/submit")
    public boolean submitSurvey(@RequestBody SurveyResponse surveyResponse) {
        return this.surveyService.storeSurveyResponse(surveyResponse);
    }

    @GetMapping("/responses/{id}")
    public SurveyResponse getSurveyById(@PathVariable Long id) {
        return this.surveyService.findSurveyById(id);
    }

    @GetMapping("/responses")
    public List<SurveyResponse> getAllSurveyResponse() {
        return this.surveyService.getAllSurveyResponse();
    }

    @PutMapping("/update/{surveyId}")
    public boolean updateSurvey(@PathVariable Long surveyId, @RequestBody SurveyResponse surveyResponse) {
        return this.surveyService.updateSurveyResponse(surveyId, surveyResponse);
    }

    @DeleteMapping("/delete/{surveyId}")
    public boolean deleteSurvey(@PathVariable Long surveyId) {
        return this.surveyService.deleteSurvey(surveyId);
    }
}
