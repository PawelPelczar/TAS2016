package pl.edu.uam.restapi.storage.database;

import pl.edu.uam.restapi.storage.model.Question;
import pl.edu.uam.restapi.storage.model.User;
import pl.edu.uam.restapi.storage.model.Survey;
import pl.edu.uam.restapi.storage.model.Result;

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
        User value = new User(id, user.getName(), user.getPass(), user.getEmail());
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
    public Result getResult(String id, String questionId) {
        List<String> listapytan = new ArrayList<>();
        List<Integer> listawynikow = new ArrayList<>();
        Result result = new Result(id, questionId, "Tytuł", listapytan, listawynikow);
        return result;
    }

    @Override
    public Result createResult(Result result) {
        Result newResult = new Result(result.getSurveyId(), result.getQuestionId(), result.getTitle(), result.getAnswers(), result.getQuantity());
        return newResult;
    }
    @Override
    public Collection<Result> getResults() {
        Collection<Result> kolekcja = new ArrayList<Result>();
        return kolekcja;
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
