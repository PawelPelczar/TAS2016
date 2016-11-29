package pl.amu.service.repository;

import pl.amu.service.rest.dao.Survey;
import java.util.List;

/**
 * Created by s407283 on 22.11.2016.
 */
public interface SurveyRepository {
    Survey save(Survey survey);
    Survey findByID(int ID);
    List<Survey> getSurveys();
}
