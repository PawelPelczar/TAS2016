package pl.edu.uam.restapi.storage.database;

import pl.edu.uam.restapi.storage.model.User;

import java.util.Collection;

public interface UserDatabase {
    User getUser(String id);

    User createUser(User user);

    Collection<User> getUsers();
}
