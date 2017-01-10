package pl.edu.uam.restapi.storage.database;

import com.google.common.collect.Lists;
import pl.edu.uam.restapi.storage.entity.UserEntity;
import pl.edu.uam.restapi.storage.model.Question;
import pl.edu.uam.restapi.storage.model.Survey;
import pl.edu.uam.restapi.storage.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.*;

/**
 * Created by s407283 on 12.12.2016.
 */
public class MsSQLDB implements UserDatabase {

    private static EntityManager entityManager;

    private static final String HOST = "mssql.labs.wmi.amu.edu.pl";
    private static final int PORT = 1433;
    private static final String DATABASE = "dtasAnkiety2016";
    private static final String USER_NAME = "dtasAnkiety2016";
    private static final String PASSWORD = "kkW1yOiS";

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

        return new User(String.valueOf(entity.getId()), entity.getFirstName(), entity.getLastName(), user.getLegia());
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
        return new User(userEntity.getId().toString(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getLegia());
    }

    private UserEntity buildUserEntity(User user, boolean active) {
        return new UserEntity(user.getFirstName(), user.getLastName(), active, user.getLegia());
    }
}

