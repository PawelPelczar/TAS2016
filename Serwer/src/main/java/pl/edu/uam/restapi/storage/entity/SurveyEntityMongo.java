package pl.edu.uam.restapi.storage.entity;

import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import pl.edu.uam.restapi.storage.entity.QuestionEntityMongo;
import pl.edu.uam.restapi.storage.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

@Entity("surveys")
public class SurveyEntityMongo {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserEntityMongo.class);

    // auto-generated, if not set (see ObjectId)
    @Id
    int id;

    //fields can be renamed
    @Property("title")
    private String title;

    //fields can be renamed
    @Property("pytania")
    //@Embedded("pytania")
    private List<Question> questions;

    //Lifecycle methods -- Pre/PostLoad, Pre/PostPersist...
    @PostLoad
    private void postLoad(DBObject dbObj) {
        LOGGER.info("postLoad: {}", dbObj);
    }

    public SurveyEntityMongo() {
    }

    public SurveyEntityMongo(String title, List<Question> questions) {
        this.title = title;
        /*List<String> nova = new ArrayList<>();
        for (String string : questions) {
            string.replace("\\", "o");
            nova.add(string);
        }
        this.questions = nova;*/
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Question> getQuestions() {
        return questions;
    }


}
