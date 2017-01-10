package pl.edu.uam.restapi.storage.database;

import pl.edu.uam.restapi.storage.model.Question;
import pl.edu.uam.restapi.storage.model.User;
import pl.edu.uam.restapi.storage.model.Survey;
import java.util.Collection;
import java.util.HashMap;
import java.util.*;

public class MemoryDB implements UserDatabase {
    private static Map<String, User> users = new HashMap<String, User>() {{
        put("0", new User("0", "Mieszko", "Pierwszy", "kurwa"));
        put("1", new User("1", "Bolesław", "Chrobry", "kurwa"));
        put("2", new User("1", "Kazimierz", "Wielki", "kurwa"));
    }};

    @Override
    public User getUser(String id) {
        return users.get(id);
    }

    @Override
    public User createUser(User user) {
        String id = Integer.toString(users.size());
        User value = new User(id, user.getFirstName(), user.getLastName(), user.getLegia());
        users.put(id, value);
        return value;
    }

    @Override
    public Collection<User> getUsers() {
        return users.values();
    }

    @Override
    public Survey getSurvey(String id) {
        List<Question> lista = new ArrayList<>();
        List<String> lista1 = new ArrayList<>();
        lista1.add("a");
        lista.add(new Question(1, "tytul", "multiple", lista1));
        Survey survey = new Survey(id, "tytul", lista);
        return survey;
    }

    @Override
    public Survey createSurvey(Survey survey) {
        String id = "1";
        Survey value = new Survey(id, survey.getTitle(), survey.getQuestions());
        //users.put(id, value);
        return value;
    }

    @Override
    public Collection<Survey> getSurveys() {
        Collection<Survey> kolekcja = new ArrayList<Survey>();
        return kolekcja;
    }



}
