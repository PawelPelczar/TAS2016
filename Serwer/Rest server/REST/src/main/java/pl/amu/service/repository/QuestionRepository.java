package pl.amu.service.repository;

import pl.amu.service.rest.dao.Question;

import java.util.List;

/**
 * Created by s407283 on 21.11.2016.
 */
public interface QuestionRepository {

    Question save(Question question);
    List<Question> findBySurveyID(int queryID);
    List<Question> getQuestions();
    Question findByID(int iD);
}
