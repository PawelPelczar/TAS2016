package pl.edu.uam.restapi.storage.entity;


import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.PostLoad;
import org.mongodb.morphia.annotations.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Embeddable;
import java.util.*;
/**
 * Created by s407283 on 03.01.2017.
 */
@Embeddable
public class QuestionEntityMongo {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionEntityMongo.class);

    // auto-generated, if not set (see ObjectId)
    @Property("idPyt")
    private int idPyt;

    //fields can be renamed
    @Property("pytanie")
    private String pytanie;

    //fields can be renamed
    @Property("rodzaj")
    private String rodzaj;

    @Property("odpowiedzi")
    private List<String> odpowiedzi;

    //Lifecycle methods -- Pre/PostLoad, Pre/PostPersist...
    @PostLoad
    private void postLoad(DBObject dbObj) {
        LOGGER.info("postLoad: {}", dbObj);
    }

    public QuestionEntityMongo() {
    }

    public QuestionEntityMongo(int idPyt, String pytanie, String rodzaj, List<String> odpowiedzi) {
        this.idPyt = idPyt;
        this.pytanie = pytanie;
        this.rodzaj = rodzaj;
        this.odpowiedzi = odpowiedzi;
    }

    public int getId() {
        return idPyt;
    }

    public String getPytanie() {
        return pytanie;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public List<String> getOdpowiedzi() {
        return odpowiedzi;
    }
}
