package pl.edu.uam.restapi.storage.database;

import com.google.common.collect.Lists;
import pl.edu.uam.restapi.storage.entity.UserEntity;
import pl.edu.uam.restapi.storage.model.Question;
import pl.edu.uam.restapi.storage.model.Survey;
import pl.edu.uam.restapi.storage.model.User;
import pl.edu.uam.restapi.storage.model.Result;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.*;

public class MySQLDB implements UserDatabase {



    private static final String HOST = "uamtest01.cczwexxpahvt.us-west-2.rds.amazonaws.com";
    private static final int PORT = 3306;
    private static final String DATABASE = "uamtest01";
    private static final String USER_NAME = "uam_test_01";
    private static final String PASSWORD = "uam_test_01";

    private static EntityManager entityManager;

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            //String dbUrl = "jdbc:mysql://" + HOST + ':' + PORT + "/" + DATABASE + "&serverTimezone=UTC";
            String dbUrl = "jdbc:mysql://" + HOST + ':' + PORT + "/" + DATABASE;
            Map<String, String> properties = new HashMap<String, String>();

            properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
            properties.put("hibernate.connection.url", dbUrl);
            properties.put("hibernate.connection.username", USER_NAME);
            properties.put("hibernate.connection.password", PASSWORD);
            properties.put("hibernate.show_sql", "true");
            properties.put("hibernate.format_sql", "true");

            properties.put("hibernate.temp.use_jdbc_metadata_defaults", "false"); //PERFORMANCE TIP!
            properties.put("hibernate.hbm2ddl.auto", "update"); //update schema for entities (create tables if not exists)

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit", properties);
            entityManager = emf.createEntityManager();
        }

        return entityManager;
    }

    @Override
    public User getUser(String sid) {
        Long id = null;

        try {
            id = Long.valueOf(sid);
        } catch (NumberFormatException e) {
            return null;
        }

        UserEntity userEntity = getEntityManager()
                .find(UserEntity.class, id);

        if (userEntity != null) {
            return buildUserResponse(userEntity);
        }

        return null;
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
    public User createUser(final User user) {
        UserEntity entity = buildUserEntity(user, false);

        try {
            getEntityManager().getTransaction().begin();

            // Operations that modify the database should come here.
            getEntityManager().persist(entity);

            getEntityManager().getTransaction().commit();
        } finally {
            if (getEntityManager().getTransaction().isActive()) {
                getEntityManager().getTransaction().rollback();
            }
        }

        return new User(String.valueOf(entity.getId()), entity.getName(), entity.getPass(), entity.getEmail());
    }

    @Override
    public Collection<User> getUsers() {
        Query query = getEntityManager().createNamedQuery("users.findAll");
        List<UserEntity> resultList = query.getResultList();

        List<User> list = Collections.emptyList();

        if (resultList != null && !resultList.isEmpty()) {
            list = Lists.newArrayListWithCapacity(resultList.size());

            for (UserEntity user : resultList) {
                list.add(buildUserResponse(user));
            }
        }

        return list;
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

    private User buildUserResponse(UserEntity userEntity) {
        return new User(userEntity.getId().toString(), userEntity.getName(), userEntity.getPass(), userEntity.getEmail());
    }

    private UserEntity buildUserEntity(User user, boolean active) {
        return new UserEntity(user.getName(), user.getPass(), active, user.getEmail());
    }
}