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


@Entity("users")
public class UserEntityMongo {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserEntityMongo.class);

    // auto-generated, if not set (see ObjectId)
    @Id
    ObjectId id;

    //fields can be renamed
    @Property("name")
    private String name;

    //fields can be renamed
    @Property("pass")
    private String pass;

    @Property("email")
    private String email;

    //fields can be indexed for better performance

    //Lifecycle methods -- Pre/PostLoad, Pre/PostPersist...
    @PostLoad
    private void postLoad(DBObject dbObj) {
        LOGGER.info("postLoad: {}", dbObj);
    }

    public UserEntityMongo() {
    }

    public UserEntityMongo(String name, String pass, String email) {
        this.name = name;
        this.pass = pass;
        this.email = email;
    }

    public ObjectId getId() {
        return id;
    }

    public String getName() { return name;}

    public String getPass() { return pass;}

    public String getEmail() { return email;}
}
