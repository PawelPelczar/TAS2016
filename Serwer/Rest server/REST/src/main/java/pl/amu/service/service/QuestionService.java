package pl.amu.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.amu.service.repository.QuestionRepository;
import pl.amu.service.rest.dao.Question;

import java.util.List;


/**
 * Created by s407283 on 22.11.2016.
 */
@Component
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {this.questionRepository = questionRepository;}

    public Question saveQuestion(Question question){ return questionRepository.save(question);}

    public Question findByID(int iD){return questionRepository.findByID(iD); }

    public List<Question> findBySurveyID(int iD){return questionRepository.findBySurveyID(iD);}

    public List<Question> getQuestions() {return questionRepository.getQuestions();}
    

}
