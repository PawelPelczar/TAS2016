package pl.edu.uam.restapi.storage.database;

import com.mongodb.*;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import pl.edu.uam.restapi.storage.entity.UserEntityMongo;
import pl.edu.uam.restapi.storage.entity.SurveyEntityMongo;
import pl.edu.uam.restapi.storage.entity.QuestionEntityMongo;
import pl.edu.uam.restapi.storage.model.User;
import pl.edu.uam.restapi.storage.model.Survey;
import pl.edu.uam.restapi.storage.model.Question;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MongoDB implements UserDatabase {
    //mongodb://<dbuser>:<dbpassword>@ds131878.mlab.com:31878/tas2016
    private static final String HOST = "ds131878.mlab.com";
    private static final int PORT = 31878;
    private static final String DATABASE = "tas2016";
    private static final String USER_NAME = "tas2016";
    private static final String PASSWORD = "Bedzie3";

    private static Datastore datastore;

    public static Datastore getDatastore() {
        if (datastore == null) {
            Morphia morphia = new Morphia();
            morphia.map(UserEntityMongo.class);
            morphia.map(SurveyEntityMongo.class);
            MongoClient client = createMongoClient();
            datastore = morphia.createDatastore(client, DATABASE);
        }

        return datastore;
    }

    private static MongoClient createMongoClient() {
        MongoClientURI uri =
                new MongoClientURI(
                    String.format(
                        "mongodb://%s:%s@%s:%s/%s",
                        USER_NAME,
                        PASSWORD,
                        HOST,
                        PORT,
                        DATABASE
                    )
                );

        try {
            return new MongoClient(uri);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUser(String id) {
        try {
            UserEntityMongo userEntity = getDatastore()
                    .find(UserEntityMongo.class)
                    .field("id")
                    .equal(new ObjectId(id))
                    .get();

            if (userEntity != null) {
                return buildUserResponse(userEntity);
            }

            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public Survey getSurvey(String id){
        try {
            SurveyEntityMongo SurveyEntity = getDatastore()
                    .find(SurveyEntityMongo.class)
                    .field("id")
                    //.equal(new ObjectId(id))
                    .equal(id)
                    .get();

            if (SurveyEntity != null) {
                return buildSurveyResponse(SurveyEntity);
            }

            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    /*
    @Override
    public List<String> getAnswers(String id, int idPyt){
                try {
                        ResponseEntityMongo ResponseEntity = getDatastore().find(ResponseEntityMongo.class).field("surveyId").equal(id).get();
                        if (ResponseEntity != null)
                        {
                            return
                        }
                    }
                catch (IllegalArgumentException e) {
                    return null;
                }


            }



    */
    @Override
    public User createUser(User user) {
        UserEntityMongo userEntity = buildUserEntity(user, false);
        Key<UserEntityMongo> userEntityKey = getDatastore()
                .save(userEntity);

        return buildUserResponse(userEntity, userEntityKey.getId());
    }

    @Override
    public Survey createSurvey(Survey survey) {
        SurveyEntityMongo surveyEntity = buildSurveyEntity(survey);
        Key<SurveyEntityMongo> surveyEntityKey = getDatastore()
                .save(surveyEntity);

        return buildSurveyResponse(surveyEntity, surveyEntityKey.getId());
    }

    @Override
    public Collection<User> getUsers() {
        Collection<User> lists = new ArrayList<User>();

        for (UserEntityMongo userEntity : getDatastore().find(UserEntityMongo.class)) {
            lists.add(buildUserResponse(userEntity));
        }

        return lists;
    }

    @Override
    public Collection<Survey> getSurveys() {
        Collection<Survey> lists = new ArrayList<Survey>();

        for (SurveyEntityMongo surveyEntity : getDatastore().find(SurveyEntityMongo.class)) {
            lists.add(buildSurveyResponse(surveyEntity));
        }

        return lists;
    }

    private User buildUserResponse(UserEntityMongo userEntity, Object id) {
        return new User(id.toString(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getLegia());
    }

    private Survey buildSurveyResponse(SurveyEntityMongo surveyEntity, Object id) {
        return new Survey(id.toString(), surveyEntity.getTitle(), surveyEntity.getQuestions());
    }

    private User buildUserResponse(UserEntityMongo userEntity) {
        return new User(userEntity.getId().toString(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getLegia());
    }

    private Survey buildSurveyResponse(SurveyEntityMongo surveyEntity) {
        return new Survey(Integer.toString(surveyEntity.getId()), surveyEntity.getTitle(), surveyEntity.getQuestions());
    }

    private UserEntityMongo buildUserEntity(User user, boolean active) {
        return new UserEntityMongo(user.getFirstName(), user.getLastName(), active, user.getLegia());
    }

    private SurveyEntityMongo buildSurveyEntity(Survey survey) {
        return new SurveyEntityMongo(survey.getTitle(), survey.getQuestions());
    }

}
