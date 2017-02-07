package pl.edu.uam.restapi.storage.entity;

import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.uam.restapi.storage.entity.QuestionEntityMongo;
import pl.edu.uam.restapi.storage.model.Question;
import java.util.*;


import javax.persistence.*;

@Entity
//@Table(name = "users")
@Table(name = "surveys")
@NamedQueries({
        @NamedQuery(name = "surveys.findAll", query = "SELECT u FROM SurveyEntity u")
})
/**
 * Created by s407283 on 19.12.2016.
 */
public class SurveyEntity {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserEntity.class);
    StringBuilder listString = new StringBuilder();


    // auto-generated
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //fields can be renamed
    //@Column(name = "first")
    @Column(name = "title")
    private String title;

    //fields can be renamed
    //@Column(name = "last")
    @Column(name = "pytania")
    private List<Question> pytania;

    @PostLoad
    private void postLoad() {
        LOGGER.info("postLoad: {}", toString());
    }

    public SurveyEntity() {
    }

    public SurveyEntity(String title, List<Question> pytania) {
        this.title = title;
        /*List<String> nova = new ArrayList<>();
        for (String string : questions) {
            string.replace("\\", "o");
            nova.add(string);
        }
        this.questions = nova;*/
        this.pytania = pytania;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Question> getQuestions() {
        return pytania;
    }


    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("title", title)
                .add("pytania", pytania).toString();
                //.toString().replace("\\", "");
    }
}
