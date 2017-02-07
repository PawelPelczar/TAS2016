package pl.edu.uam.restapi.storage.database;

import pl.edu.uam.restapi.storage.model.Question;
import pl.edu.uam.restapi.storage.model.Survey;
import pl.edu.uam.restapi.storage.model.User;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
import pl.edu.uam.restapi.storage.model.Result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisDB implements UserDatabase {

    private static final String HOST = "dab.redistogo.com";
    private static final int PORT = 9498;
    private static final String PASSWORD = "05615f45a0fe99d5c91340e13c68432d";
    private static final String USERS_COLLECTION = "users_collection";

    private final String[] USER_FIELDS = {"id", "firstName", "lastName", "active"};

    private static JedisPool datastore;

    public static JedisPool getJedisPool() {
        if (datastore == null) {
            datastore = new JedisPool(
                    new JedisPoolConfig(),
                    HOST,
                    PORT,
                    Protocol.DEFAULT_TIMEOUT,
                    PASSWORD
            );

        }

        return datastore;
    }

    private static Jedis getJedisResource() {
        return getJedisPool().getResource();
    }

    @Override
    public User getUser(String id) {
        Jedis jedisResource = getJedisResource();

        try {
            Boolean exists = jedisResource.hexists(id, "id");
            if (exists) {
                List<String> userFields = jedisResource.hmget(id, USER_FIELDS);
                return buildUserResponse(userFields);
            }

            return null;

        } finally {
            getJedisPool().returnResource(jedisResource);
        }
    }

    @Override
    public User createUser(final User user) {
        Jedis jedisResource = getJedisResource();

        try {
            final String id = String.valueOf(jedisResource.scard(USERS_COLLECTION) + 1);

            Map<String, String> userMap = new HashMap<String, String>() {{
                put("id", id);
                put("name", user.getName());
                put("pass", user.getPass());
                put("email", user.getEmail());
            }};

            jedisResource.hmset(id, userMap);
            jedisResource.sadd(USERS_COLLECTION, id);

            return buildUserResponse(user, id);

        } finally {
            getJedisPool().returnResource(jedisResource);
        }
    }

    @Override
    public Collection<User> getUsers() {
        Jedis jedisResource = getJedisResource();

        try {
            Collection<User> lists = new ArrayList<User>();
            Set<String> ids = jedisResource.smembers(USERS_COLLECTION);

            for (String id : ids) {
                List<String> userFields = jedisResource.hmget(id, USER_FIELDS);
                lists.add(buildUserResponse(userFields));
            }

            return lists;

        } finally {
            getJedisPool().returnResource(jedisResource);
        }
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
    public Collection<Survey> getSurveys() {
        Collection<Survey> kolekcja = new ArrayList<Survey>();
        return kolekcja;
    }

    private User buildUserResponse(User user, Object id) {
        return new User(user.getId().toString(), user.getName(), user.getPass(), user.getEmail());
    }

    private User buildUserResponse(List<String> list) {
        return new User(list.get(0), list.get(1), list.get(2), list.get(2));
    }
}
