package pl.edu.uam.restapi.storage.database;

import pl.edu.uam.restapi.storage.model.User;
import pl.edu.uam.restapi.storage.model.Survey;
import pl.edu.uam.restapi.storage.model.Result;
import java.util.Collection;

public interface UserDatabase {
    User getUser(String id);

    User createUser(User user);

    Collection<User> getUsers();

    Survey getSurvey(String id);

    Result getResult(String id, String questionId);

    Result createResult(Result result);

    Collection<Result> getResults();

    Survey createSurvey(Survey survey);

    Collection<Survey> getSurveys();

}
