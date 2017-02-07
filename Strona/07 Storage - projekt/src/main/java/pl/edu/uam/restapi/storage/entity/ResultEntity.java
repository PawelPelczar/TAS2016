package pl.edu.uam.restapi.storage.entity;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.uam.restapi.storage.entity.QuestionEntityMongo;
import pl.edu.uam.restapi.storage.model.Question;
import java.util.*;


import javax.persistence.*;

@Entity
//@Table(name = "users")
@Table(name = "results")
@NamedQueries({
        @NamedQuery(name = "results.findAll", query = "SELECT u FROM ResultEntity u")
})
/**
 * Created by s407283 on 07.02.2017.
 */
public class ResultEntity {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "surveyId")
    private String surveyId;

    @Column(name = "questionId")
    private String questionId;

    @Column(name = "Title")
    private String title;

    @Column(name = "answers")
    private List<String> answers;

    @Column(name = "quantity")
    private List<Integer> quantity;

    @PostLoad
    private void postLoad() {
        LOGGER.info("postLoad: {}", toString());
    }

    public ResultEntity() {
    }

    public ResultEntity(String surveyId, String questionId,String title,List<String> answers,List<Integer> quantity){
        this.surveyId = surveyId;
        this.questionId = questionId;
        this.title = title;
        this.answers = answers;
        this.quantity = quantity;
    }

    public String getSurveyId() {return surveyId;}
    public String getQuestionId() {return questionId;}
    public String getTitle() {return title;}
    public List<String> getAnswers() {return answers;}
    public List<Integer> getQuantity() {return quantity;}

    @Override
    public String toString(){
        return Objects.toStringHelper(this)
                .add("surveyId", surveyId)
                .add("questionId", questionId)
                .add("title", title)
                .add("answers", answers)
                .add("quantity", quantity).toString();
    }


}
