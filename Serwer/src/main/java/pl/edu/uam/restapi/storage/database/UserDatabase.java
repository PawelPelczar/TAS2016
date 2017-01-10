package pl.edu.uam.restapi.storage.database;

import pl.edu.uam.restapi.storage.model.User;
import pl.edu.uam.restapi.storage.model.Survey;
import java.util.Collection;

public interface UserDatabase {
    User getUser(String id);

    User createUser(User user);

    Collection<User> getUsers();

    Survey getSurvey(String id);

    Survey createSurvey(Survey survey);

    Collection<Survey> getSurveys();

}
