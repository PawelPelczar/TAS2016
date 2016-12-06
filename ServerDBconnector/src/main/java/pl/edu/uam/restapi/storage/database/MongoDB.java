package pl.edu.uam.restapi.storage.database;

import com.mongodb.*;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import pl.edu.uam.restapi.storage.entity.UserEntityMongo;
import pl.edu.uam.restapi.storage.model.User;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MongoDB implements UserDatabase {

    private static final String HOST = "ds053190.mlab.com";
    private static final int PORT = 53190;
    private static final String DATABASE = "uam_test_01";
    private static final String USER_NAME = "uam_test_01";
    private static final String PASSWORD = "uam_test_01";

    private static Datastore datastore;

    public static Datastore getDatastore() {
        if (datastore == null) {
            Morphia morphia = new Morphia();
            morphia.map(UserEntityMongo.class);
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
    public User createUser(User user) {
        UserEntityMongo userEntity = buildUserEntity(user, false);
        Key<UserEntityMongo> userEntityKey = getDatastore()
                .save(userEntity);

        return buildUserResponse(userEntity, userEntityKey.getId());
    }

    @Override
    public Collection<User> getUsers() {
        Collection<User> lists = new ArrayList<User>();

        for (UserEntityMongo userEntity : getDatastore().find(UserEntityMongo.class)) {
            lists.add(buildUserResponse(userEntity));
        }

        return lists;
    }

    private User buildUserResponse(UserEntityMongo userEntity, Object id) {
        return new User(id.toString(), userEntity.getFirstName(), userEntity.getLastName());
    }

    private User buildUserResponse(UserEntityMongo userEntity) {
        return new User(userEntity.getId().toString(), userEntity.getFirstName(), userEntity.getLastName());
    }

    private UserEntityMongo buildUserEntity(User user, boolean active) {
        return new UserEntityMongo(user.getFirstName(), user.getLastName(), active);
    }
}
