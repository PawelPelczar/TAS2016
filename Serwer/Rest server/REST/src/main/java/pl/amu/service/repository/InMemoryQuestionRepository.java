package pl.amu.service.repository;

import org.springframework.stereotype.Component;
import pl.amu.service.rest.dao.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by s407283 on 21.11.2016.
 */
@Component
public class InMemoryQuestionRepository implements QuestionRepository {

    private final List<Question> questions;

    public InMemoryQuestionRepository() {
        this.questions = new ArrayList<>();
    }

    public List<Question> findBySurveyID(int surveyID){
        List<Question> listed = new ArrayList<>();
        for (Question question : questions) {
            if (surveyID == question.getSurveyID()) {
                listed.add(question);
            }
        }
        return listed;

    }

    public Question findByID(int iD) {
        for (Question question : questions)
            if (iD == question.getQuestionID()) {
                return question;
            }
            return null;
    }

    public Question save(Question question) {
        Question dbQuestion = findByID(question.getQuestionID());
        if (dbQuestion != null) {
            dbQuestion.setIsMultiple(question.getIsMultiple());
            dbQuestion.setQuestionID(question.getQuestionID());
            dbQuestion.setSurveyID(question.getSurveyID());
            dbQuestion.setAnswers(question.getAnswers());
            dbQuestion.setContent(question.getContent());
        } else {
            questions.add(question);
        }
        return question;
    }

    public List<Question> getQuestions (){
        return questions;
    }


}
