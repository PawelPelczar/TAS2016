package pl.amu.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.amu.service.repository.SurveyRepository;
import pl.amu.service.rest.dao.Survey;

import java.util.List;

/**
 * Created by s407283 on 22.11.2016.
 */
@Component
public class SurveyService {

    private final SurveyRepository surveyRepository;

    @Autowired
    public SurveyService(SurveyRepository surveyRepository) {this.surveyRepository = surveyRepository;}

    public Survey save(Survey survey) {return surveyRepository.save(survey);}

    public Survey findByID(int iD) {return surveyRepository.findByID(iD);}

    public List<Survey> getSurveys() {return surveyRepository.getSurveys();}
}
