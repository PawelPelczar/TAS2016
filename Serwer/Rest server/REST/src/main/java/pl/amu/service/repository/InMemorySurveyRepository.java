package pl.amu.service.repository;

import org.springframework.stereotype.Component;
import pl.amu.service.rest.dao.Survey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by s407283 on 22.11.2016.
 */
@Component
public class InMemorySurveyRepository implements SurveyRepository {

    private final List<Survey> surveys;

    public InMemorySurveyRepository() {this.surveys = new ArrayList<>();}

    public Survey findByID(int iD) {
        for (Survey survey : surveys) {
            if (iD == survey.getID()) {
                return survey;
            }
        }
        return null;
    }

    public Survey save(Survey survey) {
        Survey dbSurvey = findByID(survey.getID());
        if (dbSurvey != null) {
            dbSurvey.setName(survey.getName());
            dbSurvey.setID(survey.getID());
            dbSurvey.setQuestions(survey.getQuestions());
        } else {
            surveys.add(survey);
        }
        return survey;


    }

    public List<Survey> getSurveys(){
        return surveys;
    }
}
