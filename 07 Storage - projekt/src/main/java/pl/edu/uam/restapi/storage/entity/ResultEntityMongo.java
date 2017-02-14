package pl.edu.uam.restapi.storage.entity;

import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import pl.edu.uam.restapi.storage.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

/**
 * Created by s407283 on 07.02.2017.
 */
@Entity("results")
public class ResultEntityMongo {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserEntityMongo.class);

    // auto-generated, if not set (see ObjectId)
    @Id
    ObjectId id;

    //fields can be renamed
    @Property("surveyId")
    private ObjectId surveyId;

    @Property("questionId")
    private String questionId;

    @Property("title")
    private String title;

    @Property("answers")
    private List<String> answers;

    @Property("quantity")
    private List<Integer> quantity;

    //Lifecycle methods -- Pre/PostLoad, Pre/PostPersist...
    @PostLoad
    private void postLoad(DBObject dbObj) {
        LOGGER.info("postLoad: {}", dbObj);

    }

    public ResultEntityMongo() {
    }

    public ResultEntityMongo(ObjectId surveyId, String questionId,String title,List<String> answers,List<Integer> quantity) {
        this.surveyId = surveyId;
        this.questionId = questionId;
        this.title = title;
        this.answers = answers;
        this.quantity = quantity;
    }

    public ObjectId getSurveyId() {return surveyId;}
    public String getQuestionId() {return questionId;}
    public String getTitle() {return title;}
    public List<String> getAnswers() {return answers;}
    public List<Integer> getQuantity() {return quantity;}

}
